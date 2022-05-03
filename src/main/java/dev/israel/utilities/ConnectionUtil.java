package dev.israel.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){

        try{
            String databaseString = System.getenv("PROJECT_1_DB");
            Connection conn = DriverManager.getConnection(databaseString);
            return conn;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }



    }
}
