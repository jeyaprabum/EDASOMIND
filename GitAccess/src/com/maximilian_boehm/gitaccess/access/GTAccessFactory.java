package com.maximilian_boehm.gitaccess.access;

import com.maximilian_boehm.gitaccess.model.GTHomeImpl;

public class GTAccessFactory {

   public static GTHome getHome(){
      return new GTHomeImpl();
   }

}
