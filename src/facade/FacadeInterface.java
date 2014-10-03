package facade;

import entity.Person;
import entity.RoleSchool;
import exceptions.NotFoundException;
//Inteface of Facade - most of the names of those methods are changed, compared 
//with the teachers examples, because the names were kinda non-sense to us. Hope
//you like 'em :P

public interface FacadeInterface {

    public String getPersonsAsJSON(); //Return all persons

    public String getPersonAsJSON(Integer id) throws NotFoundException; //Return 
//a single person

    public Person addPersonFromJSON(String json); //Create a Person given JSON

    public RoleSchool addRoleFromJSON(String json, Integer id); //Add a role to 
//a Person given JSON

    public Person deletePersonFromJSON(Integer id) throws NotFoundException;
//Delete the Person with the given id

}
