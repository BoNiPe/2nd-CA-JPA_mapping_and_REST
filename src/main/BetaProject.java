package main;

import entity.Person;
import entity.Student;
import entity.Teacher;
import entity.TeacherAssistant;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BetaProject {
//Testing class to test the local database without Facade and HTTP Server

//    public static void main(String[] args) {
//        new BetaProject().startLogic();
//    }
    private void startLogic() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BetaProjectbPU");
        EntityManager em = emf.createEntityManager();
        Person p1 = new Person("Boyko", "Surlev", "52639266", "boyko.surlev@gmail.com");
        Person p2 = new Person("Nikolaj", "Desting", "30579301", "nikolaj.desting@gmail.com");
        Person p3 = new Person("Peter", "Tomashchik", "00000", "yoloswag");
        EntityTransaction tr = em.getTransaction();
        System.out.println("****************************************");
        tr.begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        tr.commit();
        System.out.println("****************************************");
        Teacher t1 = new Teacher("Doctor");
        Student s1 = new Student("3rd Semester");
        TeacherAssistant tal1 = new TeacherAssistant();
        p1.addRole(t1);
        p2.addRole(s1);
        p1.addRole(tal1);
        tr.begin();
        em.persist(t1);
        em.persist(s1);
        em.persist(tal1);
        tr.commit();
        System.out.println("****************************************");
        Person test = em.find(Person.class, p1.getId());
        System.out.println("FIND MY TABLES >>>>>> " + test.toString());
        System.out.println("My size>>>>>>>.." + test.checkRoles());
    }
}
