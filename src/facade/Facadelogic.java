package facade;

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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
//Facade class (logic) representing the Person table. Created by Boyko. (not everything)

public class Facadelogic implements FacadeInterface {

    //Interface used to interact with the entity manager factory for the persistence unit.
    //Persistence knows where to put the objects and afterwards the Entity Manager
    //takes the whole information, from which it "createsEntity"
    EntityManagerFactory emf = Persistence.createEntityManagerFactory( "privatedb" );
    EntityManager em = emf.createEntityManager();
    //So that we can have a single transaction after the initialization
    EntityTransaction tr;
    //Vital for the parsing to Gson
    private Gson gson = new Gson();

    //Singleton
    private static Facadelogic instance = null;

    protected Facadelogic() {
        // Exists only to defeat instantiation..or with other words - YOLO SWAG
    }

    public static Facadelogic getInstance() {
        if ( instance == null ) {
            instance = new Facadelogic();
        }
        return instance;
    }

    @Override
    public String getPersonsAsJSON() {
        //Makes a query to DB to get all People.
        Query query = em.createQuery( "SELECT a FROM Person a" );
        List<Person> nesquick = ( List<Person> ) query.getResultList();
        return gson.toJson( nesquick );
    }

    @Override
    public String getPersonAsJSON( Integer id ) throws NotFoundException {
        Person a = em.find( Person.class, id );
        //Nick's Exception logic.
        if ( a == null ) {
            throw new NotFoundException( "No person exists for this given ID" );
        }
        //^^
        return ((a == null) ? null : gson.toJson( a ));
    }

    @Override
    public Person addPersonFromJSON( String json ) {
        initializeTransactions();
        tr.begin();
        Person p = gson.fromJson( json, Person.class );
        em.persist( p );
        tr.commit();
        System.out.println( "id:" + p.getId() + ", object:  " + p.toString() );
        return p;
    }

    /**
     * This functions has several small functionalities in it. First, by the
     * given ID from the parameters , it will find the person who's id == the
     * given id, then It will create that object. Then I will parse the jSon
     * string, given from the parameters to an element, which will be generated
     * to Object. From the object we become able to get a particular field, if
     * we know the name of it, then using the value of that field we are
     * creating switch statement, which will add roles depending of the Person's
     * role - Teacher,Student, etc. Persist & commit :)
     */
    @Override
    public RoleSchool addRoleFromJSON( String json, Integer id ) {
        Person a = em.find( Person.class, id );
        JsonElement jelement = new JsonParser().parse( json );
        JsonObject jobject = jelement.getAsJsonObject();
        JsonElement currentrole = jobject.get( "roleName" );
        String roleToString = currentrole.getAsString();
        //System.out.println("Actual role: " + roleToString);
        tr.begin();
        RoleSchool rs;
        switch ( roleToString ) {
            case "Teacher":
                JsonElement currentdegree = jobject.get( "degree" );
                String degreeToString = currentdegree.getAsString();
                rs = new Teacher( degreeToString );
                a.addRole( rs );
                em.persist( rs );
                break;
            case "Student":
                JsonElement currentsemester = jobject.get( "semester" );
                String semesterToString = currentsemester.getAsString();
                rs = new Student( semesterToString );
                a.addRole( rs );
                em.persist( rs );
                break;
            case "TeacherAssistant":
                rs = new TeacherAssistant();
                a.addRole( rs );
                em.persist( rs );
                break;
            default:
                return null;
        }
        tr.commit();
        return rs;

    }

    @Override
    public Person deletePersonFromJSON( Integer id ) throws NotFoundException {
        tr.begin();
        Person a = em.find( Person.class, id );
        if ( a == null ) {
            throw new NotFoundException( "No person exists for this given ID" );
        } else {
            em.remove( a );
        }
        tr.commit();
        return a;
    }

    private void initializeTransactions() {
        tr = em.getTransaction();
    }

//Testing method to test the facade without HTTP Server
//    public static void main(String[] args) {
//
//        new Facadelogic().testingCode();
//    }
    public void testingCode() {
        System.out.println( "Testing" );
        Person person1 = new Person( "Boyko", "Surlev", "boyko.surlev@gmail.com", "52639266" );
        Person person2 = new Person( "Nikolaj", "Desting", "nikolaj.desting@gmail.com", "30579301" );
        Person person3 = new Person( "Peter", "Tomascik", "peter@gmail.com", "69696969" );
        Person person4 = new Person( "Krisko", "Beats", "lora.lora.lora@gmail.com", "2014baby" );
        Person person5 = new Person( "Kilata", "100 Kila", "kilata.maika@gmail.com", "100" );
        Person person6 = new Person( "CphBusiness", "Lyngby", "hi@cphbusiness.dk", "2700" );
        Person person7 = new Person( "Swag", "Boy", "swag.boy94@gmail.com", "1337" );
        Person person8 = new Person( "Trance", "Around", "the.world@gmail.com", "9999999" );
        Person person9 = new Person( "Hallo", "Ven", "friendship@gmail.com", "0123456" );
        Person person10 = new Person( "WE", "LOVE", "music@gmail.com", "484848484" );

        addPersonFromJSON( gson.toJson( person1, Person.class ) );
        addPersonFromJSON( gson.toJson( person2, Person.class ) );
        addPersonFromJSON( gson.toJson( person3, Person.class ) );
        addPersonFromJSON( gson.toJson( person4, Person.class ) );
        addPersonFromJSON( gson.toJson( person5, Person.class ) );
        addPersonFromJSON( gson.toJson( person6, Person.class ) );
        addPersonFromJSON( gson.toJson( person7, Person.class ) );
        addPersonFromJSON( gson.toJson( person8, Person.class ) );
        addPersonFromJSON( gson.toJson( person9, Person.class ) );
        addPersonFromJSON( gson.toJson( person10, Person.class ) );

        Teacher teacher1 = new Teacher( "Doctor" );
        Student student1 = new Student( "3rd Semester" );
        TeacherAssistant teacherassistant1 = new TeacherAssistant();

        addRoleFromJSON( gson.toJson( teacher1, Teacher.class ), 103 );
        addRoleFromJSON( gson.toJson( student1, Student.class ), 104 );
        addRoleFromJSON( gson.toJson( student1, Student.class ), 101 );
        addRoleFromJSON( gson.toJson( student1, Student.class ), 102 );
        addRoleFromJSON( gson.toJson( teacherassistant1, TeacherAssistant.class ), 105 );
        addRoleFromJSON( gson.toJson( teacherassistant1, TeacherAssistant.class ), 102 );

        System.out.println( "List of People: " + getPersonsAsJSON() );

    }

}
