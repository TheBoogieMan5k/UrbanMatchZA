package urbanmatch.controller;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import urbanmatch.dto.ApplicationRequest;
import urbanmatch.entity.Application;
import urbanmatch.service.ApplicationService;
import java.util.List;
@RestController @RequestMapping("/api/applications")
public class ApplicationController {
 private final ApplicationService service;public ApplicationController(ApplicationService service){this.service=service;}
 @PostMapping public Application apply(Authentication a,@Valid @RequestBody ApplicationRequest r){return service.apply(a.getName(),r);}
 @GetMapping("/mine") public List<Application> mine(Authentication a){return service.mine(a.getName());}
 @GetMapping("/job/{jobId}") public List<Application> job(Authentication a,@PathVariable Long jobId){return service.forJob(a.getName(),jobId);}
 @PatchMapping("/{id}/status") public Application status(Authentication a,@PathVariable Long id,@RequestParam String value){return service.status(a.getName(),id,value);}
}
