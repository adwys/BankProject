package org.bank.databaseControllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryExecutor {

    public static ResultSet executeSelect(String Selectquery){

        try{
            Connection connection = dbController.connect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(Selectquery);
        }catch (Exception e){
            System.out.println(e);
            System.exit(0);
        }
        return null;
    }

    public static void executeQuery(String query){

        try{
            Connection connection = dbController.connect();
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
