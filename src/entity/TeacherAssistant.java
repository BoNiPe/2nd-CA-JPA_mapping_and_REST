package entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class TeacherAssistant extends RoleSchool implements Serializable {

    public TeacherAssistant() {
        super.setRoleName("TeacherAssistant");
    }
}
