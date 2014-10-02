import entity.Person;
import entity.RoleSchool;
import entity.Student;
import entity.Teacher;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Desting
 */
public class TestPerson {
    Person per;
    Person per2;
    
    @Before
    public void setUp() {
        per = new Person("Lars", "IsCool", "12345" , "BIF@Forever.dk");
        
    }
    
    @Test
    public void changeInfo(){
        per.setFirstName("a");
        per.setLastName("b");
        per.setPhone("1");
        per.setEmail("c");
        
        
        assertEquals("Name: a b with email: c and phone number: 1", per.toString());
    }
    
    @Test
    public void testWtestDB(){
        //Adding a second person
        per2 = new Person("Nikolaj", "Desting", "555", "haha@lol.dk");
        
        //Putting the 2 person objects in an arraylist
        ArrayList<Person> list = new ArrayList();
        list.add(per);
        list.add(per2);
        
        //Initializing 2 new person objects to hold the data from the list
        Person p3 = new Person();
        Person p4 = new Person();
        p3 = list.get(0);
        p4 = list.get(1);
        
        assertEquals(2, list.size());
        assertThat(per, is(p3));
        assertThat(per2, is(p4));
        
    }
    
    @Test
    public void addRole(){
        //Adding 2 roles to the person
        Teacher teacher = new Teacher("Computer Science");
        Student student = new Student("3rd");
        per.addRole(teacher);
        per.addRole(student);
        
        //Creating a list to store the roles
        List<RoleSchool> roles = new ArrayList();
        
        //Putting the roles inside of the list
        roles = per.checkRoles();
        
        //Initializing a RoleSchool object to store a single role
        RoleSchool role = new RoleSchool();
        
        //Filling it up with first value from the list
        role = roles.get(0);
        
        
        assertEquals("Teacher", role.getRoleName());
        
        //Setting role to Student instead of initializing a new RoleSchool object
        role = roles.get(1);
        
        assertEquals("Student", role.getRoleName());
        
        assertEquals(2, roles.size());
    }
    
}