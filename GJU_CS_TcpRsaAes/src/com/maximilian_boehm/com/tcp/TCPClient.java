package com.maximilian_boehm.com.tcp;
import java.security.Key;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.maximilian_boehm.com.tcp.messages.MessageEncryptedByAES;
import com.maximilian_boehm.com.tcp.messages.PublicRSAKey;
import com.maximilian_boehm.com.tcp.messages.SymmetricAESKeyEncryptedByRSA;

public class TCPClient {
   
   // member
   private byte[] AES_KEY = null;

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
   }
    
    private void start()throws Exception{
       
       long l1 = System.nanoTime();
       
       Client client = new Client();
       client.start();
       client.connect(5000, "localhost", 54555, 54777);
       client.getKryo().register(PublicRSAKey.class);
       client.getKryo().register(SymmetricAESKeyEncryptedByRSA.class);
       client.getKryo().register(MessageEncryptedByAES.class);
       client.getKryo().register(byte[].class);

       
       client.sendTCP(Codes.STATUS_RSA_KEY);
       
       
       client.addListener(new Listener() {
          public void received (Connection connection, Object object) {
             try {
                if (object instanceof PublicRSAKey) {
                   PublicRSAKey rsaKey = (PublicRSAKey)object;
                   Key publicKey = RSA.getKeyByBytes(rsaKey.getKey());
                   connection.sendTCP(new SymmetricAESKeyEncryptedByRSA(RSA.encrypt(AES_KEY, publicKey)));
                   System.out.println("Send AES Key encrypted by RSA");
                }
                
                
                if (object instanceof Integer) {
                   Integer nStatusCode = (Integer)object;
                   
                   if(nStatusCode.equals(Codes.STATUS_AES_KEY)){
                      String sMessage = "Hello, my name is Maximilian Boehm and this text is encrypted by AES";
                      connection.sendTCP(new MessageEncryptedByAES(AES.encrypt(sMessage.getBytes(), AES_KEY)));
                      System.out.println("Message encrypted by AES sent");
                   }
                   
                   if(nStatusCode.equals(Codes.MESSAGE_RECEIVED)){
                      System.out.println("STOP-Message received");
                      connection.getEndPoint().stop();
                   }
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