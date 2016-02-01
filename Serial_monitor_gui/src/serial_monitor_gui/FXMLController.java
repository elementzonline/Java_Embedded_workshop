/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serial_monitor_gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TooManyListenersException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author muneer
 */
public class FXMLController implements Initializable {
    Serial_monitor_gui mainApp;
    @FXML
    private Button coonect;
    @FXML
    public ComboBox comport_box;
     @FXML
    public TextArea text1;
     @FXML
    public TextArea text2;
     @FXML
    private Button send;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        // TODO
    }    
    public void button_action() throws TooManyListenersException{
        
        mainApp.connect();
        
    }
     public void button_send() throws TooManyListenersException{
        
        mainApp.send();
        
    }
     public void setMainApp(Serial_monitor_gui mainApp ){
        this.mainApp=mainApp;

    }
}
