package facade;

import entity.Person;
import entity.RoleSchool;

public interface FacadeInterface {

    public String getPersonsAsJSON(); //Return all persons

    public String getPersonAsJson(long id); //Return a single person

    public Person addPersonFromGson(String json); //Create a Person given JSON

    public RoleSchool addRoleFromGson(String json, long id); //Add a role to a Person given JSON

    public Person delete(long id); //Delete the Person with the given id

}
