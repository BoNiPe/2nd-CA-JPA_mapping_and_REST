/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;
import model.Person;

/**
 *
 * @author Tomascik
 */
public interface IPersonFacade {
    
    public String getPersonsAsJSON();
    public String getPersonAsJson(long id);
    public Person addPersonFromGson(String json);
    
}




/**
 * @author Lars
 */
//public interface IPersonFacade {
//  public Person addPerson(String json);  
//  public Person deletePerson(int id);  
////  public String getPerson(int id);  
////  public String getPersons();  
//  public Person editPerson(String json);  
//}