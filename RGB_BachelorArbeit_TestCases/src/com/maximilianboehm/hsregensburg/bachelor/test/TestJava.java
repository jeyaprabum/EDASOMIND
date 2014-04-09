package com.maximilianboehm.hsregensburg.bachelor.test;

import java.io.File;

import org.junit.Test;

import com.maximilianboehm.javasourceparser.access.JPFactory;
import com.maximilianboehm.javasourceparser.access.JPHome;
import com.maximilianboehm.javasourceparser.access.struct.JPAnnotation;
import com.maximilianboehm.javasourceparser.access.struct.JPClass;
import com.maximilianboehm.javasourceparser.access.struct.JPField;

public class TestJava {

   @Test
   public void test() throws Exception {
      
      File f = new File("T:\\Dateien\\Sonstiges\\git\\MBRepository\\MongoTestCase\\src\\main\\java\\com\\maximilianboehm\\hsregensburg\\bachelor\\Employee.java");
      
      JPHome jpHome = JPFactory.getHome();
      JPClass jpClass = jpHome.getParsedClass(f);
      
      System.out.println(jpClass.getClassName());
      System.out.println(jpClass.getPackageName());
      
      for(JPAnnotation anno:jpClass.getAnnotations()){
         System.out.println(anno.getType());
         for(String s:anno.getAttributes().keySet())
            System.out.println(s+"="+anno.getAttributes().get(s));
      }
      
      for(JPField field:jpClass.getFields()){
         System.out.println(field.getName());
         System.out.println(field.getType());
         if(field.hasAnnotations())
            for(JPAnnotation anno:field.getAnnotations()){
               System.out.println(anno.getType());
               if(anno.hasAttributes())
                  for(String s:anno.getAttributes().keySet())
                     System.out.println(s+"="+anno.getAttributes().get(s));
            }
      }
   }
}
