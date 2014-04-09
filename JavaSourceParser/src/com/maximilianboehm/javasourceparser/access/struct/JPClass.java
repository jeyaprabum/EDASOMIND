package com.maximilianboehm.javasourceparser.access.struct;

import java.util.List;

public interface JPClass {
   
   public List<JPAnnotation> getAnnotations() throws Exception;
   public List<JPField>      getFields()      throws Exception;

}
