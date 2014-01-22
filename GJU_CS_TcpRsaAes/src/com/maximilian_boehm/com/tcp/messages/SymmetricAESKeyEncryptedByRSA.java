package com.maximilian_boehm.com.tcp.messages;

public class SymmetricAESKeyEncryptedByRSA {
   
   public SymmetricAESKeyEncryptedByRSA() {
      // TODO Auto-generated constructor stub
   }
   public SymmetricAESKeyEncryptedByRSA(byte[] b) {
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
