package com.maximilian_boehm.javasourceparser.access;

import java.io.File;

import com.maximilian_boehm.javasourceparser.access.struct.JPClass;

public interface JPHome {
   
   public JPClass getParsedClass(File f) throws Exception;

}
