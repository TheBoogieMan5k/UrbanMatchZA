package urbanmatch.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import urbanmatch.dto.JobRequest;
import urbanmatch.entity.*;
import urbanmatch.repository.*;
import java.util.List;
@Service
public class JobService {
 private final JobRepository jobs;private final UserRepository users;
 public JobService(JobRepository jobs,UserRepository users){this.jobs=jobs;this.users=users;}
 public User current(String email,String role){
  User u=users.findByEmail(email).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
  if(!"active".equals(u.status)||!role.equals(u.role))throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Role not allowed");return u;
 }
 public Job get(Long id){return jobs.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Job not found"));}
 public List<Job> open(){return jobs.findByStatusOrderByCreatedAtDesc("open");}
 public List<Job> mine(String email){return jobs.findByEmployerIdOrderByCreatedAtDesc(current(email,"employer").id);}
 private void assign(Job j,JobRequest r){
  if(r.salaryMin()!=null&&r.salaryMax()!=null&&r.salaryMax().compareTo(r.salaryMin())<0)
   throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Maximum salary cannot be less than minimum salary");
  j.title=r.title().trim();j.description=r.description().trim();j.category=r.category();j.location=r.location();
  j.employmentType=r.employmentType();j.salaryMin=r.salaryMin();j.salaryMax=r.salaryMax();
  j.salaryFrequency=r.salaryFrequency();j.requirements=r.requirements();j.skills=r.skills();
 }
 @Transactional public Job create(String email,JobRequest r){Job j=new Job();j.employerId=current(email,"employer").id;assign(j,r);return jobs.save(j);}
 @Transactional public Job update(String email,Long id,JobRequest r){Job j=get(id);ownership(email,j);assign(j,r);return jobs.save(j);}
 @Transactional public Job status(String email,Long id,String value){
  if(!value.equals("open")&&!value.equals("closed"))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Status must be open or closed");
  Job j=get(id);ownership(email,j);j.status=value;return jobs.save(j);
 }
 @Transactional public void delete(String email,Long id){Job j=get(id);ownership(email,j);jobs.delete(j);}
 public void ownership(String email,Job j){if(!j.employerId.equals(current(email,"employer").id))throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Not your job");}
}
