package com.maximilianboehm.javasourceparser.access;

import com.maximilianboehm.javasourceparser.model.JPHomeImpl;
import com.maximilianboehm.javasourceparser.model.meta.JPAnnotationImpl;
import com.maximilianboehm.javasourceparser.model.meta.JPClassImpl;
import com.maximilianboehm.javasourceparser.model.meta.JPFieldImpl;

public class JPFactory {
   
   
   public static JPHome getHome() {
      return new JPHomeImpl();
   }
   public static JPClassImpl createJPClass(){
      return new JPClassImpl();
   }
   public static JPAnnotationImpl createJPAnnotation(){
      return new JPAnnotationImpl();
   }
   public static JPFieldImpl createJPField(){
      return new JPFieldImpl();
   }
   
}
