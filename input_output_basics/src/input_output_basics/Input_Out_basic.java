package input_output_basics;

import java.util.Scanner;

public class Input_Out_basic
{

  public static void main (String[] args) throws InterruptedException
  {
      
   
    // create a scanner so we can read the command-line input
    Scanner scanner = new Scanner(System.in);
    
    while(true){
       
        String username = scanner.next();
        
        System.out.println(username);
        Thread.sleep(1000);
    
    
    }

    //  prompt for the user's name
//    System.out.print("Enter your name: ");
//
//    // get their input as a String
//    String username = scanner.next();
//
//    // prompt for their age
//    System.out.print("Enter your age: ");
//
//    // get the age as an int
//    int age = scanner.nextInt();
//
//    System.out.println(String.format("%s, your age is %d", username, age));

  }

}