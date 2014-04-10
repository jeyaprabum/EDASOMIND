package com.maximilianboehm.javasourceparser.access.struct;

import java.util.Map;

public interface JPAnnotation {
   
   public String getType() throws Exception;
   public Map<String, String> getAttributes() throws Exception;
   public boolean hasAttributes();
   
//   public void setType(String type);
//   public void addAttribute(String key, String value);
//   public void setAttributes(Map<String, String> mapAttributes);
}
