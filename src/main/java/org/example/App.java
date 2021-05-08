package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws SQLException {
//         QueryExecutor.executeQuery("INSERT INTO public.client(\"name\",\"surname\",\"pesel\",\"means\") VALUES ('adam','wysocki',12322,12)");
//
        ResultSet res = QueryExecutor.executeSelect("SELECT * FROM public.client");
        while (res.next()) {
            System.out.print("Column 1 returned ");
            System.out.println(res.getString(2));
        }
        res.close();
        launch();
    }

}