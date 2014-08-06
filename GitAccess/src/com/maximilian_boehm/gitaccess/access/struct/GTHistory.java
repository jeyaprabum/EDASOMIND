package com.maximilian_boehm.gitaccess.access.struct;

import java.io.File;
import java.util.List;

/**
 * Represent the history of one specific file
 */
public interface GTHistory {


    /**
     * @return Get the most up2date file
     * @throws Exception
     */
    public File getCurrentFile() throws Exception;


    /**
     * @return Get the full history.
     * @throws Exception
     */
    public List<GTHistoryFile> getHistoryFiles() throws Exception;

}
