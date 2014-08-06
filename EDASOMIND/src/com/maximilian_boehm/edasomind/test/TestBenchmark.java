package com.maximilian_boehm.edasomind.test;

import java.io.File;

import org.junit.Test;

import com.maximilian_boehm.edasomind.access.EdasomindAccessFactory;


/**
 * Class for Benchmark Testing EDASOMIND
 */
public class TestBenchmark {

    private static final String PACKAGE_PREFIX = "/Users/Max/Documents/Programmierung/git/MBRepository/MongoTestCase/src/main/java/com/maximilian_boehm/hsregensburg/bachelor";

    /**
     * @throws Exception
     */
    @Test
    public void testBenchmarkRegular() throws Exception{
        runTestAndMeasureDuration(PACKAGE_PREFIX+"/TestBenchmark.java");
    }

    /**
     * @throws Exception
     */
    @Test
    public void testBenchmarkManyFields() throws Exception{
        runTestAndMeasureDuration(PACKAGE_PREFIX+"/benchmark/TestHighNumberVar.java");
    }

    /**
     * @throws Exception
     */
    @Test
    public void testBenchmarkManyCommits() throws Exception{
        runTestAndMeasureDuration(PACKAGE_PREFIX+"/benchmark/HighNumberCommit.java");
    }

    /**
     * @param sFile
     * @throws Exception
     */
    private void runTestAndMeasureDuration(String sFile) throws Exception{
        // Remember start time
        long lStartTime = System.currentTimeMillis();
        // do it 400 times
        for (int i = 0; i < 100; i++) {
            // measure time of each run
            long nCurrent = System.currentTimeMillis();
            // analyze that one file
            EdasomindAccessFactory.getHome().analyzeFile(new File(sFile));
            // sysout the needed time
            System.out.println(System.currentTimeMillis()-nCurrent);
        }
        // sysout time for whole test
        System.out.println("Time needed for whole test: "+(System.currentTimeMillis()-lStartTime));

    }

}