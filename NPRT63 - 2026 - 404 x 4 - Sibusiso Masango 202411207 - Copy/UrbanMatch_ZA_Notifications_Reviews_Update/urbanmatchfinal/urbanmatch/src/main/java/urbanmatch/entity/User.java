package urbanmatch.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="full_name",nullable=false) public String fullName;
 @Column(nullable=false,unique=true) public String email;
 @Column(name="password_hash",nullable=false) public String passwordHash;
 @Column(nullable=false) public String role;
 @Column(nullable=false) public String status="active";
 public String phone;
 public String location;
 @Column(name="created_at",insertable=false,updatable=false) public LocalDateTime createdAt;
 @Column(name="updated_at",insertable=false,updatable=false) public LocalDateTime updatedAt;
}
