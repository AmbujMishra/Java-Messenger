Java-Messenger
==============

Messenger client server


An instant messenger service in java using sockets and threads.
One device will create a server. Other devices can connect to server.

SDK:Eclipse
UI: Jave Swing
Status: Started

Phase 1: One way Messenger

chatServer Class: 
1.Taking input from user:Port no.
2.Default server address: 0.0.0.0
3.Create a local server by opening a ServerSocket on given port. //ServerSocket server=new ServerSocket(Integer.parseInt(port));
4.Listen to socket for accepting connection from client. //Socket socket = server.accept();
5. Open an DataInputStream as soon as server get the connection from client to read input from chatClient.  //  DataInputStream streamIn =new DataInputStream(new BufferedInputStream(socket.getInputStream()));
6. Close the connection when we get ".bye" from chatClient.


chatClient Class:

1. Connect to open socket in chatServer class using same port and default address as 0.0.0.0. // Socket socket = new Socket(address, Integer.parseInt(port));
2. Open an DataOutputStream to send text to chatServer. //  DataOutputStream streamOut= new DataOutputStream(socket.getOutputStream());
3.Enter text ".bye" to close the communication and connection.

