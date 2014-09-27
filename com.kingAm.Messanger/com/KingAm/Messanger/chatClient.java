package com.KingAm.Messanger;

import java.net.*;
import java.awt.EventQueue;
import java.io.*;
 
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
 
public class chatClient extends JFrame
{  /**
                *
                 */
                private static final long serialVersionUID = 1L;
private Socket socket              = null;
   private DataInputStream  console   = null;
   private DataOutputStream streamOut = null;
   private JTextArea tracker;
   private static String address;
   private static String port;
 
   public chatClient()
   { 
                 //  chatClient client = new chatClient(args[0], Integer.parseInt(args[1]));
                  
                   //System.out.println("Establishing connection. Please wait ...");
                  
      try
      { 
                //make sure the program exits when the frame closes
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setTitle("Chat Client");
          setSize(500,300);
         
          //This will center the JFrame in the middle of the screen
          setLocationRelativeTo(null);
          //Using JTextArea to show clicks and responses
          tracker = new JTextArea("Processing Started:");
          add(tracker);
          setVisible(true);    
         
        //Input dialog with a text field
          port =  JOptionPane.showInputDialog(this,"Enter 4 digit port no:");
          TrackResponse("Entered port no.."+port);
        //Input dialog with a text field
          address=JOptionPane.showInputDialog(this,"Enter server address:");
          TrackResponse("Entered server address.."+address);
          TrackResponse("Establishing connection. Please wait ...");
         
                 socket = new Socket(address, Integer.parseInt(port));
         //System.out.println("Connected: " + socket);
                 TrackResponse("Connected: " + socket);
         start();
      }
      catch(UnknownHostException uhe)
      {  System.out.println("Host unknown: " + uhe.getMessage());
      }
      catch(IOException ioe)
      {  System.out.println("Unexpected exception: " + ioe.getMessage());
      }
      String line = "";
      while (!line.equals(".bye"))
      {  try
         { 
                  //line = console.readLine();
       //Input dialog with a text field
         line=JOptionPane.showInputDialog(this,"Enter chat Text:");
         TrackResponse(line);
            streamOut.writeUTF(line);
            streamOut.flush();
         }
         catch(IOException ioe)
         {  System.out.println("Sending error: " + ioe.getMessage());
         }
      }
   }
   public void start() throws IOException
   { // console   = new DataInputStream(System.in);
      streamOut = new DataOutputStream(socket.getOutputStream());
   }
   public void stop()
   {  try
      {  if (console   != null)  console.close();
         if (streamOut != null)  streamOut.close();
         if (socket    != null)  socket.close();
         TrackResponse("Thanks for using");
      }
      catch(IOException ioe)
      {  System.out.println("Error closing ...");
      }
   }
   public void TrackResponse(String response)
   {
                 
       //showInputDialog method returns null if the dialog is exited
       //without an option being chosen
       if (response == null)
       {
           tracker.append("\nYou closed the dialog without any input..");
       }
       else
       {
           tracker.append("\n"+response + "..");
       }
   }
   public static void main(String args[])
   { 
                   try
                                {
                                                EventQueue.invokeLater(new Runnable()
                                    {
                                        public void run()
                                        {
                                            //create GUI frame
                                            new chatClient().setVisible(true);         
                                        }
                                    });
                                               
                                                //FileOperations();
                                               
                                }
                                catch (Exception e)
                                {
                                                e.printStackTrace();
                                }
   }
  
}