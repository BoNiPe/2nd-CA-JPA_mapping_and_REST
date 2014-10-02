package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
//Entity class representing the Person table. Created by everyone.
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
    private String mail;

    public Person() {
    }

    public Person(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.mail = email;
    }
//uni-directional connection between table Person and RoleSchool with Cascade
//CascadeType removes the Joined table which @OneToMany creates and also deletes 
//all roles which have PK-FK connecitons with the particular person, who has to
//be removed from DB.
    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn
    List<RoleSchool> listOfSchoolRoles = new ArrayList();
    
    public void addRole(RoleSchool sr){
        listOfSchoolRoles.add(sr);
        System.out.println(sr.getId() + " "+ sr.getRoleName());
    }
    
    public List checkRoles(){
        return listOfSchoolRoles;
    }
    
    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return mail;
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
        this.mail = email;
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
//Helpful for testing
    @Override
    public String toString() {
        return "Name: " + getFirstName() + " " + getLastName() + " with email: "
                + getEmail() + " and phone number: " + getPhone();
    }

}
