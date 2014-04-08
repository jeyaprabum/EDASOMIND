package com.maximilianboehm.hsregensburg.bachelor.test.java;

import java.io.File;

import org.junit.Test;

import com.maximilianboehm.scheme4mongo.java.JavaClass;
import com.maximilianboehm.scheme4mongo.java.JavaSourceReader;

public class ReadTest {

   @Test
   public void test() throws Exception {
      
      File f = new File("T:\\Dateien\\Sonstiges\\git\\MBRepository\\MongoTestCase\\src\\main\\java\\com\\maximilianboehm\\hsregensburg\\bachelor\\Employee.java");
      
      JavaClass jClass = new JavaClass(f);
      
      new JavaSourceReader().parseJavaSourceFile(jClass);
      
      System.out.println(jClass);
//      fail("Not yet implemented");
   }

}
