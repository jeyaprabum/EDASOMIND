package com.maximilian_boehm.javasourceparser.access;

import com.maximilian_boehm.javasourceparser.model.JPHomeImpl;

public class JPAccessFactory {
   
   public static JPHome getHome() {
      return new JPHomeImpl();
   }
}
