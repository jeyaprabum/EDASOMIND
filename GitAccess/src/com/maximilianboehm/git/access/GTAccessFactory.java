package com.maximilianboehm.git.access;

import com.maximilianboehm.git.model.GTHomeImpl;

public class GTAccessFactory {

   public static GTHome getHome(){
      return new GTHomeImpl();
   }

}
