package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Tomascik
 */
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "Person", discriminatorType = DiscriminatorType.CHAR)
//@DiscriminatorValue("Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    
    //private List<String> newRoleList = new ArrayList(); // <- NOT SURE IF CORRECT WAY
  
    @ElementCollection//(fetch = FetchType.LAZY) // not sure about fetching - if it is not loading from another then no needed, right ? 
    //@MapKeyColumn(name = "Phone")
    private List<String> phone = new ArrayList();

    @ElementCollection //(fetch = FetchType.LAZY)
   //@MapKeyColumn(name = "Email")
    private List<String> email = new ArrayList();
    
//    @ElementCollection// (fetch = FetchType.LAZY) // NOT SURE IF NEEDEDFOR RoleSchool
   // @MapKeyColumn(name = "RoleSchool") 
    @OneToMany (cascade = CascadeType.ALL)//(mappedBy="person")
    @JoinColumn
    private List<RoleSchool> roles = new ArrayList();
    
    
    
      public void addRole(RoleSchool newRole)
    {
        roles.add(newRole);
    }
  
    public void addPhoneNumber(String phoneNumber) {
        phone.add(phoneNumber);
    }

    
    public void addEmail(String mail) {
        email.add(mail);
    }
   
    
    // Getters and Setters + Constructors

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
  
    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
