package urbanmatch.controller;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import urbanmatch.dto.JobRequest;
import urbanmatch.entity.Job;
import urbanmatch.service.JobService;
import java.util.List;
@RestController @RequestMapping("/api/jobs")
public class JobController {
 private final JobService jobs;public JobController(JobService jobs){this.jobs=jobs;}
 @GetMapping("/open") public List<Job> open(){return jobs.open();}
 @GetMapping("/{id}") public Job one(@PathVariable Long id){Job j=jobs.get(id);if(!"open".equals(j.status))throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND);return j;}
 @GetMapping("/mine") public List<Job> mine(Authentication a){return jobs.mine(a.getName());}
 @PostMapping public Job create(Authentication a,@Valid @RequestBody JobRequest r){return jobs.create(a.getName(),r);}
 @PutMapping("/{id}") public Job update(Authentication a,@PathVariable Long id,@Valid @RequestBody JobRequest r){return jobs.update(a.getName(),id,r);}
 @DeleteMapping("/{id}") public void delete(Authentication a,@PathVariable Long id){jobs.delete(a.getName(),id);}
 @PatchMapping("/{id}/status") public Job status(Authentication a,@PathVariable Long id,@RequestParam String value){return jobs.status(a.getName(),id,value);}
}
