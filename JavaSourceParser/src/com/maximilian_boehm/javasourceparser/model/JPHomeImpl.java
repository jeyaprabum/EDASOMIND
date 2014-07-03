package com.maximilian_boehm.javasourceparser.model;

import java.io.File;

import com.maximilian_boehm.javasourceparser.access.JPHome;
import com.maximilian_boehm.javasourceparser.access.struct.JPClass;

public class JPHomeImpl implements JPHome{

   
   private JPSourceReader jsr = new JPSourceReader();
   
   public JPClass getParsedClass(File f) throws Exception {
      return jsr.parseJavaSourceFile(f, JPModelFactory.createJPClass());
   }

}
