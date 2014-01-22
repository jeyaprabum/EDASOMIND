package com.maximilian_boehm.com.XXX;

import java.io.PrintWriter;
import java.net.Socket;


public class TCPRequest {
   
   private int StatusCode;
   private int length;
   private byte[] data;
   
   public TCPRequest(int nStatus, byte[] d) {
      setStatusCode(nStatus);
      setData(d);
      setLength(d.length);
   }
   
   public void send(Socket cClient) throws Exception {
      PrintWriter cOut = new PrintWriter(cClient.getOutputStream(), true);
      cOut.print(getStatusCode());
      cOut.print(",");
      cOut.println(getLength());
      cClient.getOutputStream().write(getData());
      cClient.getOutputStream().flush();
   }

   
   public int getStatusCode() {
      return StatusCode;
   }

   public void setStatusCode(int statusCode) {
      StatusCode = statusCode;
   }

   public int getLength() {
      return length;
   }

   public void setLength(int length) {
      this.length = length;
   }

   public byte[] getData() {
      return data;
   }

   public void setData(byte[] data) {
      this.data = data;
   }

}
