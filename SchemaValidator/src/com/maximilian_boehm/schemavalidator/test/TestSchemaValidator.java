package com.maximilian_boehm.schemavalidator.test;

import org.junit.Test;

import com.maximilian_boehm.schemavalidator.access.SVAccessFactory;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchema;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaManager;

public class TestSchemaValidator {

    @Test
    public void testSchemaValidator() throws Exception{
        SVSchemaManager manager = SVAccessFactory.getSVHome().createSchemaManager();

        SVSchema schema = manager.createInputSchema();

    }

}
