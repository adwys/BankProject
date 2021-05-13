package org.bank;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bank.Exceptions.ExceptionController;
import org.bank.Exceptions.InvalidTransferAmount;
import org.bank.databaseControllers.QueryExecutor;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {

    @FXML
    private TextField ilosc;

    @FXML
    private Button payment;

    @FXML
    private Button payoff;

    @FXML
    private Button goBack;

    private String UPDATE = "UPDATE public.client SET means =%s WHERE \"id\" = %d";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ilosc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    ilosc.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }



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

        App.setRoot("secondary");
    }

    @FXML
    private void Wypłać(ActionEvent event) throws IOException, SQLException {
        try {
            if (!ExceptionController.check()) return;

            double value = Float.parseFloat(ilosc.getText());
            int id = ClientDisplayController.curr_id;
            String query = "SELECT * from public.client WHERE \"id\" =" + id;
            ResultSet res = QueryExecutor.executeSelect(query);
            res.next();
            double means = Float.parseFloat(res.getString(5));
            System.out.println(means);

            if (means - value < 0) throw new InvalidTransferAmount("transfer error");

            String forma = String.format(Locale.US, "%.2f", means - value);
            String upd = String.format(UPDATE, forma, id);
            QueryExecutor.executeQuery(upd);
        }catch (Exception e){
            ExceptionController.Invalid_payment();
        }
        App.setRoot("secondary");
    }




}
