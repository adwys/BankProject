package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.example.QueryExecutor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StartingSceneController extends App  {

    @FXML
    private Button displayClients;

    @FXML
    private void DisplayClientsHandler(ActionEvent event) throws IOException {

        System.out.println("xd");
        App.setRoot("secondary");

    }

}
