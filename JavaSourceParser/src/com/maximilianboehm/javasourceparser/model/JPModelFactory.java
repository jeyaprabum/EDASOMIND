package com.maximilianboehm.javasourceparser.model;

import com.maximilianboehm.javasourceparser.model.meta.JPAnnotationImpl;
import com.maximilianboehm.javasourceparser.model.meta.JPClassImpl;
import com.maximilianboehm.javasourceparser.model.meta.JPFieldImpl;

public class JPModelFactory {
   
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
