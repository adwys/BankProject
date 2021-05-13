package org.bank.Exceptions;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ExceptionController {

    public static boolean check(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Zatwierdzić zmiany?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDelete(String id){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Czy na pewno chesz usunąć Klienta o id " +id);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }

    public static void Invalid_user(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("New Client ERROR");
        alert.setHeaderText("Nie poprawny format Klienta");
        alert.showAndWait();
    }

    public static void Invalid_payment(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Payment ERROR");
        alert.setHeaderText("Brak wystarczających środków");
        alert.showAndWait();
    }


}

