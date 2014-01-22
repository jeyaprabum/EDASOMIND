package com.maximilian_boehm.com.tcp;

import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.maximilian_boehm.com.tcp.messages.MessageEncryptedByAES;
import com.maximilian_boehm.com.tcp.messages.PublicRSAKey;
import com.maximilian_boehm.com.tcp.messages.SymmetricAESKeyEncryptedByRSA;

public class TCPServer {
   
   // members 
   Key pubKey = null;
   Key privKey = null;
   byte[] AES_KEY = null;

   public static void main(String[] args) throws Exception{
      TCPServer server = new TCPServer();
      server.init();
      server.start();
      
      

   }
   
   private void init() {
      final int RSAKeySize = 1024;
      // Initialise RSA
      try{
          KeyPairGenerator RSAKeyGen = KeyPairGenerator.getInstance("RSA");
          RSAKeyGen.initialize(RSAKeySize);
          KeyPair pair = RSAKeyGen.generateKeyPair();
          pubKey = pair.getPublic();
          privKey = pair.getPrivate();
      } catch (GeneralSecurityException e) {
          System.out.println(e.getLocalizedMessage());
          System.out.println("Error initialising encryption. Exiting.\n");
          System.exit(0);
      }
   }
   
   private void start()throws Exception {
      Server server = new Server();
      server.start();
      server.bind(54555, 54777);
      
      server.getKryo().register(PublicRSAKey.class);
      server.getKryo().register(SymmetricAESKeyEncryptedByRSA.class);
      server.getKryo().register(MessageEncryptedByAES.class);
      server.getKryo().register(byte[].class);
      
      server.addListener(new Listener() {
         public void received (Connection connection, Object object) {
            try {
               if (object instanceof Integer) {
                  Integer nStatusCode = (Integer)object;
                  
                  if(nStatusCode.equals(Codes.STATUS_RSA_KEY))
                     connection.sendTCP(new PublicRSAKey(pubKey.getEncoded()));
               }
               
               if (object instanceof SymmetricAESKeyEncryptedByRSA) {
                  SymmetricAESKeyEncryptedByRSA aesKey = (SymmetricAESKeyEncryptedByRSA)object;
                  
                  AES_KEY = RSA.decrypt(aesKey.getKey(), privKey);
                  
                  System.out.println(new String(AES_KEY));
                  
                  connection.sendTCP(Codes.STATUS_AES_KEY);
               }
               
               
               
               if (object instanceof MessageEncryptedByAES) {
                  MessageEncryptedByAES msg = (MessageEncryptedByAES)object;
                  
                  String sMessage = new String(AES.decrypt(msg.getKey(), AES_KEY));
                  
                  System.out.println(AES_KEY);
                  System.out.println(sMessage);
                  
                  connection.sendTCP(Codes.STATUS_OK);
               }
               
               
            } catch (Exception e) {
               // TODO: handle exception
            }
         }
      });
      

   }
   


}
