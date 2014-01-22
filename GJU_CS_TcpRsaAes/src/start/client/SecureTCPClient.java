package start.client;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SecureTCPClient{
   
   public String handleSecureMessage(String sMessage) {
      String sRSAPublicKey = send("REQ_RSA_PUB_KEY");
      System.out.println(sRSAPublicKey);
      // generate AES key
      // send AES key encrypted by RSA
      // send message encrypted by AES
      
      return "DEI_MUDDA";
      
   }
   
 private String send(String sMessage){
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
 
public void getAES() {
   try {
      KeyGenerator keygen = KeyGenerator.getInstance("AES");
      SecureRandom random = new SecureRandom();
      keygen.init(random);
      SecretKey key = keygen.generateKey();
   } catch (Exception e) {
      // TODO: handle exception
   }
}
}