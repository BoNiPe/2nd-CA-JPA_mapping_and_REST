package restCRUD;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entity.RoleSchool;
import entity.Teacher;
import exceptions.NotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class HandlerRole implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println("Hello");
        String response = "";
        int status = 200;
        String method = he.getRequestMethod().toUpperCase();
        switch (method) {
            case "POST":
                System.out.println("IN POST");
                try {
                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    if (lastIndex > 0) {
                        String idString = path.substring(lastIndex + 1);
                        int id = Integer.parseInt(idString);
                        response = RestFileServer.facade.getPersonAsJSON(id);
                        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
                        BufferedReader br = new BufferedReader(isr);
                        String jsonQuery = br.readLine();
                        System.out.println("jsonQuery:" + jsonQuery);
                        //Teacher t = new Teacher();
                        //response = new Gson().toJson(t);;
                        RoleSchool r= RestFileServer.facade.addRoleFromJSON(jsonQuery, id);
                        //Person p = facade.addPersonFromJSON(jsonQuery);
                    } else {
                        response = RestFileServer.facade.getPersonsAsJSON();
                    }
                } catch (NumberFormatException nfe) {
                    response = "Id is not a number";
                    status = 404;
                } catch (NotFoundException nfe) //***** WTF??
                {
                    response = nfe.getMessage();
                    status = 404;
                }
                break;

        }
        he.getResponseHeaders().add("Content-Type", "application/json");
        he.sendResponseHeaders(status, 0);
        try (OutputStream os = he.getResponseBody()) {
            os.write(response.getBytes());
        }

    }

}
