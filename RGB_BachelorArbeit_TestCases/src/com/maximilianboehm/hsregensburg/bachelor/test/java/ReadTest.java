package com.maximilianboehm.hsregensburg.bachelor.test.java;

import java.io.File;

import org.junit.Test;

import com.maximilianboehm.javasourceparser.model.JavaSourceReader;
import com.maximilianboehm.javasourceparser.model.S4MClassImpl;
import com.sun.tools.doclint.Entity;

public class ReadTest {

   @Test
   public void test() throws Exception {
      
      
      File f = new File("T:\\Dateien\\Sonstiges\\git\\MBRepository\\MongoTestCase\\src\\main\\java\\com\\maximilianboehm\\hsregensburg\\bachelor\\Employee.java");
      
      S4MClassImpl jClass = new S4MClassImpl(f);
      
      new JavaSourceReader().parseJavaSourceFile(jClass);
      
      System.out.println(jClass);
//      fail("Not yet implemented");
   }

}
