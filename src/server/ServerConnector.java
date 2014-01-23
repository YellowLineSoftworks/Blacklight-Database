package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServerConnector {
    
    public static List<ServerConnector> servers = new ArrayList<>();
    public Socket echoSocket = null;
    public PrintWriter clientOut = null;
    public BufferedReader serverIn = null;
    public boolean connected = false;
    public String gServer;
    public int gPort;
    public String gPrimaryPass;
    public String gDailyPass;
    
    public ServerConnector(String server, int port, String primaryPass, String dailyPass, display.ConnectToServer gui) {
        
        System.out.println("Connecting to server " + server + "...");
        gServer = server;
        gPort = port;
        gPrimaryPass = primaryPass;
        gDailyPass = dailyPass;
        try {
            Thread.sleep(500);
            echoSocket = new Socket(server, port);
            clientOut = new PrintWriter(echoSocket.getOutputStream(), true);
            serverIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            clientOut.println(primaryPass);
            String in = serverIn.readLine();
            if (in.equals("User authenticated!")) {
                System.out.println("Connected to server!\n");
                connected = true;
                servers.add(this);
                gui.setVisible(false);
            }
            
        } catch (IOException e) {
            System.err.println("IOException: Couldn't connect to " + server + " on port " + port);
        } catch (Exception e) {
            System.err.println(e.getMessage() + " - Program could not resolve exception. Sorry about that.");
        }
    }
}