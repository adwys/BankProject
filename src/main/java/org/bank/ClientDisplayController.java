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
import org.bank.databaseControllers.GetSelect;
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
        GetSelect getSelect = new GetSelect(gridPane);
        getSelect.select(QueryExecutor.executeSelect("SELECT * FROM public.client"));
    }

}