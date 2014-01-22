package com.maximilian_boehm.com.XXX;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPResponse extends TCPProtocol{
   
   public TCPResponse(Socket sock) {
      try {
         InputStream is = sock.getInputStream();
         BufferedReader input = new BufferedReader(new InputStreamReader(is));
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
