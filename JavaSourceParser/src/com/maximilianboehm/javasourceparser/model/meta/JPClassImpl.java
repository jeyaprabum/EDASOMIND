package com.maximilianboehm.javasourceparser.model.meta;

import java.util.ArrayList;
import java.util.List;

import com.maximilianboehm.javasourceparser.access.struct.JPClass;
import com.maximilianboehm.javasourceparser.access.struct.JPField;
import com.maximilianboehm.javasourceparser.model.meta.base.JPAnnotationHolder;

public class JPClassImpl extends JPAnnotationHolder implements JPClass{
   
   private String ClassName;
   private List<JPField>      listFields;
   
   public String getClassName() {
      return ClassName;
   }
   public void setClassName(String className) {
      ClassName = className;
   }
   
   public void addDeclaredField(JPField field){
      if(listFields==null) listFields = new ArrayList<JPField>();
      listFields.add(field);
   }
   
   public void setDeclaredFields(List<JPField> listFields) {
      this.listFields = listFields;
   }
   public List<JPField> getFields() throws Exception {
      return listFields;
   }


}
