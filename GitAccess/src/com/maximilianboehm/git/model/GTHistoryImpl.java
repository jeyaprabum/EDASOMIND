package com.maximilianboehm.git.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.maximilianboehm.git.access.struct.GTHistory;
import com.maximilianboehm.git.access.struct.GTHistoryFile;

public class GTHistoryImpl implements GTHistory {

   private File currentFile;
   private List<GTHistoryFile> listHistory;

   @Override
   public File getCurrentFile() {
      return currentFile;
   }
   public void setCurrentFile(File currentFile) {
      this.currentFile = currentFile;
   }
   @Override
   public List<GTHistoryFile> getHistoryFiles() {
      return listHistory;
   }
   public void addHistoryFile(GTHistoryFileImpl historyFile){
      if(listHistory==null){
         listHistory = new ArrayList<>();
      }
      listHistory.add(historyFile);
   }

}
