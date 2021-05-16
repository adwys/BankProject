package org.bank;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.bank.Exceptions.ExceptionController;
import org.bank.databaseControllers.GetSelect;
import org.bank.databaseControllers.QueryExecutor;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SelectDisplayController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField textField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.setValue("id");
        choiceBox.getItems().add("id");
        choiceBox.getItems().add("Imie");
        choiceBox.getItems().add("Nazwisko");
        choiceBox.getItems().add("adres");
        choiceBox.getItems().add("pesel");


        textField.setText("0");
        gridPane.setVisible(false);


    }


    @FXML
    private void find(){

        try {
            String val = choiceBox.getValue();
            val = to_eng(val);
            if(textField.getText().equals(""))throw new SQLException("no entry data");
            String query = "SELECT * FROM public.client WHERE "+ val+ "= '" + textField.getText()+"'";
            if(!query_check())throw new Exception();
            System.out.println(query);


            GetSelect getSelect = new GetSelect(gridPane);

            ResultSet res = QueryExecutor.executeSelect(query);
            if(!res.next())throw new SQLException("no data");
            getSelect.select(QueryExecutor.executeSelect(query));
            gridPane.setVisible(true);
        }catch (Exception e){
            ExceptionController.No_Data();
            gridPane.setVisible(false);
        }

    }

    private String to_eng(String val){
        if(val.equals("Imie"))return "name";
        if(val.equals("Nazwisko"))return "surname";
        if(val.equals("adres"))return "address";
        return val;
    }

    private boolean query_check(){
        if(choiceBox.getValue().equals("id") || choiceBox.getValue().equals("pesel")){
            try {
                Integer.parseInt(textField.getText());
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return true;
    }

    @FXML
    private void go_back() throws IOException {
        App.setRoot("primary");
    }

}
