package com.maximilian_boehm.gitaccess.access.struct;

import java.io.File;
import java.util.List;

/**
 * Represent the history of one specific file
 */
public interface GTHistory {


    /**
     * Get the most up2date file
     * @return
     * @throws Exception
     */
    public File getCurrentFile() throws Exception;


    /**
     * Get the full history.
     * @return a sorted output
     * @throws Exception
     */
    public List<GTHistoryFile> getHistoryFiles() throws Exception;

}
