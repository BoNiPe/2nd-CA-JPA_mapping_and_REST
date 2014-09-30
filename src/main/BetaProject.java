
package main;

import entity.Person;
import entity.SchoolRole;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BetaProject {

    public static void main(String[] args) {
        new BetaProject().startLogic();
    }
    
    private void startLogic(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BetaProjectbPU");
        EntityManager em = emf.createEntityManager();
        Person p1 = new Person("Boyko", "Surlev", "52639266", "boyko.surlev@gmail.com");
        Person p2 = new Person("Nikolaj","Desting","30579301", "yourmomishot.bullshit@iRock.me");
        EntityTransaction tr = em.getTransaction();
        
        tr.begin();
        em.persist(p1);
        em.persist(p2);
        tr.commit();
        System.out.println("****************************************");
        SchoolRole sr1 = new SchoolRole("Teacher");
        p1.addRole(sr1);
              
        tr.begin();
        em.persist(p1);
        em.persist(sr1);
        tr.commit();      
        
        Person test = em.find(Person.class, p1.getId());
        System.out.println("FIND MY TABLES >>>>>> "+ test.toString());
        System.out.println("FIND MY TABLES2222222222 >>>>>>"+test.checkRoles().toString());
        
    }
    
}
