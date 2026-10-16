package urbanmatch.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name="jobs")
public class Job {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="employer_id",nullable=false) public Long employerId;
 @Column(nullable=false) public String title;
 @Column(nullable=false,columnDefinition="TEXT") public String description;
 public String category;
 public String location;
 @Column(name="employment_type") public String employmentType;
 @Column(name="salary_min") public BigDecimal salaryMin;
 @Column(name="salary_max") public BigDecimal salaryMax;
 @Column(name="salary_frequency") public String salaryFrequency;
 @Column(columnDefinition="TEXT") public String requirements;
 @Column(columnDefinition="TEXT") public String skills;
 public String status="open";
 @Column(name="created_at",insertable=false,updatable=false) public LocalDateTime createdAt;
 @Column(name="updated_at",insertable=false,updatable=false) public LocalDateTime updatedAt;
}
