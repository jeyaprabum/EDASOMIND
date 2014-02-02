package com.maximilian_boehm.com.tcp;

import java.util.UUID;

import com.esotericsoftware.kryonet.Client;

public class TCPClient extends TCP{
   
   protected Client client  = null;
   
   protected void init() throws Exception {
      client = new Client();
      client.start();
      client.connect(60000, sHost, nPortRangeFrom, nPortRangeTo);
      registerClasses(client.getKryo());
  }
   
   protected String getRandomMessage() {
      String s =  "Hello, this is just a randomized message:  "+UUID.randomUUID().toString();
      System.out.println("SENDING: "+s);
      return s;
   }
   
   private int nCounter = 0;
   public boolean doAgain(){
      nCounter++;
      
      if(nCounter<10)
         return true;
      
      return false;
   }


}
