package com.maximilianboehm.hsregensburg.bachelor.test;

import java.io.File;

import org.junit.Test;

import com.maximilianboehm.git.access.GTAccessFactory;
import com.maximilianboehm.git.access.struct.GTHistory;
import com.maximilianboehm.git.access.struct.GTHistoryFile;
import com.maximilianboehm.javasourceparser.access.JPAccessFactory;
import com.maximilianboehm.javasourceparser.access.JPHome;
import com.maximilianboehm.javasourceparser.access.struct.JPClass;

public class TestGit {

   @Test
   public void test() throws Exception{
      File f = new File("C:\\MBRepository\\MongoTestCase\\src\\main\\java\\com\\maximilianboehm\\hsregensburg\\bachelor\\Employee.java");
      GTHistory history = GTAccessFactory.getHome().getGitHistoryOfFile(f);

      JPHome  jpHome  = JPAccessFactory.getHome();

      JPClass jpClassC = jpHome.getParsedClass(history.getCurrentFile());
      System.out.println(jpClassC);
      ;
      for(GTHistoryFile historyFile:history.getHistoryFiles()){
         JPClass jpClass = jpHome.getParsedClass(historyFile.getFile());
         System.out.println(jpClass);
      }
   }

}
