package com.maximilianboehm.git.model;

import java.io.File;

import com.maximilianboehm.git.access.GTHome;
import com.maximilianboehm.git.access.struct.GTHistory;

public class GTHomeImpl implements GTHome{

   @Override
   public GTHistory getGitHistoryOfFile(File f) throws Exception{
      return new MGitHistory().getHistory(f);
   }

}
