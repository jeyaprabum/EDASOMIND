package com.maximilian_boehm.schemavalidator.test;

import static org.hamcrest.core.IsEqual.equalTo;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;

import com.maximilian_boehm.schemavalidator.access.SVAccessFactory;
import com.maximilian_boehm.schemavalidator.access.SVHome;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResult;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResultTable;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResultType;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaManager;
import com.maximilian_boehm.schemavalidator.model.SVCompareResultImpl;
public class TestSchemaValidator {

    private SVHome home;
    private String sPath;

    /**
     * Retrieve Java-Source-File from Test-Data
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        // Get the test-file
        //Employee.class.getResource("Employee.java").getFile();
        sPath = TestSchemaValidator.class.getResource("testdata/Locate.txt").getFile();

        // Workaround: Get path to src-directory
        sPath = sPath.replace("SchemaValidator/bin/com/", "SchemaValidator/src/com/");
        sPath = sPath.replace("testdata/Locate.txt", "testdata/");

        home = SVAccessFactory.getSVHome();

    }

    /**
     * Test the recognition of a new field
     * @throws Exception
     */
    @org.junit.Test
    public void testNewField() throws Exception {
        SVSchemaManager manager = home.createSchemaManager();
        manager.addSchemaByFile(new File(sPath+"Test_Add_After.java" ), Calendar.getInstance());
        manager.addSchemaByFile(new File(sPath+"Test_Add_Before.java"), Calendar.getInstance());
        for(SVCompareResultTable resultTable:manager.compareSchemata()){
            for(SVCompareResult result:resultTable.getResults()){
                Assert.assertEquals(SVCompareResultType.NEW_FIELD, result.getType());
                Assert.assertEquals("ABC",result.getFieldName());
            }
        }
    }

    /**
     * @throws Exception
     */
    @org.junit.Test
    public void testRemoveField() throws Exception {
        SVSchemaManager manager = home.createSchemaManager();
        manager.addSchemaByFile(new File(sPath+"Test_Delete_After.java" ), Calendar.getInstance());
        manager.addSchemaByFile(new File(sPath+"Test_Delete_Before.java"), Calendar.getInstance());
        for(SVCompareResultTable resultTable:manager.compareSchemata()){
            for(SVCompareResult result:resultTable.getResults()){
                Assert.assertEquals(SVCompareResultType.REMOVE_FIELD, result.getType());
                Assert.assertEquals("ABC", result.getFieldName());
            }
        }
    }

    /**
     * @throws Exception
     */
    @org.junit.Test
    public void testChangeDatatype() throws Exception {
        SVSchemaManager manager = home.createSchemaManager();
        manager.addSchemaByFile(new File(sPath+"Test_Datatype_After.java" ), Calendar.getInstance());
        manager.addSchemaByFile(new File(sPath+"Test_Datatype_Before.java"), Calendar.getInstance());
        for(SVCompareResultTable resultTable:manager.compareSchemata()){
            for(SVCompareResult result:resultTable.getResults()){
                Assert.assertEquals(SVCompareResultType.CHANGE_FIELD, result.getType());
                Assert.assertEquals("ABC", result.getFieldName());
            }
        }
    }

    /**
     * @throws Exception
     */
    @org.junit.Test
    public void testReintroduceField() throws Exception {
        SVSchemaManager manager = home.createSchemaManager();
        manager.addSchemaByFile(new File(sPath+"Test_Reintroduce_After.java" ), Calendar.getInstance());
        manager.addSchemaByFile(new File(sPath+"Test_Reintroduce_Middle.java" ), Calendar.getInstance());
        manager.addSchemaByFile(new File(sPath+"Test_Reintroduce_Before.java" ), Calendar.getInstance());

        List<SVCompareResultTable> listResult = manager.compareSchemata();

        Assert.assertEquals(2, listResult.size());
        Assert.assertThat(listResult.get(0).getResults(), equalTo(getResultList0()));
        Assert.assertThat(listResult.get(1).getResults(), equalTo(getResultList1()));
    }

    private List<SVCompareResult> getResultList0() {
        List<SVCompareResult> listResult = new ArrayList<SVCompareResult>();
        listResult.add(new SVCompareResultImpl(SVCompareResultType.NEW_FIELD, "ABC"));
        listResult.add(new SVCompareResultImpl(SVCompareResultType.REINTRODUCE, "ABC"));
        return listResult;
    }
    private List<SVCompareResult> getResultList1() {
        List<SVCompareResult> listResult = new ArrayList<SVCompareResult>();
        listResult.add(new SVCompareResultImpl(SVCompareResultType.REMOVE_FIELD, "ABC"));
        return listResult;
    }

}
