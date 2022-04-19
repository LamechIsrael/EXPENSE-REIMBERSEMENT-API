package dev.israel.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){

        try{
            Connection conn = DriverManager.getConnection("jdbc:postgresql://israel-db.cazcdjmud22d.us-west-1.rds.amazonaws.com/project1db?user=postgres&password=shadow17");
            return conn;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }



    }
}
