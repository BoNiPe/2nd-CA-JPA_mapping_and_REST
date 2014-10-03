package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
//Entity class representing the RoleSchool table. Created by everyone.
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RoleSchool implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schrolIDSeq")
    @SequenceGenerator(name = "schrolIDSeq", sequenceName = "SCHROL_SEQ",
            initialValue = 200, allocationSize = 1)
    private Integer id;
    private String roleName;

    public RoleSchool(String role) {
        this.roleName = role;
    }

    public RoleSchool() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
