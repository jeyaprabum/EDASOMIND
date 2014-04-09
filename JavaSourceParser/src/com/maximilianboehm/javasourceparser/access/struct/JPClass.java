package com.maximilianboehm.javasourceparser.access.struct;

import java.util.List;

import com.maximilianboehm.javasourceparser.access.struct.base.JPAnnotationHolder;

public interface JPClass extends JPAnnotationHolder{
   
   public String getClassName();
   public void setClassName(String className);
   public List<JPField>      getFields()      throws Exception;

}
