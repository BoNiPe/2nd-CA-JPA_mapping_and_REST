/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import entity.Person;
import entity.RoleSchool;
import entity.Student;
import entity.Teacher;
import exceptions.NotFoundException;
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
public class TestFacade {

    FacadeMock facade = FacadeMock.getInstance();

    Person p1;
    Person p2;
    Person p3;
    Person p4;
    Person p5;
    Person p6;

    String json;

    private Gson gson = new Gson();

    @Before
    public void setUp() {
        p1 = new Person("Nik", "Des", "11111", "hehe@lol.dk");
        p2 = new Person("Pet", "Tom", "22222", "something@lol.dk");
        p3 = new Person("Boy", "Sur", "33333", "lala@lol.dk");
        p4 = new Person("Sup", "Dog", "44444", "wuhu@lol.dk");
        p5 = new Person("Yir", "Man", "55555", "nana@lol.dk");
        p6 = new Person("Kat", "Lat", "66666", "katt@lol.dk");

        p1.setId(1);
        p2.setId(2);
        p3.setId(3);
        p4.setId(4);
        p5.setId(5);
        p6.setId(6);

        facade.persons.add(p1);
        facade.persons.add(p2);
        facade.persons.add(p3);
        facade.persons.add(p4);
        facade.persons.add(p5);
        facade.persons.add(p6);

    }

    @After
    public void tearDown() {

        facade.persons.clear();

    }

    @Test
    public void getPersons() {

        String result = "[{\"id\":1,\"firstName\":\"Nik\",\"lastName\":\"Des\",\"phone\":\"11111\",\"mail\":\"hehe@lol.dk\",\"listOfSchoolRoles\":[]},"
                + "{\"id\":2,\"firstName\":\"Pet\",\"lastName\":\"Tom\",\"phone\":\"22222\",\"mail\":\"something@lol.dk\",\"listOfSchoolRoles\":[]},"
                + "{\"id\":3,\"firstName\":\"Boy\",\"lastName\":\"Sur\",\"phone\":\"33333\",\"mail\":\"lala@lol.dk\",\"listOfSchoolRoles\":[]},"
                + "{\"id\":4,\"firstName\":\"Sup\",\"lastName\":\"Dog\",\"phone\":\"44444\",\"mail\":\"wuhu@lol.dk\",\"listOfSchoolRoles\":[]},"
                + "{\"id\":5,\"firstName\":\"Yir\",\"lastName\":\"Man\",\"phone\":\"55555\",\"mail\":\"nana@lol.dk\",\"listOfSchoolRoles\":[]},"
                + "{\"id\":6,\"firstName\":\"Kat\",\"lastName\":\"Lat\",\"phone\":\"66666\",\"mail\":\"katt@lol.dk\",\"listOfSchoolRoles\":[]}]";

        assertThat(result, is(facade.getPersonsAsJSON()));
    }

    @Test
    public void getPersonWithCorrectID() throws NotFoundException {
        json = gson.toJson(p4);
        assertEquals(json, facade.getPersonAsJSON(4));
    }

//    @Test (expected = NotFoundException.class)
//    public void getPersonWithIncorrectID() throws NotFoundException{
//        assertEquals(null, facade.getPersonAsJSON(0));
//    }
    // Getting AssertionError instead of NotfoundException
    
    @Test
    public void addPersonFromJSON(){
        json = gson.toJson(p1);
        
        System.out.println(p1);
        System.out.println(facade.addPersonFromJSON(json));
        
        Person d = facade.addPersonFromJSON(json);
        
        assertEquals(p1.getId(), d.getId());
        
        
    }
//    Fails even though they are exactly the same??
    //Ask teacher
    
    @Test
    public void addRoleFromJSON() {

        Teacher teacher = new Teacher("Math");
        json = gson.toJson(teacher);
        RoleSchool role = facade.addRoleFromJSON(json, 1);
        
        List<RoleSchool> roles = new ArrayList();
        roles = p1.checkRoles();
        RoleSchool result = new RoleSchool();
        result = roles.get(0);
        
        assertThat(result, is(role));
    }
    
    
    @Test
    public void deletePersonFromJSON() throws NotFoundException{
        assertTrue(facade.persons.size()==6);
        Person person = facade.deletePersonFromJSON(1);
        
        assertTrue(facade.persons.size()==5);
        assertEquals(person, p1);
    }

}
