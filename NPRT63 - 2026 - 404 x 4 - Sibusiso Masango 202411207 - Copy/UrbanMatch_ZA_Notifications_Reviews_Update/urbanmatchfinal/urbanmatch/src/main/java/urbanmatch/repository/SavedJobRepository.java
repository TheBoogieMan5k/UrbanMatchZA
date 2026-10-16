package urbanmatch.repository;
import urbanmatch.entity.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SavedJobRepository extends JpaRepository<SavedJob,Long> {}
