package urbanmatch.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import urbanmatch.dto.ApplicationRequest;
import urbanmatch.entity.*;
import urbanmatch.repository.*;
import java.util.List;
@Service
public class ApplicationService {
 private final ApplicationRepository applications;private final JobService jobs;private final org.springframework.jdbc.core.JdbcTemplate jdbc;
 public ApplicationService(ApplicationRepository applications,JobService jobs,org.springframework.jdbc.core.JdbcTemplate jdbc){this.applications=applications;this.jobs=jobs;this.jdbc=jdbc;}
 @Transactional public Application apply(String email,ApplicationRequest r){
  User u=jobs.current(email,"seeker");Job j=jobs.get(r.jobId());
  if(!"open".equals(j.status))throw new ResponseStatusException(HttpStatus.CONFLICT,"Job is closed");
  if(applications.existsByJobIdAndSeekerId(j.id,u.id))throw new ResponseStatusException(HttpStatus.CONFLICT,"Already applied");
  Application a=new Application();a.jobId=j.id;a.seekerId=u.id;a.coverMessage=r.coverMessage();
  try{Application saved=applications.saveAndFlush(a);
   jdbc.update("insert into notifications(user_id,title,message,notification_type,related_id,is_read) values(?,?,?,?,?,false)",j.employerId,"New application","A job seeker applied for "+j.title,"application",saved.id);
   jdbc.update("insert into notifications(user_id,title,message,notification_type,related_id,is_read) values(?,?,?,?,?,false)",u.id,"Application submitted","Your application for "+j.title+" was submitted.","application",saved.id);
   return saved;}
  catch(org.springframework.dao.DataIntegrityViolationException ex){throw new ResponseStatusException(HttpStatus.CONFLICT,"Duplicate or invalid application");}
 }
 public List<Application> mine(String email){return applications.findBySeekerIdOrderByAppliedAtDesc(jobs.current(email,"seeker").id);}
 public List<Application> forJob(String email,Long jobId){jobs.ownership(email,jobs.get(jobId));return applications.findByJobIdOrderByAppliedAtDesc(jobId);}
 @Transactional public Application status(String email,Long id,String status){
  if(!List.of("pending","reviewed","shortlisted","accepted","rejected").contains(status))
   throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid application status");
  Application a=applications.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Application not found"));
  Job job=jobs.get(a.jobId);jobs.ownership(email,job);a.status=status;Application updated=applications.save(a);
  jdbc.update("insert into notifications(user_id,title,message,notification_type,related_id,is_read) values(?,?,?,?,?,false)",a.seekerId,"Application update","Your application for "+job.title+" is now "+status,"application",a.id);
  return updated;
 }
}
