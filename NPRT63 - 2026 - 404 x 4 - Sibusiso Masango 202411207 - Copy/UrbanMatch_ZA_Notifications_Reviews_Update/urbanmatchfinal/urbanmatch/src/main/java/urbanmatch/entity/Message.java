package urbanmatch.entity;
import jakarta.persistence.*;
@Entity @Table(name="messages")
public class Message {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="conversation_id") public Long conversationId;
 @Column(name="sender_id") public Long senderId;
 @Column(name="message_text") public String messageText;
 @Column(name="is_read") public Boolean isRead;
}
