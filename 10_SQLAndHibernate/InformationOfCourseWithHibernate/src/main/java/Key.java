import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class Key implements Serializable {
    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    @Override
    public int hashCode() {
        int result = Integer.parseInt(47 * studentId + String.valueOf(courseId));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        Key that = (Key) o;
        if (this.courseId == that.courseId && this.studentId == that.studentId) return true;
        else return false;
    }

}
