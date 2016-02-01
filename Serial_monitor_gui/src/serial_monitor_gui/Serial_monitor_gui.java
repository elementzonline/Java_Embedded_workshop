/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serial_monitor_gui;

import java.io.IOException;
import java.util.TooManyListenersException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author muneer
 */
public class Serial_monitor_gui extends Application {
    
    Stage primerystage;
    SerialCommunication obj1;
    TextArea t_input,t_output;
    ComboBox comport_box;
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
         this.primerystage=primaryStage;
      obj1=  new SerialCommunication(this);
      init_layout();
      obj1.serch_port();
       int len = 0;
     
     
        
     
    }
    public void send(){
        obj1.StringToArrayForSend(t_output.getText().toString());
        t_output.setText("");
       
    
    
    }
    public void init_layout() throws IOException{
//         FXMLLoader loader = new FXMLLoader();
         
         FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXML.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Scene scene = new Scene(pane); 
           primerystage.resizableProperty().setValue(Boolean.FALSE);
            primerystage.setTitle("Serial Monitor");
            primerystage.setScene(scene);
            primerystage.show();
            FXMLController controller = loader.getController();
            controller.setMainApp(this);
            t_input=controller.text1;comport_box=controller.comport_box;
            t_output=controller.text2;
            

    }
    
    public void connect() throws TooManyListenersException{
        
    obj1.connect_ser();
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
