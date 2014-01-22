package com.maximilian_boehm.com.tcp;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class TCPPlainClient {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
       TCPPlainClient client = new TCPPlainClient();
       client.start();
    }
    
    private void start()throws Exception{
       
       long l1 = System.nanoTime();
       
       Client client = new Client();
       client.start();
       client.connect(5000, "localhost", 54555, 54777);
       
       client.sendTCP("Hello, my name is Maximilian Boehm and this text is encrypted by AES");
       
       
       client.addListener(new Listener() {
          public void received (Connection connection, Object object) {
             try {
                if (object instanceof Integer) {
                   Integer nStatusCode = (Integer)object;
                   
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
       
       System.out.println(System.nanoTime()-l1);
       
    }
}