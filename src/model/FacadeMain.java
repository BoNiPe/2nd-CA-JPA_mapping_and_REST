/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.Gson;
import entity.Person;
import entity.RoleSchool;
import exceptions.NotFoundException;
import facade.IPersonFacade;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tomascik
 */
public class FacadeMain implements IPersonFacade{
    
  Map<Integer,Person> persons = new HashMap();
  private int nextId;
  private final Gson gson = new Gson();
  private static FacadeMain instance = new FacadeMain();   //<- Not sure what it does
  
   private FacadeMain() { // <- Not sure what it does3
  }

    @Override
    public String getPersonsAsJSON() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPersonAsJson(long id) throws NotFoundException // <- in case of Exceptions
    {
        Person p = persons.get(id);
        if (p == null) {
            throw new NotFoundException("No person exists for the given id");
        }
    return gson.toJson(p);
    }

    @Override
    public FacadeMain addPersonFromGson(String json) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoleSchool addRoleFromGson(String json, long id) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FacadeMain delete(long id) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
