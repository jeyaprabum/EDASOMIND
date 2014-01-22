package com.maximilian_boehm.com.tcp;
import java.security.Key;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.maximilian_boehm.com.tcp.messages.Message;
import com.maximilian_boehm.com.tcp.messages.MessageTyp;

public class TCPClient {
   
   // member
   private byte[] AES_KEY = null;
   private Client client  = null;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
       TCPClient client = new TCPClient();
       client.init();
       client.start();
    }
    
    
    
    private void init() throws Exception {
       AES_KEY = AES.generateAESKey();
       client = new Client();
       client.start();
       client.connect(5000, "localhost", 54555, 54777);
       client.getKryo().register(Message.class);
       client.getKryo().register(MessageTyp.class);
       client.getKryo().register(byte[].class);
   }
    
    private void start()throws Exception{
       
       long l1 = System.nanoTime();
       
       // Request RSA-Public-Key
       client.sendTCP(new Message(MessageTyp.C_RSA_PUBLIC_KEY, null));
       
       
       client.addListener(new Listener() {
          public void received (Connection connection, Object object) {
             try {
                Message msg = (Message)object;
                
                switch (msg.getTyp()) {
                  case C_RSA_PUBLIC_KEY:
                     // RETRIEVE KEY
                     Key publicKey = RSA.getKeyByBytes(msg.getData());
                     // Send AES-Key encrypted by public RSA Key
                     connection.sendTCP(new Message(MessageTyp.C_AES_KEY, RSA.encrypt(AES_KEY, publicKey)));
                     System.out.println("Send AES Key encrypted by RSA");
                     break;
                     
                  case AES_KEY_ACCEPTED:
                     String sMessage = "Hello, my name is Maximilian Boehm and this text is encrypted by AES";
                     connection.sendTCP(new Message(MessageTyp.C_AES_MESSAGE, AES.encrypt(sMessage.getBytes(), AES_KEY)));
                     System.out.println("Message encrypted by AES sent");
                     break;
                     
                  case MESSAGE_RECEIVED:
                     System.out.println("STOP-Message received");
                     connection.getEndPoint().stop();
                     break;

                  default:
                     throw new Exception("Error");
               }
                
            } catch (Exception e) {
               e.printStackTrace();
            }
             
          }
       });
       
       client.run();
       
       System.out.println(System.nanoTime()-l1);
       
    }
    
    

    
    

}