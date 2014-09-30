package facade;

import com.google.gson.Gson;
import entity.Person;
import entity.RoleSchool;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import sun.rmi.server.LoaderHandler;

public class Facadelogic implements FacadeInterface {
    //Interface used to interact with the entity manager factory for the persistence unit.
    //Persistence knows where to put the objects and afterwards the Entity Manager
    //takes the whole information, from which it "createsEntity"
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BetaProjectbPU");
    EntityManager em = emf.createEntityManager();
    //So that we can have a single transaction after the initialization
    EntityTransaction tr = em.getTransaction();
    //Vital for the parsing to Gson
    private Gson gson = new Gson();
    //*************************************************
    Map<Integer, Person> people = new HashMap();

    @Override
    public String getPersonsAsJSON() {
        return ((people.isEmpty()) ? null : people.values().toString());
    }

    @Override
    public String getPersonAsJson(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person addPersonFromGson(String json) {
        Person p = gson.fromJson(json, Person.class);
        people.put(p.getId(), p);
        System.out.println(p.toString());
        return p;
    }

    @Override
    public RoleSchool addRoleFromGson(String json, long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    private void testingCode(){
        String addingperson = "{ \"firstName\":\"John\", \"lastName\":\"McLaren\", \"mail\":\"j@m.uk\", \"phone\":\"3456\" }";
        addPersonFromGson(addingperson);
        System.out.println("ListOfObjects: "+getPersonsAsJSON());
    }
    
    public static void main(String[] args) {
        
        new Facadelogic().testingCode();
    }
    
}
