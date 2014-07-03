package com.maximilian_boehm.schemavalidator.test;

import com.maximilian_boehm.schemavalidator.access.SVAccessFactory;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchema;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaManager;


public class TestCLS {

	public static void main(String[] args) throws Exception{
		
		SVSchemaManager manager = SVAccessFactory.getSVHome().createSchemaManager();
		
		SVSchema schema = manager.createInputSchema();
	}
	
	

}
