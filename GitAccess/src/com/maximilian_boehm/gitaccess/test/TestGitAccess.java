package com.maximilian_boehm.gitaccess.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import com.maximilian_boehm.gitaccess.access.GTAccessFactory;
import com.maximilian_boehm.gitaccess.access.struct.GTHistory;
import com.maximilian_boehm.gitaccess.access.struct.GTHistoryFile;

public class TestGitAccess {

	/**
	 * Retrieve all versions of a file
	 * The file is a simple txt-file which content increments from 0 to 5
	 * Thereby just compare if the retrieved integer is ok
	 * @throws Exception
	 */
	@Test
	public void testGitAccess() throws Exception{
		// Get the test-file
		String sFile = TestGitAccess.class.getResource("testdata/test.txt").getFile();
		
		// Workaround: Get path to src-directory
		sFile = sFile.replace("GitAccess/bin/com/", "GitAccess/src/com/");
		
		// Retrieve history
		GTHistory history = GTAccessFactory.getHome().getGitHistoryOfFile(new File(sFile));

		// Content starts at 0
		int nStart =  0;
		
		Calendar cal = Calendar.getInstance();
		
		// Iterate through history
		for(GTHistoryFile historyFile:history.getHistoryFiles()){
			// Compare content to the counter
			Assert.assertEquals(nStart, getIntFromFile(historyFile.getFile()));
			// The author needs to be set
			Assert.assertEquals("Maximilian Böhm", historyFile.getAuthor());
			// The comment is the counter and "-comment" appended
			Assert.assertEquals(nStart+"-comment", historyFile.getComment());
			// The history is sorted by commit-time. so the time always needs to be smaller than the previous one
			Assert.assertTrue(historyFile.getCommitDate().getTimeInMillis() < cal.getTimeInMillis());
			// set the new date for comparing in next iteration
			cal = historyFile.getCommitDate();
			
			// Increment counter
			nStart = nStart+1;
		}
	}
	
	/**
	 * Retrieve the content from a given file as an integer
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private int getIntFromFile(File f) throws IOException{
		byte[] encoded = Files.readAllBytes(Paths.get(f.getAbsolutePath()));
		return Integer.parseInt(new String(encoded));
	}
}
