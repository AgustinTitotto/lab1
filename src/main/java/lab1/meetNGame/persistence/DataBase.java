package lab1.meetNGame.persistence;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;

public class DataBase {

    final private static String DB_LOCATION = "/tmp/database";
    private Server server;

    public void startDatabaseServer(){
        HsqlProperties properties = new HsqlProperties();
        properties.setProperty("server.database.0", "file:" + DB_LOCATION + "meetNGame_db;");
        properties.setProperty("server.dbname.0", "xdatabase");
        server = new org.hsqldb.Server();
        try {
            server.setProperties(properties);
        } catch (Exception e) {
            return;
        }
        server.start();
    }

    public void stopDataBaseServer() {
        server.shutdown();
    }

}
