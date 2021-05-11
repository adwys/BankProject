package org.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StartingSceneController extends App  {

    @FXML
    private Button displayClients;

    @FXML
    private void DisplayClientsHandler(ActionEvent event) throws IOException {
        System.out.println("diss");
        App.setRoot("secondary");
    }

    @FXML
    private void DisplayAddClient(ActionEvent event) throws IOException{
        System.out.println("add");
        App.setRoot("addClient");
    }



}
