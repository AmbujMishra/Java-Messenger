package com.KingAm.Messanger;

import java.net.*;
import java.io.*;
 
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
 
import java.awt.EventQueue;
 
@SuppressWarnings("serial")
public class chatServer extends JFrame
{  private Socket          socket   = null;
   private ServerSocket    server   = null;
   private DataInputStream streamIn =  null;
   private JTextArea tracker;
   private static String port;
  
   public chatServer()
   {  try
      { 
         
       //make sure the program exits when the frame closes
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setTitle("Chat Server");
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
       //tracker.append("Entered port no.."+port);
         //chatServer server = null;
          //  if (port == null)
             //  System.out.println("Usage: java ChatServer port");
            //else
             //  server = new chatServer(port);
         //int p= Integer.parseInt(port);
           //System.out.println("Binding to port " + port + ", please wait  ...");
           TrackResponse("Binding to port " + port + ", please wait  ...");
           //tracker.notify();
           //notify();
           //server.notify();
         server = new ServerSocket(Integer.parseInt(port));
         //server.wait();
         //System.out.println("Server started: " + server);
         TrackResponse("Server started: " + server);
         //System.out.println("Waiting for a client ...");
         TrackResponse("Waiting for a client ...");
        // notify();
        // tracker.notify();
         //socket.notify();
         //server.notify();
         //notifyAll();
         //System.out.println(Thread.currentThread());
         //listening to socket
 
         //Thread t= new Thread(server.accept());
         //tracker.notifyAll();
         socket = server.accept();
 
        // socket.wait();
         //socket.notify();
        
         //System.out.println("Client accepted: " + socket);
         TrackResponse("Client accepted: " + socket);
         //tracker.notify();
         //notify();
         open();
         boolean done = false;
         while (!done)
         {  try
            {  String line = streamIn.readUTF();
               //System.out.println(line);
               TrackResponse(line);
               done = line.equals(".bye");
            }
            catch(IOException ioe)
            {  done = true;
            }
         }
         close();
         //notify();
      }
      catch(Exception e)
      {
           System.out.println(e);
           e.printStackTrace();
             TrackResponse(e.toString());
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
   public void open() throws IOException
   {  streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
   }
   public void close() throws IOException
   {  if (socket != null)    socket.close();
      if (streamIn != null)  streamIn.close();
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
                        new chatServer().setVisible(true);         
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
 