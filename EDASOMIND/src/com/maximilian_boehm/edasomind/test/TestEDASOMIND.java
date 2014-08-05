package com.maximilian_boehm.edasomind.test;

import java.io.File;

import org.junit.Test;

import com.maximilian_boehm.edasomind.access.EdasomindAccessFactory;
import com.maximilian_boehm.edasomind.access.struct.EdasomindResultList;


public class TestEDASOMIND {

    @Test
    public void testProgram() throws Exception{
        File f = new File("/Users/Max/Documents/Programmierung/git/MBRepository/MongoTestCase/src/main/java/com/maximilian_boehm/hsregensburg/bachelor/TestSchemaValidator.java");
        EdasomindResultList result = EdasomindAccessFactory.getHome().analyzeFile(f);

        System.out.println(result);

    }

}
