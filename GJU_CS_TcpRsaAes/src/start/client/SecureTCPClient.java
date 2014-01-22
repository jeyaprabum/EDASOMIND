package start.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import start.Common;

public class SecureTCPClient extends Common{
   public static void main(String[] args) throws Exception {
      SecureTCPClient stc = new SecureTCPClient();
      stc.handleSecureMessage("TEST");
      
   }
   
   public String handleSecureMessage(String sMessage) throws Exception {
      getPublicRSAKey(send("REQ_RSA_PUB_KEY"));
      System.out.println(pubKey);
      // generate AES key
      // send AES key encrypted by RSA
      // send message encrypted by AES
      
      return "DEI_MUDDA";
      
   }
   
 private byte[] send(String sMessage){
    try {
        BufferedReader inFromUser = new BufferedReader( new StringReader(sMessage));
        Socket clientSocket = new Socket("localhost", 23073);
        
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream inFromServer  = new DataInputStream(clientSocket.getInputStream());
        
        String input = inFromUser.readLine();
        sendBytes(input.getBytes(), outToServer);
        byte[] answer = readBytes(inFromServer);
        clientSocket.close();
        return answer;
    } catch (Exception e) {
       e.printStackTrace();
       return new byte[]{0};
    }
 }
 
   
   PublicKey pubKey = null;
   private void getPublicRSAKey(byte[] bytes) throws Exception {
      pubKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bytes));
   }

   public byte[] rsaEncrypt(byte[] data) throws Exception {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, pubKey);
      byte[] cipherData = cipher.doFinal(data);
      return cipherData;
   }
}