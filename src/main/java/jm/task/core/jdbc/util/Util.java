package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private Util() {

    }


    private static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream in = Util.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void getConnection() {
        Properties prop = getProperties();
        try (Connection conn = DriverManager.getConnection(prop.getProperty("db.url"),
                prop.getProperty("db.username"),
                prop.getProperty("db.password"))){
            if(conn != null){
                System.out.println("connection");
            }else {
                System.out.println("connection failed ");
            }

        } catch (SQLException exp){
            System.err.format("SQL State: %s\n%s", exp.getSQLState(), exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
        }
    }
}



