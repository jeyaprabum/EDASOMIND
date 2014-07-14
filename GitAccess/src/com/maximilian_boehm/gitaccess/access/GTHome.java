package com.maximilian_boehm.gitaccess.access;

import java.io.File;

import com.maximilian_boehm.gitaccess.access.struct.GTHistory;

/**
 * Home for GitAccess-Project
 */
public interface GTHome {

    /**
     * Retrieve all previous versions of a file
     * Determins the information by using the path to find the GIT-Home
     * @param f, the current version
     * @return
     * @throws Exception
     */
    public GTHistory getGitHistoryOfFile(File f) throws Exception;

}
