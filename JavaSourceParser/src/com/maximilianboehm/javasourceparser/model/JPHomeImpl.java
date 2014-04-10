package com.maximilianboehm.javasourceparser.model;

import java.io.File;

import com.maximilianboehm.javasourceparser.access.JPHome;
import com.maximilianboehm.javasourceparser.access.struct.JPClass;

public class JPHomeImpl implements JPHome{

   
   private JavaSourceReader jsr = null;
   
   public JPClass getParsedClass(File f) throws Exception {
      if(jsr==null)
         jsr = new JavaSourceReader();
      return jsr.parseJavaSourceFile(f, JPModelFactory.createJPClass());
   }

}
