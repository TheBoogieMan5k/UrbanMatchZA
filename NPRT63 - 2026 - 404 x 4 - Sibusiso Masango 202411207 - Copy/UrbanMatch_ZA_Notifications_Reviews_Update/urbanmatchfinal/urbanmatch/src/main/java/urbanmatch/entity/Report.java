package urbanmatch.entity;
import jakarta.persistence.*;
@Entity @Table(name="reports")
public class Report {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="reporter_id") public Long reporterId;
 @Column(name="reported_user_id") public Long reportedUserId;
 @Column(name="reported_job_id") public Long reportedJobId;
 @Column(name="reason") public String reason;
 @Column(name="description") public String description;
 @Column(name="status") public String status;
}
