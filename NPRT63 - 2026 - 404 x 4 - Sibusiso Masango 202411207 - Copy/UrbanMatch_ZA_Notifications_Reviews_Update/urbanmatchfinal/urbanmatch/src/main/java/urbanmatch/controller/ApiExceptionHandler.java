package urbanmatch.controller;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<Map<String,String>> invalid(MethodArgumentNotValidException e){
  String message=e.getBindingResult().getFieldErrors().stream().findFirst()
    .map(x->x.getField()+": "+x.getDefaultMessage()).orElse("Invalid request");
  return ResponseEntity.badRequest().body(Map.of("error",message));
 }
}
