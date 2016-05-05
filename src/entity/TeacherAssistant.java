package entity;

import java.io.Serializable;
import javax.persistence.Entity;
//Entity class representing the TeacherAssistant table, inherited by RoleSchool. 
//Created by everyone.

@Entity
public class TeacherAssistant extends RoleSchool implements Serializable {

    public TeacherAssistant() {
        super.setRoleName( "TeacherAssistant" );
    }
}
