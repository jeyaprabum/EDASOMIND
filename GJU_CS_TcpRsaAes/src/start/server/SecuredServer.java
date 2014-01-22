package start.server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class SecuredServer{
   
   
   
   public static void main(String argv[]) throws Exception{
      new SecuredServer();
   }
   
   
   // members
   Key publicKey = null;
   Key privateKey = null;
   
   
   
   public SecuredServer() {
      // init
      
      initServer();
      // start
      start();
   }
   private void start() {
      try {
         
         String clientSentence;
         String capitalizedSentence;

         try (ServerSocket welcomeSocket = new ServerSocket(6789);){
            while(true){
               Socket connectionSocket = welcomeSocket.accept();
               BufferedReader   inFromClient = new BufferedReader  (new InputStreamReader(connectionSocket.getInputStream()));
               DataOutputStream outToClient  = new DataOutputStream(connectionSocket.getOutputStream());
               clientSentence = inFromClient.readLine();
               
               
               
               capitalizedSentence = handleRequest(clientSentence) + '\n';
               outToClient.writeBytes(capitalizedSentence);
            }
         } 
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   private String handleRequest(String sRequest){
      if(sRequest.startsWith("REQ_RSA_PUB_KEY"))
         return new String(publicKey.getEncoded());
      else if(sRequest.startsWith("REQ_AES_KEY"))
         return retrieveAESKeyByRSACipher(sRequest);
      else if(sRequest.startsWith("REQ_AES_CONTENT"))
         return encryptAndDescryptRequest(sRequest);
      else
         return "ERROR";
   }
   
   public String encryptAndDescryptRequest(String sReq) {
      return sReq;
   }
   
   public String retrieveAESKeyByRSACipher(String sReq){
      return sReq;
   }
   
   
   private void initServer() {
      try {
         KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
         kpg.initialize(2048);
         KeyPair kp = kpg.genKeyPair();
         publicKey = kp.getPublic();
         privateKey = kp.getPrivate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   
} 