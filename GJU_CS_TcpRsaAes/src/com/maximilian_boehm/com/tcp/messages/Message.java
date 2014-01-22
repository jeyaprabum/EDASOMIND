package com.maximilian_boehm.com.tcp.messages;

public class Message {
   
   
   public Message() {
   }
   
   public Message(MessageTyp typ, byte[] b) {
      setTyp(typ);
      setData(b);
   }
   
   private byte[] data;
   private MessageTyp typ;
   
   public MessageTyp getTyp() {
      return typ;
   }

   public void setTyp(MessageTyp typ) {
      this.typ = typ;
   }

   public byte[] getData() {
      return data;
   }

   public void setData(byte[] key) {
      this.data = key;
   }

}
