package urbanmatch.repository;
import urbanmatch.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ApplicationRepository extends JpaRepository<Application,Long> {
 boolean existsByJobIdAndSeekerId(Long jobId,Long seekerId);
 List<Application> findBySeekerIdOrderByAppliedAtDesc(Long seekerId);
 List<Application> findByJobIdOrderByAppliedAtDesc(Long jobId);
}
