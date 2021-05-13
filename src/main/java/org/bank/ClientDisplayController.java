package org.bank;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.bank.Exceptions.ExceptionController;
import org.bank.databaseControllers.QueryExecutor;

public class ClientDisplayController extends App implements Initializable {

    private final static URL path = ClientDisplayController.class.getResource("secondary.fxml");
    public static int curr_id;
    @FXML
    private GridPane gridPane;

    @FXML
    private VBox vBox;

    Scene scene;
    public Parent root;
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ResultSet res = QueryExecutor.executeSelect("SELECT * FROM public.client");
            int index=1;

            while (res.next()) {
                if(index>=gridPane.getRowCount()){
                    gridPane.addRow(index);
                }
                insertData(res,index);
                index++;
            }



        }catch (Exception e){
            System.out.println(e);
        }
//        gridPane.setGridLinesVisible(true);
    }

    private void insertData(ResultSet res,int index) throws SQLException {
        Text id = new Text();
        String data = res.getString(1);
        id.setText(data);
        gridPane.add(id,0,index);
        for(int i=1;i<6;i++) {
            Text text = new Text();
            data = res.getString(i+1);
            text.setText(data);

            gridPane.add(text,i,index);
        }

        Button transactionBtn = new Button();
        transactionBtn.setText("tranzakcje");


        transactionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    curr_id = Integer.parseInt(id.getText());
                    System.out.println(curr_id);
                    setRoot("transaction");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        gridPane.add(transactionBtn,6,index);


        Button delBnt = new Button();
        delBnt.setText("usuń");
        delBnt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(!ExceptionController.checkDelete(id.getText()))throw new Exception("delete canceled");

                    QueryExecutor.executeQuery("DELETE FROM public.client\n" +
                            "WHERE id =" + id.getText());
                    try {
                        App.setRoot("secondary");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    System.out.println(e);
                }

            }
        });
        gridPane.add(delBnt,7,index);
    }

//    public static void displayText(Stage stage) throws IOException, SQLException {
//
//        VBox pane = new VBox();
//        ResultSet res = QueryExecutor.executeSelect("SELECT * FROM public.client");
//        Text header = new Text();
//        String headerText = "#  imie   nazwisko   adres   srodki   pesel";
//        header.setText(headerText);
//
//        pane.getChildren().add(header);
//        while (res.next()) {
//            Text text = new Text();
//            String data = res.getString(1)+ " " + res.getString(2) + " " + " " + res.getString(3)+ " " + res.getString(4)+ " " + res.getString(5)+ " " + res.getString(6);
////            data =String.format("%43s", data);
//            text.setText(data);
//            pane.getChildren().add(text);
//        }
//        res.close();
//        stage.setScene(new Scene(pane,640,480));
//        stage.show();
//    }
}