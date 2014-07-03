package com.maximilian_boehm.hsregensburg.bachelor.test;

import java.io.File;

import org.junit.Test;

import com.maximilian_boehm.gitaccess.access.GTAccessFactory;
import com.maximilian_boehm.gitaccess.access.struct.GTHistory;
import com.maximilian_boehm.gitaccess.access.struct.GTHistoryFile;
import com.maximilian_boehm.javasourceparser.access.JPAccessFactory;
import com.maximilian_boehm.javasourceparser.access.JPHome;
import com.maximilian_boehm.javasourceparser.access.struct.JPClass;
import com.maximilian_boehm.schemavalidator.access.SVAccessFactory;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchema;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaCondition;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaManager;

public class TestGit {

   @Test
   public void test() throws Exception{
      File f = new File("/Users/Max/Documents/Programmierung/git/MBRepository/MongoTestCase/src/main/java/com/maximilianboehm/hsregensburg/bachelor/Employee.java");
      GTHistory history = GTAccessFactory.getHome().getGitHistoryOfFile(f);

      
      JPHome  jpHome  = JPAccessFactory.getHome();

      JPClass jpClassC = jpHome.getParsedClass(history.getCurrentFile());
      System.out.println(jpClassC);
      
      for(GTHistoryFile historyFile:history.getHistoryFiles()){
    	  JPClass jpClass = jpHome.getParsedClass(historyFile.getFile());
    	  System.out.println(jpClass);
      }
      
      
  	  JPClass jpCurrentClass = jpHome.getParsedClass(history.getCurrentFile());
  	  
  	  SVSchemaManager manager = SVAccessFactory.getSVHome().createSchemaManager();
  	  
  	  
  	  SVSchema schema = manager.createInputSchema();
  	  schema.addCondition(null);
      
      
      for(GTHistoryFile historyFile:history.getHistoryFiles()){
    	  JPClass jpClass = jpHome.getParsedClass(historyFile.getFile());
    	  
    	  SVSchema schema2compare = manager.createSchema2Compare();
    	  schema.addCondition(null);
    	  System.out.println(jpClass);
      }
   }

}
