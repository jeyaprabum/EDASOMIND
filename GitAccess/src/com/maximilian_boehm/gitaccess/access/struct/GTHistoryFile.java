package com.maximilian_boehm.gitaccess.access.struct;

import java.io.File;
import java.util.Calendar;

public interface GTHistoryFile {

	public File getFile() throws Exception;
	public String getAuthor();
	public String getComment();
	public Calendar getCommitDate();


}
