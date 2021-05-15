package org.bank.databaseControllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.bank.App;
import org.bank.Exceptions.ExceptionController;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetSelect {

    private GridPane gridPane;
    private int curr_id;
    public GetSelect(GridPane gridPane) {
        this.gridPane = gridPane;
    }



    public void select(ResultSet res){
        try {

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
                    App.setRoot("transaction");
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
}
