package urbanmatch.repository;
import urbanmatch.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ReviewRepository extends JpaRepository<Review,Long> {}
