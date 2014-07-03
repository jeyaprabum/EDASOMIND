package com.maximilian_boehm.gitaccess.access;

import java.io.File;

import com.maximilian_boehm.gitaccess.access.struct.GTHistory;

public interface GTHome {

   public GTHistory getGitHistoryOfFile(File f) throws Exception;

}
