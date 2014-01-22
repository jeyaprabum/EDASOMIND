package com.maximilian_boehm.com.tcp;
import java.util.UUID;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.maximilian_boehm.com.tcp.messages.Message;
import com.maximilian_boehm.com.tcp.messages.MessageTyp;

public class TCPPlainClient extends TCPClient{

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
       TCPPlainClient client = new TCPPlainClient();
       client.init();
       client.start();
    }
    
    private void start()throws Exception{
       
       final long lStart = System.currentTimeMillis();
       
       // Request RSA-Public-Key
       String sMessage = "Hello, this is just a randomized message:  "+UUID.randomUUID().toString();
       client.sendTCP(new Message(MessageTyp.C_PLAIN, sMessage.getBytes()));
       
       
       client.addListener(new Listener() {
          
          private long lLastAction = 0;
          
          
          
          public void received (Connection connection, Object object) {
             try {
                Message msg = (Message)object;
                
                switch (msg.getTyp()) {
                  case MESSAGE_RECEIVED:
                     if(lLastAction!=0)
                        System.out.println("Time for Sending Encrypted Message: "+(System.currentTimeMillis()-lLastAction));
                     if(doAgain()){
                        lLastAction = System.currentTimeMillis();
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
       con.sendTCP(new Message(MessageTyp.C_PLAIN, getRandomMessage().getBytes()));
   }
    

}