package facade;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Person;
import entity.RoleSchool;
import entity.Teacher;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Facadelogic implements FacadeInterface {

    //Interface used to interact with the entity manager factory for the persistence unit.
    //Persistence knows where to put the objects and afterwards the Entity Manager
    //takes the whole information, from which it "createsEntity"
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BetaProjectbPU");
    EntityManager em = emf.createEntityManager();
    //So that we can have a single transaction after the initialization
    EntityTransaction tr;
    //Vital for the parsing to Gson
    private Gson gson = new Gson();

    @Override
    public String getPersonsAsJSON() {
        Query query = em.createQuery("SELECT a FROM Person a");
        List<Person> nesquick = (List<Person>) query.getResultList();
        return gson.toJson(nesquick);
    }

    @Override
    public String getPersonAsJSON(Integer id) {
        Person a = em.find(Person.class, id);
        return ((a == null) ? null : gson.toJson(a));
    }

    @Override
    public Person addPersonFromJSON(String json) {
        initializeTransactions();
        tr.begin();
        Person p = gson.fromJson(json, Person.class);
        em.persist(p);
        tr.commit();
        System.out.println("id:" + p.getId() + ", object:  " + p.toString());
        return p;
    }

    @Override
    public RoleSchool addRoleFromJSON(String json, Integer id) {
        Person a = em.find(Person.class, id);
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonElement currentrole = jobject.get("roleName");
        String roleToString = currentrole.getAsString();
        System.out.println("yoloswag " + roleToString);
        tr.begin();
        Teacher t;
        switch (roleToString) {
            case "Teacher":
                JsonElement currentdegree = jobject.get("degree");
                String degreeToString = currentdegree.getAsString();
                t = new Teacher(degreeToString);
                a.addRole(t);
                em.persist(t);
                break;
            default:
                return null;
        }

        tr.commit();
        return t;

    }

    @Override
    public Person deletePersonFromJSON(Integer id) {
        tr.begin();
        Person a = em.find(Person.class, id);
        if (a != null) {
            em.remove(a);
        }
        tr.commit();
        return a;
    }

    private void initializeTransactions() {
        tr = em.getTransaction();
    }

    private void testingCode() {
        String addingperson = "{ \"firstName\":\"John\", \"lastName\":\"McLaren\", \"mail\":\"j@m.uk\", \"phone\":\"3456\" }";
        String addingperson2 = "{ \"firstName\":\"aaaaaa\", \"lastName\":\"aaaaa\", \"mail\":\"aaaaaa@m.uk\", \"phone\":\"33242346\" }";
        String addingperson3 = "{ \"firstName\":\"bbbbb\", \"lastName\":\"bbbb\", \"mail\":\"bbbbbb@m.uk\", \"phone\":\"3234324236\" }";
        Person p = addPersonFromJSON(addingperson);
        Person p2 = addPersonFromJSON(addingperson2);
        Person p3 = addPersonFromJSON(addingperson3);
        System.out.println("Object: " + p.toString());
        System.out.println("Object: " + p2.toString());
        System.out.println("Object: " + p3.toString());
        System.out.println("loraloralorao>O " + getPersonsAsJSON());
        System.out.println("GetParticularPerson :" + getPersonAsJSON(100));
        String t1 = "{\"degree\":\"d-1\", \"roleName\":\"Teacher\" }";
        addRoleFromJSON(t1, 100);
        deletePersonFromJSON(102);

    }

    public static void main(String[] args) {

        new Facadelogic().testingCode();
    }

}
