package org.bank;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.bank.Exceptions.ExceptionController;
import org.bank.databaseControllers.GetSelect;
import org.bank.databaseControllers.QueryExecutor;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TransferController implements Initializable {

    @FXML
    private ChoiceBox<String> sender;

    @FXML
    private ChoiceBox<String> receiver;

    @FXML
    private TextField textField;

    @FXML
    private Button submitBnt;

    private int receiver_id;
    private int sender_id;
    private String UPDATE = "UPDATE public.client SET means =%s WHERE \"id\" = %d";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            submitBnt.setVisible(false);
            textField.setText("0");
            ResultSet res = QueryExecutor.executeSelect("SELECT * from public.client");
            if(!res.next()){
                sender.getItems().add("brak Klientów");
                receiver.getItems().add("brak Klientów");
                sender.setValue("brak Klientów");
                receiver.setValue("brak Klientów");
            }
            GetSelect senderSelect = new GetSelect(sender);
            senderSelect.ChoiceBoxSelect();
            GetSelect receiverSelect = new GetSelect(receiver);
            receiverSelect.ChoiceBoxSelect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        sender.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if(receiver.getValue() != null){
                    submitBnt.setVisible(true);
                }
            }
        });

        receiver.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if(sender.getValue() != null){
                    submitBnt.setVisible(true);
                }
            }
        });

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    @FXML
    private void transfer() throws SQLException {
        try {
            String s = sender.getValue();
            sender_id= Integer.parseInt(s.split("[^\\d]")[0]);
            String r = receiver.getValue();
            receiver_id= Integer.parseInt(r.split("[^\\d]")[0]);
            System.out.println(sender_id + " " + receiver_id);
        }catch (Exception e){
            System.out.println("transfer exception "+e);
        }
        if(sender_id == receiver_id){
            ExceptionController.Same_id();
            return;
        }
        if(textField.getText().equals("")){
            ExceptionController.empty_textField();
            return;
        }
        double sum = Double.parseDouble(textField.getText());
        ResultSet res = QueryExecutor.executeSelect("SELECT * FROM public.client WHERE id="+sender_id);
        res.next();
        double sender_money = Double.parseDouble(res.getString(5));
        res = QueryExecutor.executeSelect("SELECT * FROM public.client WHERE id="+receiver_id);
        res.next();
        double receiver_money = Double.parseDouble(res.getString(5));
        if(sender_money<sum){
            ExceptionController.Invalid_payment();
            return;
        }
        if(!ExceptionController.check())return;
        String upd_sender = String.format(UPDATE,sender_money-sum,sender_id);
        System.out.println(upd_sender);
        QueryExecutor.executeQuery(upd_sender);
        String upd_receiver = String.format(UPDATE,receiver_money+sum,receiver_id);
        System.out.println(upd_receiver);
        QueryExecutor.executeQuery(upd_receiver);

    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

}
