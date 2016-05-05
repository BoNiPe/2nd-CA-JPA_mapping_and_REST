//Nick's and Peter's logic
package restCRUD;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entity.Person;
import exceptions.NotFoundException;
import facade.Facadelogic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author Desting
 */
public class HandlerPerson implements HttpHandler {

        Facadelogic facade;

    public HandlerPerson() throws NotFoundException {
        facade = Facadelogic.getFacade();
        if (RestFileServer.DEVELOPMENT_MODE) {
        facade.testingCode();
      }
    }
        @Override
        public void handle(HttpExchange he) throws IOException {
            String response = "";
            int status = 200;
            String method = he.getRequestMethod().toUpperCase();
            switch (method)
            {
                case "GET":
                    try
                    {
                        String path = he.getRequestURI().getPath();
                        int lastIndex = path.lastIndexOf("/");
                        if (lastIndex > 0)
                        {
                            String idString = path.substring(lastIndex + 1);

                            int id = Integer.parseInt(idString);
                            response = facade.getPersonAsJSON(id);
                        } else
                        {
                            response = facade.getPersonsAsJSON();
                        }
                    } catch (NumberFormatException nfe)
                    {
                        response = "Id is not a number";
                        status = 404;
                    } catch (NotFoundException nfe) //***** WTF??
                    {
                        response = nfe.getMessage();
                        status = 404;
                    }
                    break;
                case "POST":
                    InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    String jsonQuery = br.readLine();
                    Person p = facade.addPersonFromJSON(jsonQuery);
                    response = new Gson().toJson(p);
                    
                    
                    break;
                case "PUT":
                    break;
                case "DELETE":
                    try{
          String path = he.getRequestURI().getPath();
          int lastIndex = path.lastIndexOf("/");
          if (lastIndex > 0) {  //person/id
            int id = Integer.parseInt(path.substring(lastIndex+1));
            Person pDeleted = facade.deletePersonFromJSON(id);
            response = new Gson().toJson(pDeleted);
          }
          else{
            status = 400;
            response = "<h1>Bad Request</h1>No id supplied with request";
          }
          }catch(NotFoundException nfe){
            status = 404;
            response = nfe.getMessage();
          }
          catch (NumberFormatException nfe) {
            response = "Id is not a number";
            status = 404;
          } 
          break;
            }
            he.getResponseHeaders().add("Content-Type", "application/json");
            he.sendResponseHeaders(status, 0);
            try (OutputStream os = he.getResponseBody())
            {
                os.write(response.getBytes());
            }
        }
    }