package urbanmatch.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record JobRequest(
 @NotBlank String title,
 @NotBlank String description,
 String category, String location, String employmentType,
 @DecimalMin("0") BigDecimal salaryMin,
 @DecimalMin("0") BigDecimal salaryMax,
 String salaryFrequency, String requirements, String skills
) {}
