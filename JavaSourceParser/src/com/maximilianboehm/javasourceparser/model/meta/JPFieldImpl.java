package com.maximilianboehm.javasourceparser.model.meta;

import com.maximilianboehm.javasourceparser.access.struct.JPField;
import com.maximilianboehm.javasourceparser.model.meta.base.JPAnnotationHolderImpl;

public class JPFieldImpl extends JPAnnotationHolderImpl implements JPField{

   private String name;
   private String type;
   
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getType() {
      return type;
   }
   public void setType(String type) {
      this.type = type;
   }
}
