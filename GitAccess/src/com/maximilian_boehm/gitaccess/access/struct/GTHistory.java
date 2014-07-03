package com.maximilian_boehm.gitaccess.access.struct;

import java.io.File;
import java.util.List;

public interface GTHistory {
   
   public File getCurrentFile() throws Exception;
   public List<GTHistoryFile> getHistoryFiles() throws Exception;

}
