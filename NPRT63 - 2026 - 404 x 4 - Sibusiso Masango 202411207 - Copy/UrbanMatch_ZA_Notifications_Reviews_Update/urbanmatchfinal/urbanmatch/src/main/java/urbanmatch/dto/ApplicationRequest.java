package urbanmatch.dto;
import jakarta.validation.constraints.*;
public record ApplicationRequest(@NotNull Long jobId, String coverMessage) {}
