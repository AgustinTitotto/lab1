package lab1.meetNGame;

import lab1.meetNGame.persistence.DataBase;
import lab1.meetNGame.persistence.EntityManagers;
import spark.Spark;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class WebApp {

    private final WebRoutes webRoutes = new WebRoutes();
    private final DataBase dataserver = new DataBase();

    public void start(){
        //startDatabase();
        startWebServer();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("monolithic-db");
        EntityManagers.setFactory(entityManagerFactory);

    }

    private void startDatabase() {
        dataserver.startDatabaseServer();
    }

    private void startWebServer() {
        staticFiles.location("public");
        port(4335);
        webRoutes.startRoutes();
    }

    /*private void stopDatabase() {
        dataserver.stopDataBaseServer();
    }

    private void stopWebServer() {
        Spark.stop();
    }*/
}
