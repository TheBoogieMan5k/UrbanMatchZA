package urbanmatch.entity;
import jakarta.persistence.*;
@Entity @Table(name="reviews")
public class Review {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="reviewer_id") public Long reviewerId;
 @Column(name="reviewee_id") public Long revieweeId;
 @Column(name="rating") public Integer rating;
 @Column(name="comment") public String comment;
}
