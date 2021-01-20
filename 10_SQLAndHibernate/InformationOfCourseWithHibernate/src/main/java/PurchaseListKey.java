import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ToString
@Data
public class PurchaseListKey implements Serializable {

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

}

