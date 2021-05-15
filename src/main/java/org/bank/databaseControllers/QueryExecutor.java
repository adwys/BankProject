package org.bank.databaseControllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.bank.App;
import org.bank.Exceptions.ExceptionController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
