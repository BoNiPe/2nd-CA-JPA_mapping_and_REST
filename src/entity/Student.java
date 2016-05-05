package entity;

import java.io.Serializable;
import javax.persistence.Entity;
//Entity class representing the Student table, inherited by RoleSchool. Created
//by everyone.

@Entity
public class Student extends RoleSchool implements Serializable {

    private String semester;

    public Student() {
    }

    public Student( String semester ) {
        this.semester = semester;
        super.setRoleName( "Student" );
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester( String semester ) {
        this.semester = semester;
    }

}
