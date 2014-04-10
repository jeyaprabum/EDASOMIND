package com.maximilianboehm.git.model;

import java.io.File;
import java.util.List;

public class GTHistoryImpl {

   private File currentFile;
   private List<GTHistoryFileImpl> listHistory;

   public File getCurrentFile() {
      return currentFile;
   }
   public void setCurrentFile(File currentFile) {
      this.currentFile = currentFile;
   }
   public List<GTHistoryFileImpl> getListHistory() {
      return listHistory;
   }
   public void setListHistory(List<GTHistoryFileImpl> listHistory) {
      this.listHistory = listHistory;
   }

}
