package com.maximilianboehm.git.model;

import java.io.File;

import com.maximilianboehm.git.access.struct.GTHistoryFile;

public class GTHistoryFileImpl implements GTHistoryFile{

   private File file;

   @Override
   public File getFile() {
      return file;
   }

   public void setFile(File file) {
      this.file = file;
   }

}
