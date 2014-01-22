package com.maximilian_boehm.com.tcp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class Server {

    public static void main(String[] args) {
       Server server = new Server();
       server.start();
    }

    private void start() {
       final int RSAKeySize = 1024;
       final String newline = "\n";

       Key pubKey = null;
       PrintWriter cOut = null;

       // Initialise RSA
       try{
           KeyPairGenerator RSAKeyGen = KeyPairGenerator.getInstance("RSA");
           RSAKeyGen.initialize(RSAKeySize);
           KeyPair pair = RSAKeyGen.generateKeyPair();
           pubKey = pair.getPublic();
       } catch (GeneralSecurityException e) {
           System.out.println(e.getLocalizedMessage() + newline);
           System.out.println("Error initialising encryption. Exiting.\n");
           System.exit(0);
       }

       // Initialise socket connection
       while(true){
          try(ServerSocket cServer = new ServerSocket(Codes.Port)){
              Socket cClient = cServer.accept();
              
//              BufferedReader input = new BufferedReader(new InputStreamReader(cClient.getInputStream()));
//              input.readLine();
              
              cOut = new PrintWriter(cClient.getOutputStream(), true);
              cOut.print(Codes.STATUS_RSA_KEY+",");
              cOut.println(pubKey.getEncoded().length);
              cClient.getOutputStream().write(pubKey.getEncoded());
              cClient.getOutputStream().flush();
          } catch (IOException e) {
             e.printStackTrace();
          }
       }
    }
    
}