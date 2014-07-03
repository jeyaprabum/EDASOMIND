package com.maximilian_boehm.hsregensburg.bachelor.test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.maximilian_boehm.gitaccess.access.GTAccessFactory;
import com.maximilian_boehm.gitaccess.access.GTHome;
import com.maximilian_boehm.gitaccess.access.struct.GTHistory;
import com.maximilian_boehm.gitaccess.access.struct.GTHistoryFile;
import com.maximilian_boehm.javasourceparser.access.JPAccessFactory;
import com.maximilian_boehm.javasourceparser.access.JPHome;
import com.maximilian_boehm.javasourceparser.access.struct.JPClass;
import com.maximilian_boehm.javasourceparser.access.struct.JPField;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class TestCLS {

	public static void main(String[] args) throws Exception{
		GTHome gitHome = GTAccessFactory.getHome();
		JPHome  jpHome = JPAccessFactory.getHome();
//		File f = new File("/Users/Max/Documents/Programmierung/git/MBRepository/MongoTestCase/src/main/java/com/maximilianboehm/hsregensburg/bachelor/errorcases/ReintroduceAttribute.java");
		File f = new File("/Users/Max/Documents/Programmierung/git/MBRepository/MongoTestCase/src/main/java/com/maximilianboehm/hsregensburg/bachelor/Employee.java");
//		File f = new File("/Users/Max/Documents/Programmierung/git/MBRepository/MongoTestCase/src/main/java/com/maximilianboehm/hsregensburg/bachelor/errorcases/DeleteAttribute.java");
//		File f = new File("/Users/Max/Documents/Programmierung/git/MBRepository/MongoTestCase/src/main/java/com/maximilianboehm/hsregensburg/bachelor/errorcases/RenameAttribute.java");
//		File f = new File("/Users/Max/Documents/Programmierung/git/MBRepository/MongoTestCase/src/main/java/com/maximilianboehm/hsregensburg/bachelor/errorcases/TypeError.java");
		
		long lTime = System.currentTimeMillis();
        GTHistory history = gitHome.getGitHistoryOfFile(f);
        System.out.println("Getting GIT History: "+(System.currentTimeMillis()-lTime)+" ms");

  
		long lTimeParser = System.currentTimeMillis();
        JPClass jpClassC = jpHome.getParsedClass(history.getCurrentFile());
        System.out.println("Parsing Java Source File: "+(System.currentTimeMillis()-lTimeParser)+" ms");

        Set<String> setWarnings = new HashSet<>();
        
		long lTimeCompare = System.currentTimeMillis();

        for(GTHistoryFile historyFile:history.getHistoryFiles()){
        	System.out.println("CHECK FILE");
        	JPClass jpClass = jpHome.getParsedClass(historyFile.getFile());
        
        	for(JPField field:jpClass.getFields())
        		if(!jpClassC.getFields().contains(field))
        			setWarnings.add("The field "+field.getName()+" is in an older version but not in the current file");
        }
        
        System.out.println("Comparing: "+(System.currentTimeMillis()-lTimeCompare)+" ms");

        for(String s:setWarnings)
        	System.out.println(s);	
        
        System.out.println("Whole process: "+(System.currentTimeMillis()-lTime)+" ms");
	}
	
	

}
