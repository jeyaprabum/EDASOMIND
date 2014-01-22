package com.maximilian_boehm.com.tcp;

import com.esotericsoftware.kryo.Kryo;
import com.maximilian_boehm.com.tcp.messages.Message;
import com.maximilian_boehm.com.tcp.messages.MessageTyp;

public class TCP {
   
   protected final String sHost = "mbtest.auctores.biz";
   protected final int nPortRangeFrom = 54555;
   protected final int nPortRangeTo   = 54777;
   
   protected void registerClasses(Kryo krypo) {
      krypo.register(Message.class);
      krypo.register(MessageTyp.class);
      krypo.register(byte[].class);
   }
}
