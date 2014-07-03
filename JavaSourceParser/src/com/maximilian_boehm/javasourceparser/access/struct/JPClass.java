package com.maximilian_boehm.javasourceparser.access.struct;

import java.util.List;

import com.maximilian_boehm.javasourceparser.access.struct.base.JPAnnotationHolder;

public interface JPClass extends JPAnnotationHolder{
   
   public String getPackageName();
   public String getClassName();
   public List<JPField> getFields() throws Exception;
   
//   public void setPackageName(String sPackageName);
//   public void setClassName(String className);
//   public void addField(JPField field);

}
