package com.maximilianboehm.javasourceparser.access;

import com.maximilianboehm.javasourceparser.model.JavaSourceParserImpl;

public class JavaSourceParserFactory {
   
   
   public JavaSourceParserHome getHome() throws Exception{
      return new JavaSourceParserImpl();
   }

}
