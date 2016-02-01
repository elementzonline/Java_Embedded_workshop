/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serial_read;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import javaapplication11.SerialCommunication.SerialReader;


/**
 *
 * @author muneer
 */
public class Serial_Read {
    
 static  SerialPort   serialPort;
 static  private InputStream in;
 static  private OutputStream out ;
 static SerialReader input;
   
   


//SerialCommunication ob1=new SerialCommunication();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connect();
        // ob1.connect_ser();
       
      
       
        // TODO code application logic here
   }
    
     public static void connect(){
           try {
             CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM6");
              CommPort commPort = portIdentifier.open("",2000);
               if ( commPort instanceof SerialPort )
            {
                serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                in = serialPort.getInputStream();
                out = serialPort.getOutputStream();
                input=new SerialReader(in);
                serialPort.addEventListener(input);
                serialPort.notifyOnDataAvailable(true);
                
                
               //StringToArrayForSend("sdsfdsfsdfdsfsdf");
                
//              (new Thread(new SerialReader(in))).start();
               // (new Thread(new SerialWriter(out))).start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
            
        } catch (Exception e) {
        }
        
       
        
        
        
        }
    
    
    
    
     public  static class SerialReader implements SerialPortEventListener 
    {
        private InputStream in;
        private byte[] buffer = new byte[1024];
        String buffer_string;
      //  SerialCommunication serial_;
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
                   // System.out.println(data);
                    if ( data == 13 ) {
                         buffer_string = new String(buffer,0,len);
                         System.out.print(buffer_string);
                       //  setReturnvalue(buffer_string); 
                         
                        break;
                    }
                    buffer[len++] = (byte) data;
                 // buffer_string = new String( buffer,0,len);
                    
                   //  System.out.print(buffer_string);
                }
                //System.out.print(new String(buffer,0,len));
               
               
                
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                System.exit(-1);
            }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
}
