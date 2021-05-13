package org.bank;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private void subbmitBtn(ActionEvent event){ //dziala do exepction do zrobienia
        String query = "INSERT INTO public.client(\"name\",\"surname\",\"ADDRESS        \",\"means\",\"pesel\")" +
                "VALUES ('" + name.getText() +"', '"+surname.getText()+"', '" +address.getText() +"', "+means.getText() + ", " +
                pesel.getText() + ")";
        QueryExecutor.executeQuery(query);
        System.out.println(query);
    }

    @FXML
    private void goBackBtn(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

}
