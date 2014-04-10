package com.maximilianboehm.hsregensburg.bachelor.test;

import java.io.File;

import org.junit.Test;

import com.maximilianboehm.git.access.GTAccessFactory;

public class TestGit {

   @Test
   public void test() throws Exception{
      File f = new File("C:\\MBRepository\\MongoTestCase\\src\\main\\java\\com\\maximilianboehm\\hsregensburg\\bachelor\\Employee.java");
      GTAccessFactory.getHome().getGitHistoryOfFile(f);
   }

}
