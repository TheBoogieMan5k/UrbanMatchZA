package urbanmatch.entity;
import jakarta.persistence.*;
@Entity @Table(name="notifications")
public class Notification {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="user_id") public Long userId;
 @Column(name="title") public String title;
 @Column(name="message") public String message;
 @Column(name="notification_type") public String notificationType;
 @Column(name="related_id") public Long relatedId;
 @Column(name="is_read") public Boolean isRead;
}
