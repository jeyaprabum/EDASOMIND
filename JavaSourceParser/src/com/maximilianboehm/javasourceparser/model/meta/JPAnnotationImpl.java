package com.maximilianboehm.javasourceparser.model.meta;

import java.util.HashMap;
import java.util.Map;

import com.maximilianboehm.javasourceparser.access.struct.JPAnnotation;

public class JPAnnotationImpl implements JPAnnotation{
   
   private String type;
   private Map<String, String> mapAttributes;
   
   public String getType() {
      return type;
   }
   public void setType(String type) {
      this.type = type;
   }
   

   public void addAttribute(String key, String value){
      if(getAttributes()==null) mapAttributes = new HashMap<String, String>();
      mapAttributes.put(key, value);
   }
   public Map<String, String> getAttributes() {
      return mapAttributes;
   }
   public void setAttributes(Map<String, String> mapAttributes) {
      this.mapAttributes = mapAttributes;
   }

}
