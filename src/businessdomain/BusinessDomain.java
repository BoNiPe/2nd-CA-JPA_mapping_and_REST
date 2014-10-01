package businessdomain;

import entity.AssistenTeacher;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entity.Person;
import entity.RoleSchool;
import entity.Student;
import entity.Teacher;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Tomascik
 */
public class BusinessDomain {


    

   public void runDatabase() {

   EntityManagerFactory emf;
   emf = Persistence.createEntityManagerFactory("BetaProjectbPU");
   EntityManager em = emf.createEntityManager();
   EntityTransaction et = em.getTransaction();
   
   Person p1 = new Person("Peter", "Tomascik","9409410","sasd@");
   Person p2 = new Person("Boyko","Mitic","6510651","kjbnji@");
   Person p3 = new Person("Nikolaj","Numsehol","65160651","kjbijno@");
   
   
   
   Teacher t1 = new Teacher ("PCexpert");
   //Teacher t2 = new Teacher ("+50p");
   
   Student st1 = new Student("1stSemester");   
   //Student st2 = new Student("3rdSemester");
   
   AssistenTeacher ast1 = new  AssistenTeacher();
   //AssistenTeacher ast2 = new  AssistenTeacher();
   
   
  
   p1.addRole(t1);
   
 
   p1.addRole(st1);
   
   
   p1.addRole(ast1);
   
   et.begin();
   
   em.persist(p1);
   em.persist(t1);
   
   em.persist(p2);
   em.persist(st1);
   
   em.persist(p3);
   em.persist(ast1);
     
et.commit();
           
        }
    
    
    public static void main(String[] args) {
        new BusinessDomain().runDatabase();
    }
}
