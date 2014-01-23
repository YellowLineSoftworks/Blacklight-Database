package server;

import server.ServerGUI;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    
   public Socket sock;
   public ServerSocket serverSock;
   public String name;
   public PrintWriter clientOut;
   public BufferedReader clientIn;
   public ServerGUI guim;
   public int level;
   public static int counter = 0;
   
   public void connect(ServerSocket serverSock, int listenPort, ServerGUI gui) throws Exception {
      
      guim = gui;
      gui.jTextArea1.append("Listening on port " + listenPort + "\n");
      sock = serverSock.accept();
      gui.jTextArea1.append("New connection on " + sock + "\n");
      clientOut = new PrintWriter(sock.getOutputStream(), true);
      clientIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
      name = clientIn.readLine();
      gui.jTextArea1.append("Client name: " + name + "\n");
      
   }
   
}