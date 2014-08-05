package com.maximilian_boehm.edasomind.access;

import java.io.File;

import com.maximilian_boehm.edasomind.access.struct.EdasomindResultList;

public interface EdasomindHome {

    public EdasomindResultList analyzeFile(File f) throws Exception;

}
