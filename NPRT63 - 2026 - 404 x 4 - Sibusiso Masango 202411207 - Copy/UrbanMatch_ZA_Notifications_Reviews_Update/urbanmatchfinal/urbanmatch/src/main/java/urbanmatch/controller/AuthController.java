package urbanmatch.controller;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import urbanmatch.dto.*;
import urbanmatch.service.AuthService;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AuthService service;public AuthController(AuthService service){this.service=service;}
 @PostMapping("/register") public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.register(r));}
 @GetMapping("/me") public UserResponse me(Authentication a){return service.me(a.getName());}
 // Stateless HTTP Basic: each protected request must include Authorization credentials.
 @GetMapping("/login") public UserResponse login(Authentication a){return service.me(a.getName());}
}
