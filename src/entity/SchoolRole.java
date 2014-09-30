package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SchoolRole implements Serializable {

    @Id
    private Integer id;
    private String roleName;

    public SchoolRole(String role) {
        this.roleName = role;
    }

    public SchoolRole() {
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
