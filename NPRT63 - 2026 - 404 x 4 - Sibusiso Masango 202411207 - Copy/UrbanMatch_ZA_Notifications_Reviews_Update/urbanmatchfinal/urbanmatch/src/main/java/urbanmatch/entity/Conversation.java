package urbanmatch.entity;
import jakarta.persistence.*;
@Entity @Table(name="conversations")
public class Conversation {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="seeker_id") public Long seekerId;
 @Column(name="employer_id") public Long employerId;
 @Column(name="job_id") public Long jobId;
}
