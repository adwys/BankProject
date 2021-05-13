package org.bank;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bank.Exceptions.ExceptionController;
import org.bank.Exceptions.InvalidUserException;
import org.bank.databaseControllers.QueryExecutor;

import java.io.IOException;

public class AddClientController {

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField address;

    @FXML
    private TextField pesel;

    @FXML
    private TextField means;
    @FXML
    private Button submitButton;

    @FXML
    private void subbmitBtn(ActionEvent event){


        try {
            if(!check_client()) throw new InvalidUserException("user error");
            String query = "INSERT INTO public.client(\"name\",\"surname\",\"ADDRESS        \",\"means\",\"pesel\")" +
                    "VALUES ('" + name.getText() +"', '"+surname.getText()+"', '" +address.getText() +"', "+means.getText() + ", " +
                    pesel.getText() + ")";
            QueryExecutor.executeQuery(query);
            System.out.println(query);

        }catch (Exception e){
            ExceptionController.Invalid_user();
        }


    }

    @FXML
    private void goBackBtn(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    private boolean check_client(){
        if (name.getText() == null)return false;
        if (surname.getText() == null)return false;
        if (pesel.getText() == null || !isNumeric(pesel.getText()))return false;
        if(Integer.parseInt(pesel.getText()) < 1000)return false;
        if(means.getText() == null || !isNumeric(means.getText()))return false;
        return true;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
