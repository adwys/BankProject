package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbController {


    public static Connection connect(){
        final String URL = "jdbc:postgresql://localhost/bank";
        final String user = "postgres";
        final String password = "root";
        Connection connection = null;
        Connection connect = null;
        try {
            connect =DriverManager.getConnection(URL, user, password);
        }catch (Exception e){
            System.out.println(e);
        }
        return connect;
    }

}
