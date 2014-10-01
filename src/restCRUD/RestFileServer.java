//Nick's and Peter's logic
package restCRUD;

import com.sun.net.httpserver.HttpServer;
import exceptions.NotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;

public class RestFileServer {

    static int port = 8084;
    static String ip = "127.0.0.1";
    static String publicFolder = "src/html/";
    static String startFile = "index.html";
    static String filesUri = "/pages";
    static final boolean DEVELOPMENT_MODE = true;

    public void run() throws IOException, NotFoundException {
        HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        //REST Routes
        server.createContext("/person", new HandlerPerson());

        //HTTP Server Routes
        server.createContext(filesUri, new HandlerFileServer());

        server.start();
        System.out.println("Server started, listening on port: " + port);
    }

    public static void main(String[] args) throws IOException, NotFoundException {
        if (args.length >= 3) {
            port = Integer.parseInt(args[0]);
            ip = args[1];
            publicFolder = args[2];
        }
        new RestFileServer().run();
    }
}
