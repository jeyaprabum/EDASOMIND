package com.maximilian_boehm.com.tcp;
import java.security.Key;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.maximilian_boehm.com.tcp.messages.Message;
import com.maximilian_boehm.com.tcp.messages.MessageTyp;

public class TCPRsaAesClient extends TCPClient{
   
   // member
   private byte[] AES_KEY = null;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
       TCPRsaAesClient client = new TCPRsaAesClient();
       client.init();
       client.start();
    }
    
    
    
    protected void init() throws Exception {
       AES_KEY = AES.generateAESKey();
       super.init();
   }
    
    private void start()throws Exception{
       
       final long lStart = System.currentTimeMillis();
       
       // Request RSA-Public-Key
       client.sendTCP(new Message(MessageTyp.C_RSA_PUBLIC_KEY, null));
       
       
       client.addListener(new Listener() {
          
          private long lLastAction = 0;
          
          public void received (Connection connection, Object object) {
             try {
                Message msg = (Message)object;
                
                switch (msg.getTyp()) {
                  case C_RSA_PUBLIC_KEY:
                     System.out.println("Time for Retrieving RSA Key: "+(System.currentTimeMillis()-lStart));
                     // RETRIEVE KEY
                     Key publicKey = RSA.getKeyByBytes(msg.getData());
                     // Send AES-Key encrypted by public RSA Key
                     lLastAction = System.currentTimeMillis();
                     connection.sendTCP(new Message(MessageTyp.C_AES_KEY, RSA.encrypt(AES_KEY, publicKey)));
                     //System.out.println("Send AES Key encrypted by RSA");
                     break;
                     
                  case AES_KEY_ACCEPTED:
                     System.out.println("Time for Sending Encrypted AES Key: "+(System.currentTimeMillis()-lLastAction));
                     lLastAction = System.currentTimeMillis();
                     sendMessage(connection);
                     //System.out.println("Message encrypted by AES sent");
                     break;
                     
                  case MESSAGE_RECEIVED:
                     System.out.println("Time for Sending Encrypted Message: "+(System.currentTimeMillis()-lLastAction));
                     if(doAgain()){
                        lLastAction = System.currentTimeMillis();
                        //System.out.println("SEND AGAIN");
                        sendMessage(connection);
                     } else{
                        System.out.println("Needed time: "+(System.currentTimeMillis()-lStart)+"ms");
                        connection.close();
                     }
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
    }
    
    private void sendMessage(Connection con) {
       con.sendTCP(new Message(MessageTyp.C_AES_MESSAGE, AES.encrypt(getRandomMessage().getBytes(), AES_KEY)));
   }
    

}