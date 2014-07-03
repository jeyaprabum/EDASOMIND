package com.maximilian_boehm.edasomind.test;

import org.junit.Test;

import com.maximilian_boehm.gitaccess.test.TestGitAccess;
import com.maximilian_boehm.javasourceparser.test.TestJavaSourceParser;
import com.maximilian_boehm.schemavalidator.test.TestSchemaValidator;

public class TestEDASOMIND {

	@Test
	public void test() {
		// Tests all other Components
		new TestGitAccess().test();
		new TestJavaSourceParser().test();
		new TestSchemaValidator().test();
	}

}
