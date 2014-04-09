package com.maximilianboehm.javasourceparser.access.struct;

import java.util.List;
import java.util.Map;

public interface JPAnnotation {
   
   public String getType() throws Exception;
   public Map<String, String> getAttributes() throws Exception;
}
