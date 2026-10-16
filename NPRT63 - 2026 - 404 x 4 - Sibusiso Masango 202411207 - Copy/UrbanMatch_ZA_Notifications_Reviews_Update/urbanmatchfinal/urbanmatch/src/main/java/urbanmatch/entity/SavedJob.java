package urbanmatch.entity;
import jakarta.persistence.*;
@Entity @Table(name="saved_jobs")
public class SavedJob {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="seeker_id") public Long seekerId;
 @Column(name="job_id") public Long jobId;
}
