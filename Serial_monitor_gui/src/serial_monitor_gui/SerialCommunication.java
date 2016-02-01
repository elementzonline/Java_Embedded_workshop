package serial_monitor_gui;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

public class SerialCommunication
{
    private InputStream in;
    private OutputStream out ;
   public SerialPort serialPort;
   SerialReader input;
    private HashMap portMap = new HashMap();
    private Enumeration ports = null;
    private boolean bConnected = false;
    Serial_monitor_gui mainApp;
    public SerialCommunication(Serial_monitor_gui mainApp)
    {
//        super();
        this.mainApp=mainApp;
        
    }
    public void serch_port(){
                 ports = CommPortIdentifier.getPortIdentifiers();
              //   mainApp.loca.setText("hi111");
                 while(ports.hasMoreElements()){
                      CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();
                      if(curPort.getPortType() == CommPortIdentifier.PORT_SERIAL){
                         mainApp.comport_box.getItems().addAll(curPort.getName());
                         mainApp.comport_box.setValue(curPort.getName());
                        // portMap.put(curPort.getName(), curPort);
                       //   mainApp.comport_box.getItems().addAll("j","k");
                         //  mainApp.comport_box
                      // mainApp.comport_box.addItem(curPort.getName());
               // portMap.put(curPort.getName(), curPort);
                      
                      }
                      
                 
                 
                 }
    
    
    }
    
    public void connect_ser () throws TooManyListenersException 
            
    {
        try {
            String portName=mainApp.comport_box.getValue().toString();
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
//         if ( portIdentifier.isCurrentlyOwned() )
//        {
//            System.out.println("Error: Port is currently in use");
//        }
        
        
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            
            if ( commPort instanceof SerialPort )
            {
                serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                in = serialPort.getInputStream();
                  out = serialPort.getOutputStream();
                input=new SerialReader(in);
               serialPort.addEventListener(input);
                serialPort.notifyOnDataAvailable(true);
                setbConnected(true);
               //StringToArrayForSend("sdsfdsfsdfdsfsdf");
                
//              (new Thread(new SerialReader(in))).start();
               // (new Thread(new SerialWriter(out))).start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        
            
        } catch (NoSuchPortException e) {
            System.out.println("No such port");
             setbConnected(false);
        }
        catch (PortInUseException e) {
            System.out.println("Port is Already in Use");
            setbConnected(false);
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.initOwner(mainApp.primerystage);
             alert.setTitle("Port in Use");
             alert.setHeaderText("Port is Already in Use");
            

        alert.showAndWait();
        }
        catch (UnsupportedCommOperationException e) {
            
            System.out.println("Unsupported port");
             setbConnected(false);
        }
         catch (IOException e) {
              System.out.println("input/output problem");
        }
        
           
    }
    public void disconnect_ser(){
    
           
        try {
            serialPort.close();
            in.close();
            out.close();
            setbConnected(false);
        } catch (IOException ex) {
            Logger.getLogger(SerialCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }

    public boolean isbConnected() {
        return bConnected;
    }

  

    public void setbConnected(boolean bConnected) {
        this.bConnected = bConnected;
    }
    
    public void writedata(int data){
     
        try {
            out.write(data);
        } catch (IOException e) {
            
            System.out.println("Please Connect to port");
        }
    
    
    }
     public void StringToArrayForSend(String str){
    
    char arr[]=str.toCharArray();
    for(int i=0;i<str.length();i++){
    int ascii= (int)arr[i];
    writedata(ascii); 
    } 
    }

    /** */
    public  class SerialReader implements SerialPortEventListener 
    {
        private InputStream in;
        private byte[] buffer = new byte[1024];
        String buffer_string;
        SerialCommunication serial_;
        public SerialReader ( InputStream in)
        {
            this.in = in;
           // this.serial_=serial_;
        }

        @Override
        public void serialEvent(SerialPortEvent spe) {
            int data;
             try
            {
                int len = 0;
                while ( ( data = in.read()) > -1 )
                {
                    if ( data == 13 ) {
                         buffer_string = new String(buffer,0,len);
                         System.out.print(buffer_string);
                           
                       //  setReturnvalue(buffer_string); 
                         
                        break;
                    }
                    buffer[len++] = (byte) data;
                    mainApp.t_input.setText(buffer_string);
                }
                System.out.print(new String(buffer,0,len));
              
               
               
                
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                System.exit(-1);
            }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
     
//       public  class SerialReader implements Runnable 
//    {
//        InputStream in;
//        String buffer_string;
//        
//        public SerialReader ( InputStream in )
//        {
//            this.in = in;
//        }
//        
//        public void run ()
//        {
//            byte[] buffer = new byte[1024];
//            int data ;
//            try
//            {
//                int len=0;
//                while ( ( data = this.in.read()) > -1 )
//                {
//                    
//                     if ( data == 13 ) {
//                         buffer_string = new String(buffer,0,len);
//                         System.out.print(buffer_string);
//                         setReturnvalue(buffer_string); 
//                         len=0;
//                        break;
//                    }
//                    buffer[len++] = (byte)data;
//                    
//                    System.out.print(new String(buffer,0,len));
//                }
//            }
//            catch ( IOException e )
//            {
//                e.printStackTrace();
//            }            
//        }
//    }

    /** */
//    public static class SerialWriter implements Runnable 
//    {
//        OutputStream out;
//        
//        public SerialWriter ( OutputStream out )
//        {
//            this.out = out;
//        }
//        
//        public void run ()
//        {
//            try
//            {                
//                int c = 0;
//                while ( ( c = System.in.read()) > -1 )
//                {
//                    this.out.write(c);
//                }                
//            }
//            catch ( IOException e )
//            {
//                e.printStackTrace();
//            }            
//        }
//    }
    
//    public static void main ( String[] args )
//    {
//        try
//        {
//            (new TwoWaySerialComm()).connect("COM3");
//        }
//        catch ( Exception e )
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}

