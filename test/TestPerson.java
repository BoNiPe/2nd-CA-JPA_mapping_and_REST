/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Person;
import entity.Student;
import entity.Teacher;
import java.util.ArrayList;
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
    
    public TestPerson() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        per = new Person("Lars", "IsCool", "12345" , "BIF@Forever.dk");
        
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void changeInfo(){
        per.setFirstName("a");
        per.setLastName("b");
        per.setPhone("1");
        per.setMail("c");
        
        
        assertEquals("Name: a b with email: c and phone number: 1", per.toString());
    }
    
    @Test
    public void testWtestDB(){
        Person per2 = new Person("Nikolaj", "Desting", "555", "haha@lol.dk");
        ArrayList list = new ArrayList();
        
        list.add(per);
        list.add(per2);
        
        assertEquals(2, list.size());
        assertEquals("[Name: Lars IsCool with email: BIF@Forever.dk and phone number: 12345, Name: Nikolaj Desting with email: haha@lol.dk and phone number: 555]", list.toString());
    }
    
//    @Test
//    public void addRole(){
//        Person per2 = new Person("Nikolaj", "Desting", "555", "haha@lol.dk");
//        Teacher teacher = new Teacher("Computer Science");
//        Student student = new Student("3rd");
//        per.addRole(teacher);
//        per2.addRole(teacher);
//        per2.addRole(student);
//        assertEquals("teacher", per.checkRoles());
//        assertEquals(2, per2.checkRoles());
//    }
    
}
