package com.maximilianboehm.javasourceparser.access.struct;

import java.util.List;

public interface JPField {
   
   public String getName();
   public String getType();
   public List<JPAnnotation> getAnnotations() throws Exception;


}
