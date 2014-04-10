package com.maximilianboehm.javasourceparser.access;

import com.maximilianboehm.javasourceparser.model.JPHomeImpl;

public class JPAccessFactory {
   
   public static JPHome getHome() {
      return new JPHomeImpl();
   }
}
