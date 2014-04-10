package com.maximilianboehm.git.access;

import java.io.File;

import com.maximilianboehm.git.access.struct.GTHistory;

public interface GTHome {

   public GTHistory getGitHistoryOfFile(File f) throws Exception;

}
