package com.maximilian_boehm.edasomind.test;

import static org.hamcrest.core.IsEqual.equalTo;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.maximilian_boehm.edasomind.access.EdasomindAccessFactory;
import com.maximilian_boehm.edasomind.access.struct.EdasomindResultList;
import com.maximilian_boehm.edasomind.access.struct.EdasomindSignificance;
import com.maximilian_boehm.edasomind.model.EdasomindResultListImpl;


/**
 * Class for Testing EDASOMIND
 *
 */
public class TestEDASOMIND {

    @Test
    public void testProgram() throws Exception{
        // Get file for Testing
        File f = new File("/Users/Max/Documents/Programmierung/git/MBRepository/MongoTestCase/src/main/java/com/maximilian_boehm/hsregensburg/bachelor/TestSchemaValidator.java");
        // Retrieve result from analyzing
        EdasomindResultList result = EdasomindAccessFactory.getHome().analyzeFile(f);
        // Compare with static result
        Assert.assertThat(result, equalTo(getExpectedResults()));
    }

    /**
     * Create static result
     * @return
     */
    public EdasomindResultList getExpectedResults(){
        EdasomindResultListImpl list = new EdasomindResultListImpl();
        list.addTestResult(EdasomindSignificance.NONE,   1405603244000L, 1407225469000L, "Added field ABC");
        list.addTestResult(EdasomindSignificance.NONE,   1405603244000L, 1407225469000L, "Added field DEF");
        list.addTestResult(EdasomindSignificance.HIGH,   1405603244000L, 1407225469000L, "Reintroduced field ABC");
        list.addTestResult(EdasomindSignificance.HIGH,   1405603244000L, 1407225469000L, "Reintroduced field DEF");
        list.addTestResult(EdasomindSignificance.MIDDLE, 1405603238000L, 1405603244000L, "Removed field DEF");
        list.addTestResult(EdasomindSignificance.MIDDLE, 1405603229000L, 1405603238000L, "Removed field ABC");
        list.addTestResult(EdasomindSignificance.NONE,   1405603208000L, 1405603229000L, "Added field GHI");
        list.addTestResult(EdasomindSignificance.NONE,   1405603193000L, 1405603208000L, "Added field DEF");
        return list;
    }
}