package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personIDSeq")
    @SequenceGenerator(name = "personIDSeq", sequenceName = "PERSON_SEQ",
            initialValue = 100, allocationSize = 1)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public Person() {
    }

    public Person(String firstName, String lastName, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
    
    @OneToMany
    List<SchoolRole> justTryin;
    
    public void addRole(SchoolRole sr){
        sr.setId(id);
        justTryin.add(sr);
        System.out.println(sr.getId() + " "+ sr.getRoleName());
    }
    
    public List checkRoles(){
        return justTryin;
    }
    
    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Name: " + getFirstName() + " " + getLastName() + " with email: "
                + getEmail() + " and phone number: " + getPhone();
    }

}
