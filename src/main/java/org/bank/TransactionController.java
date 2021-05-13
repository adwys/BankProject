package org.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bank.databaseControllers.QueryExecutor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class TransactionController {

    @FXML
    private TextField ilosc;

    @FXML
    private Button payment;

    @FXML
    private Button payoff;

    @FXML
    private Button goBack;

    private String UPDATE = "UPDATE public.client SET means =%s WHERE \"id\" = %d";




    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        System.out.println(ClientDisplayController.curr_id);
        App.setRoot("secondary");
    }

    @FXML
    private void Wpłać(ActionEvent event) throws IOException, SQLException {
       double value = Float.parseFloat(ilosc.getText());
       int id = ClientDisplayController.curr_id;
       String query = "SELECT * from public.client WHERE \"id\" ="+id;
       ResultSet res = QueryExecutor.executeSelect(query);
       res.next();
        double means = Float.parseFloat(res.getString(5));
       System.out.println(means);
       String forma = String.format(Locale.US,"%.2f",value+means);
       String upd = String.format(UPDATE,forma,id);
       QueryExecutor.executeQuery(upd);
    }

    @FXML
    private void Wypłać(ActionEvent event) throws IOException, SQLException {

        if(!PopUpController.check())return;

        double value = Float.parseFloat(ilosc.getText());
        int id = ClientDisplayController.curr_id;
        String query = "SELECT * from public.client WHERE \"id\" ="+id;
        ResultSet res = QueryExecutor.executeSelect(query);
        res.next();
        double means = Float.parseFloat(res.getString(5));
        System.out.println(means);
        String forma = String.format(Locale.US,"%.2f",means - value);
        String upd = String.format(UPDATE,forma,id);
        QueryExecutor.executeQuery(upd);
    }



}
