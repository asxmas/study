import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
@Data
@IdClass(Key.class)
public class Subscription {

    @EmbeddedId
    private Key id;

    @OneToOne
    @Column(name = "student_id")
    private int studentId;

    @OneToOne
    @Column(name = "course_id")
    private int courseId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @Column(name = "subscription_date")
    private Date registrationDate;

}
