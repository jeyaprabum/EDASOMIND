package com.maximilianboehm.git.model;

public class GTModelFactory {

   public static GTHistoryImpl createHistoryImpl() {
      return new GTHistoryImpl();
   }

   public static GTHistoryFileImpl createHistoryFileImpl() {
      return new GTHistoryFileImpl();
   }
}
