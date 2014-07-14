package com.maximilian_boehm.gitaccess.model;

import java.io.File;
import java.util.Calendar;

import com.maximilian_boehm.gitaccess.access.struct.GTHistoryFile;

/**
 * Implementation of the interface
 */
public class GTHistoryFileImpl implements GTHistoryFile{

    // members
    private File file;
    private String Author;
    private String Comment;
    private Calendar commitDate;

    @Override
    public File getFile() {return file;}
    public void setFile(File file) {this.file = file;}

    @Override
    public String getAuthor() {return Author;}
    public void setAuthor(String author) {Author = author;}

    @Override
    public String getComment() {return Comment;}
    public void setComment(String comment) {Comment = comment;}

    @Override
    public Calendar getCommitDate() {return commitDate;}
    public void setCommitDate(Calendar commitDate) {this.commitDate = commitDate;}
}
