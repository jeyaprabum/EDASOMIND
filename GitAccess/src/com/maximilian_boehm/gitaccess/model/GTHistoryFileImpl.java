package com.maximilian_boehm.gitaccess.model;

import java.io.File;

import com.maximilian_boehm.gitaccess.access.struct.GTHistoryFile;

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
