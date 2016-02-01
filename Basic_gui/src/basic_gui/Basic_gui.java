/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic_gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author muneer
 */
public class Basic_gui extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML.fxml"));
        AnchorPane pane=(AnchorPane)loader.load();
        Scene scene = new Scene(pane); 
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setTitle("Bulk Messenger");
        primaryStage.setScene(scene);
        primaryStage.show();
      
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("FXML.fxml"));
//            AnchorPane pane = (AnchorPane) loader.load();
//            Scene scene = new Scene(pane); 
//            primaryStage.resizableProperty().setValue(Boolean.FALSE);
//            primaryStage.setTitle("Bulk Messenger");
//            primaryStage.setScene(scene);
//            primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
