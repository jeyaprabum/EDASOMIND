package com.maximilian_boehm.com.tcp.messages;

public class PublicRSAKey {
   
   public PublicRSAKey() {
   }
   
   public PublicRSAKey(byte[] b) {
      setKey(b);
   }
   
   private byte[] key;

   public byte[] getKey() {
      return key;
   }

   public void setKey(byte[] key) {
      this.key = key;
   }

}
