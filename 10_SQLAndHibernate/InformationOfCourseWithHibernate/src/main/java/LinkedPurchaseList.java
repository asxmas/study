import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LinkedPurchaseList")
@Data
public class LinkedPurchaseList implements Serializable {
    @Id
    @Column(name = "student_Id")
    private Integer studentId;

    @Id
    @Column(name = "course_Id")
    private Integer courseId;

    public LinkedPurchaseList() {}

    public LinkedPurchaseList(Integer studentId, Integer courseId){
       this.studentId = studentId;
       this.courseId = courseId;
   }




}
