package entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class TeacherAssistant extends SchoolRole implements Serializable {
    
    public String swaglvl;

    public TeacherAssistant() {
    }

    public TeacherAssistant(String swaglvl) {
        this.swaglvl = swaglvl;
        super.setRoleName("TeacherAssistant");
    }

    public String getSwaglvl() {
        return swaglvl;
    }

    public void setSwaglvl(String swaglvl) {
        this.swaglvl = swaglvl;
    }
    
    
    
}
