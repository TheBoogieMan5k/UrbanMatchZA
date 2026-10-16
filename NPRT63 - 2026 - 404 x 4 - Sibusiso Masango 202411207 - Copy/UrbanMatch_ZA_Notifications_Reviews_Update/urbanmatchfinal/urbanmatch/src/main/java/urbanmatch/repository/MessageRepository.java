package urbanmatch.repository;
import urbanmatch.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MessageRepository extends JpaRepository<Message,Long> {}
