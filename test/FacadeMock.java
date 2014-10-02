
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Person;
import entity.RoleSchool;
import entity.Student;
import entity.Teacher;
import entity.TeacherAssistant;
import exceptions.NotFoundException;
import facade.FacadeInterface;
import facade.Facadelogic;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Desting
 */
public class FacadeMock implements FacadeInterface{
    
    private Gson gson = new Gson();
    public static List<Person> persons = new ArrayList();
    
    private static FacadeMock instance = null;
    
    public static FacadeMock getInstance() {
        if (instance == null) {
            instance = new FacadeMock();
        }
        return instance;
    }

    @Override
    public String getPersonsAsJSON() {
        return gson.toJson(persons);
    }

    @Override
    public String getPersonAsJSON(Integer id) throws NotFoundException {
        Person a = new Person();
        
        for(int i = 0; i<persons.size(); i++){
            if(persons.get(i).getId() == id){
               a = persons.get(i);  
            }
        }
        if (a == null) {
            throw new NotFoundException("No person exists for this given ID");
        }
        
        return ((a == null) ? null : gson.toJson(a));
        
    }

    @Override
    public Person addPersonFromJSON(String json) {
        Person p = gson.fromJson(json, Person.class);
        return p;
    }

    @Override
    public RoleSchool addRoleFromJSON(String json, Integer id) {
        Person a = new Person();
        
        for(int i = 0; i<persons.size(); i++){
            if(persons.get(i).getId() == id){
               a = persons.get(i);  
               //Might have to remove and insert
            }
        }
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonElement currentrole = jobject.get("roleName");
        String roleToString = currentrole.getAsString();
        
        RoleSchool rs;
        switch (roleToString) {
            case "Teacher":
                JsonElement currentdegree = jobject.get("degree");
                String degreeToString = currentdegree.getAsString();
                rs = new Teacher(degreeToString);
                a.addRole(rs);
                break;
            case "Student":
                JsonElement currentsemester = jobject.get("semester");
                String semesterToString = currentsemester.getAsString();
                rs = new Student(semesterToString);
                a.addRole(rs);
                break;
            case "TeacherAssistant":
                rs = new TeacherAssistant();
                a.addRole(rs);
                break;
            default:
                return null;
        }
        return rs;
    }

    @Override
    public Person deletePersonFromJSON(Integer id) throws NotFoundException {
        Person a = new Person();
        
        for(int i = 0; i<persons.size(); i++){
            if(persons.get(i).getId() == id){
               a = persons.remove(i);  
            }
        }
        if (a == null) {
            throw new NotFoundException("No person exists for this given ID");
        }
        return a;
    }
    
}
