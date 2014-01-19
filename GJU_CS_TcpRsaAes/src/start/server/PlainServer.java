package start.server;
import java.io.*;
import java.net.*;

public class PlainServer{
   
   public static void main(String argv[]) throws Exception{
         String clientSentence;
         String capitalizedSentence;

         try (ServerSocket welcomeSocket = new ServerSocket(6789);){
            while(true){
               Socket connectionSocket = welcomeSocket.accept();
               BufferedReader   inFromClient = new BufferedReader  (new InputStreamReader(connectionSocket.getInputStream()));
               DataOutputStream outToClient  = new DataOutputStream(connectionSocket.getOutputStream());
               clientSentence = inFromClient.readLine();
               System.out.println("Received: " + clientSentence);
               capitalizedSentence = clientSentence.toUpperCase() + '\n';
               outToClient.writeBytes(capitalizedSentence);
            }
         } 
      }
} 