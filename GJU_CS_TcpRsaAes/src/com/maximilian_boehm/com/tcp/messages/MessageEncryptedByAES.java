package com.maximilian_boehm.com.tcp.messages;

public class MessageEncryptedByAES {
   
   
   public MessageEncryptedByAES() {
   }
   
   public MessageEncryptedByAES(byte[] b) {
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
