package com.maximilianboehm.javasourceparser.access;

import com.maximilianboehm.javasourceparser.access.struct.JPAnnotation;
import com.maximilianboehm.javasourceparser.access.struct.JPClass;
import com.maximilianboehm.javasourceparser.access.struct.JPField;
import com.maximilianboehm.javasourceparser.model.JPHomeImpl;
import com.maximilianboehm.javasourceparser.model.meta.JPAnnotationImpl;
import com.maximilianboehm.javasourceparser.model.meta.JPClassImpl;
import com.maximilianboehm.javasourceparser.model.meta.JPFieldImpl;

public class JPFactory {
   
   
   public static JPHome getHome() {
      return new JPHomeImpl();
   }
   public static JPClass createJPClass(){
      return new JPClassImpl();
   }
   public static JPAnnotation createJPAnnotation(){
      return new JPAnnotationImpl();
   }
   public static JPField createJPField(){
      return new JPFieldImpl();
   }
   
}
