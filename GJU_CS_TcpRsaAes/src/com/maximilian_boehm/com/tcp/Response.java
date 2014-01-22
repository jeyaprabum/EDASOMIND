package com.maximilian_boehm.com.tcp;


public class Response {
   
   private int StatusCode;
   private int length;
   private byte[] data;
   
   public Response() {
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
