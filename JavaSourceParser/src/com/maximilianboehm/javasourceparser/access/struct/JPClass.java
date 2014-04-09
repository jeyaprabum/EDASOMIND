package com.maximilianboehm.javasourceparser.access.struct;

import java.util.List;

import com.maximilianboehm.javasourceparser.access.struct.base.JPAnnotationHolder;

public interface JPClass extends JPAnnotationHolder{
   
   public String getPackageName();
   public void setPackageName(String sPackageName);
   public String getClassName();
   public void setClassName(String className);
   public List<JPField> getFields() throws Exception;
   public void addField(JPField field);

}
