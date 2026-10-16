package urbanmatch.dto;
import jakarta.validation.constraints.*;
public record RegisterRequest(
 @NotBlank String fullName,
 @NotBlank @Email String email,
 @NotBlank @Size(min=8) String password,
 @NotBlank @Pattern(regexp="seeker|employer") String role,
 String phone, String location
) {}
