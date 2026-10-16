package urbanmatch.repository;
import urbanmatch.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface JobRepository extends JpaRepository<Job,Long> {
 List<Job> findByStatusOrderByCreatedAtDesc(String status);
 List<Job> findByEmployerIdOrderByCreatedAtDesc(Long employerId);
}
