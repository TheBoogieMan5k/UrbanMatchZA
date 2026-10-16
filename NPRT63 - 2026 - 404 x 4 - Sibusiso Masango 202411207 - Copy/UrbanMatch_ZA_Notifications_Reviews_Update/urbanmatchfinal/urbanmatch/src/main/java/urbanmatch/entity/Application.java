package urbanmatch.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="applications",uniqueConstraints=@UniqueConstraint(columnNames={"job_id","seeker_id"}))
public class Application {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="job_id",nullable=false) public Long jobId;
 @Column(name="seeker_id",nullable=false) public Long seekerId;
 @Column(name="cover_message",columnDefinition="TEXT") public String coverMessage;
 @Column(name="cv_filename") public String cvFilename;
 @Column(name="cv_storage_path") public String cvStoragePath;
 public String status="pending";
 @Column(name="applied_at",insertable=false,updatable=false) public LocalDateTime appliedAt;
 @Column(name="updated_at",insertable=false,updatable=false) public LocalDateTime updatedAt;
}
