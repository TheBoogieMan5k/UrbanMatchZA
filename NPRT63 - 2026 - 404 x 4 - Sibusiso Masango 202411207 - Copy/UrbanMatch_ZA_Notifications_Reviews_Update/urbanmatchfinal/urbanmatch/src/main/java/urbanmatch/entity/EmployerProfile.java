package urbanmatch.entity;
import jakarta.persistence.*;
@Entity @Table(name="employer_profiles")
public class EmployerProfile {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="user_id") public Long userId;
 @Column(name="company_name") public String companyName;
 @Column(name="industry") public String industry;
 @Column(name="company_description") public String companyDescription;
 @Column(name="website") public String website;
 @Column(name="company_logo") public String companyLogo;
}
