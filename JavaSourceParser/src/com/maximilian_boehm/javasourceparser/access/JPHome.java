package com.maximilian_boehm.javasourceparser.access;

import java.io.File;

import com.maximilian_boehm.javasourceparser.access.struct.JPClass;

public interface JPHome {

    /**
     * Extract information of file
     * @param f
     * @return
     * @throws Exception
     */
    public JPClass getParsedClass(File f) throws Exception;

}
