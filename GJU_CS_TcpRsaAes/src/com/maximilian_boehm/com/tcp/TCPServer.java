package com.maximilian_boehm.com.tcp;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.maximilian_boehm.com.tcp.messages.Message;
import com.maximilian_boehm.com.tcp.messages.MessageTyp;

public class TCPServer extends TCP{
   
   // members 
   private Key publicRSAKey  = null;
   private Key privateRSAKey = null;
   private byte[] symmAESKey = null;
   private Server server     = null;

   public static void main(String[] args) throws Exception{
      TCPServer server = new TCPServer();
      server.init();
      server.configureListener();
   }
   
   private void init() {
      try{
         server = new Server();
         server.start();
         server.bind(nPortRangeFrom, nPortRangeTo);
         registerClasses(server.getKryo());
         
         KeyPairGenerator RSAKeyGen = KeyPairGenerator.getInstance("RSA");
         RSAKeyGen.initialize(1024);
         KeyPair pair = RSAKeyGen.generateKeyPair();
         publicRSAKey = pair.getPublic();
         privateRSAKey = pair.getPrivate();
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(0);
      }
   }
   
   private void configureListener()throws Exception {
      server.addListener(new Listener() {
         public void received (Connection connection, Object object) {
            try {
               Message msg = (Message)object;
               
               switch (msg.getTyp()) {
                  case C_RSA_PUBLIC_KEY:
                     connection.sendTCP(new Message(MessageTyp.C_RSA_PUBLIC_KEY, publicRSAKey.getEncoded()));
                     System.out.println("SENT Public RSA Key");
                     break;
                  
                  case C_AES_KEY:
                     symmAESKey = RSA.decrypt(msg.getData(), privateRSAKey);
                     connection.sendTCP(new Message(MessageTyp.AES_KEY_ACCEPTED, null));
                     System.out.println("SENT OK for AES Key");
                     break;
                  
                  case C_AES_MESSAGE:
                     String sMessage = new String(AES.decrypt(msg.getData(), symmAESKey));
                     System.out.println("Message received: "+sMessage);
                     connection.sendTCP(new Message(MessageTyp.MESSAGE_RECEIVED, null));
                     break;
                     
                  case C_PLAIN:
                     String sMsg = new String(msg.getData());
                     System.out.println("Message received: "+sMsg);
                     connection.sendTCP(new Message(MessageTyp.MESSAGE_RECEIVED, null));
                     break;
                  
                  default:
                     throw new Exception("ERROR");
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
}
