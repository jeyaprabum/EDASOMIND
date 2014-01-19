package start.client;
import java.io.*;
import java.net.*;

public class PlainTCPClient{
   
 public String send(String sMessage){
    try {
        String sentence;
        String modifiedSentence;
        
        BufferedReader inFromUser = new BufferedReader( new StringReader(sMessage));
        Socket clientSocket = new Socket("localhost", 6789);
        
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
        return modifiedSentence;
    } catch (Exception e) {
       e.printStackTrace();
       return "FEHLER";
    }
 }
}