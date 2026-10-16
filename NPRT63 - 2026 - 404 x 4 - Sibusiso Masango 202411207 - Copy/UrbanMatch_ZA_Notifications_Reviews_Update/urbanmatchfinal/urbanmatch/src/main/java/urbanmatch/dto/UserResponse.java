package urbanmatch.dto;
import urbanmatch.entity.User;
public record UserResponse(Long id,String fullName,String email,String role,String status,String phone,String location) {
 public static UserResponse of(User u) {return new UserResponse(u.id,u.fullName,u.email,u.role,u.status,u.phone,u.location);}
}
