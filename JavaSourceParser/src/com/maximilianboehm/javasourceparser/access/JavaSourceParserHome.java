package com.maximilianboehm.javasourceparser.access;

import java.io.File;

import com.maximilianboehm.javasourceparser.access.struct.JPClass;

public interface JavaSourceParserHome {
   
   public JPClass getClass(File f) throws Exception;

}
