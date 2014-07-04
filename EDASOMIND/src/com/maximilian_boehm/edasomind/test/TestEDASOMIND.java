package com.maximilian_boehm.edasomind.test;

import org.junit.Test;

import com.maximilian_boehm.gitaccess.test.TestGitAccess;
import com.maximilian_boehm.javasourceparser.test.TestJavaSourceParser;
import com.maximilian_boehm.schemavalidator.test.TestSchemaValidator;

public class TestEDASOMIND {

	@Test
	public void test() throws Exception{
		// Tests all other Components
		new TestGitAccess().testGitAccess();
		new TestJavaSourceParser().testJavaSourceParser();
		new TestSchemaValidator().testSchemaValidator();
	}

}
