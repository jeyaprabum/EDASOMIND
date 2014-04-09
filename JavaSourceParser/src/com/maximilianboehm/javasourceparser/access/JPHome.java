package com.maximilianboehm.javasourceparser.access;

import java.io.File;

import com.maximilianboehm.javasourceparser.access.struct.JPClass;

public interface JPHome {
   
   public JPClass getParsedClass(File f) throws Exception;

}
