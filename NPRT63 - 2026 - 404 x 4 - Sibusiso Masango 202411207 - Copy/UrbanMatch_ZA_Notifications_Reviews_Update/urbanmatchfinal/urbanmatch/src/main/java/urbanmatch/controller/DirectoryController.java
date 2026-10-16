package urbanmatch.controller;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/directory")
public class DirectoryController {
 private final JdbcTemplate db;
 public DirectoryController(JdbcTemplate db){this.db=db;}
 @GetMapping public List<Map<String,Object>> list(){
  return db.queryForList("SELECT id, full_name, role, status, location FROM users WHERE status='active' AND role <> 'admin'");
 }
}
