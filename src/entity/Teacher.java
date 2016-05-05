package entity;

import java.io.Serializable;
import javax.persistence.Entity;
//Entity class representing the Teacher table, inherited by RoleSchool. Created
//by everyone.

@Entity
public class Teacher extends RoleSchool implements Serializable {

    private String degree;

    public Teacher() {
    }

    public Teacher( String degree ) {
        this.degree = degree;
        super.setRoleName( "Teacher" );
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree( String degree ) {
        this.degree = degree;
    }
}
