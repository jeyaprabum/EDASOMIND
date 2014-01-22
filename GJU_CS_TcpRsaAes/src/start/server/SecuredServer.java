package start.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

import start.Common;

class SecuredServer extends Common {
   public static void main(String argv[]) throws Exception {
      SecuredServer ss = new SecuredServer();
      ss.initServer();
      ss.start();
   }
   
   // members
   Key publicKey = null;
   Key privateKey = null;

   private void start() throws Exception{
      try(ServerSocket welcomeSocket = new ServerSocket(23073)){

         System.out.println("Server waiting for connections");
         while (true) {
            // Connection alive?
            Socket connectionSocket = welcomeSocket.accept();
            // Client here!
            System.out.println("Client "+connectionSocket.getRemoteSocketAddress()+" connected");
            DataInputStream  dis = new DataInputStream (connectionSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(connectionSocket.getOutputStream());
   
            byte[] arr = null;
            while ((arr = readBytes(dis)) != null) {
               String sRequest = new String(arr);
               System.out.println(sRequest);
               //System.out.println(new String(rsaDecrypt(arr)));
            }
            
            sendBytes(publicKey.getEncoded(), dos);
         }
      }
   }
   
   
   private void initServer() throws Exception {
      KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
      kpg.initialize(2048);
      KeyPair kp = kpg.genKeyPair();
      publicKey  = kp.getPublic();
      privateKey = kp.getPrivate();
   }

   private byte[] rsaDecrypt(byte[] data) throws Exception {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      byte[] cipherData = cipher.doFinal(data);
      return cipherData;
   }

}