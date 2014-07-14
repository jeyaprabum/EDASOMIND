package com.maximilian_boehm.schemavalidator.test;

import org.junit.Test;

import com.maximilian_boehm.schemavalidator.access.SVAccessFactory;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchema;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaManager;
import com.maximilian_boehm.schemavalidator.access.struct.SVSimpleCondition;

public class TestSchemaValidator {

    @Test
    public void testSchemaValidator() throws Exception{
        SVSchemaManager manager = SVAccessFactory.getSVHome().createSchemaManager();

        SVSchema schema = manager.createInputSchema();

        SVSimpleCondition cond = new SVSimpleCondition();
        cond.setKey("vorname");
        schema.addCondition(cond);

        SVSimpleCondition cond2 = new SVSimpleCondition();
        cond2.setKey("nachname");
        schema.addCondition(cond2);

        schema.printConditions();

        SVSchema schemacompare = manager.createSchema2Compare();
        SVSimpleCondition cond3 = new SVSimpleCondition();
        cond3.setKey("vorname");
        schemacompare.addCondition(cond3);

        SVSimpleCondition cond4 = new SVSimpleCondition();
        cond4.setKey("nachname12");
        schemacompare.addCondition(cond4);

        manager.compare(schema, schemacompare);
    }

}
