package com.maximilian_boehm.com.tcp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.Socket;

public class Request extends Protocol{
   
   public Request(BufferedReader input, Socket sock) {
      try {
         String[] line = input.readLine().split(",");
         setStatusCode(Integer.parseInt(line[0]));
         setLength    (Integer.parseInt(line[1]));
         
         
         byte[] data = new byte[getLength()];
         sock.getInputStream().read(data,0,getLength());
         setData(data);
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
