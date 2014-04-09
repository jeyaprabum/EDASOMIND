package com.maximilianboehm.hsregensburg.bachelor.test;

import java.io.File;

import org.junit.Test;

import com.maximilianboehm.javasourceparser.access.JPFactory;
import com.maximilianboehm.javasourceparser.access.JPHome;
import com.maximilianboehm.javasourceparser.access.struct.JPClass;

public class TestJava {

   @Test
   public void test() throws Exception {
      
      
      File f = new File("T:\\Dateien\\Sonstiges\\git\\MBRepository\\MongoTestCase\\src\\main\\java\\com\\maximilianboehm\\hsregensburg\\bachelor\\Employee.java");
      
      
      JPHome jpHome = JPFactory.getHome();
      JPClass jpClass = jpHome.getParsedClass(f);
      
      
      
//      S4MClassImpl jClass = new S4MClassImpl(f);
//      
//      new JavaSourceReader().parseJavaSourceFile(jClass);
//      
//      System.out.println(jClass);
//      fail("Not yet implemented");
   }

}
