package urbanmatch.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import urbanmatch.repository.UserRepository;
import urbanmatch.entity.User;
import urbanmatch.dto.*;
@Service
public class AuthService {
 private final UserRepository users; private final PasswordEncoder encoder;
 public AuthService(UserRepository users,PasswordEncoder encoder){this.users=users;this.encoder=encoder;}
 @Transactional public UserResponse register(RegisterRequest req){
  String email=req.email().trim().toLowerCase();
  if(users.existsByEmail(email))throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already registered");
  User u=new User();u.fullName=req.fullName().trim();u.email=email;u.passwordHash=encoder.encode(req.password());
  u.role=req.role().trim().toLowerCase();u.status="active";u.phone=req.phone();u.location=req.location();
  try{return UserResponse.of(users.saveAndFlush(u));}
  catch(org.springframework.dao.DataIntegrityViolationException e){throw new ResponseStatusException(HttpStatus.CONFLICT,"Account already exists or invalid data");}
 }
 public UserResponse me(String email){return UserResponse.of(users.findByEmail(email).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found")));}
}
