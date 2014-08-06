package com.maximilian_boehm.gitaccess.model;

import java.io.File;
import java.util.Calendar;

import com.maximilian_boehm.gitaccess.access.struct.GTHistoryFile;

/**
 * Implementation of the interface
 */
public class GTHistoryFileImpl implements GTHistoryFile{

    // members
    private File     file;
    private String   sAuthor;
    private String   sComment;
    private Calendar commitDate;

    @Override
    public File getFile() {return file;}
    public void setFile(File file) {this.file = file;}

    @Override
    public String getAuthor() {return sAuthor;}
    public void setAuthor(String author) {sAuthor = author;}

    @Override
    public String getComment() {return sComment;}
    public void setComment(String comment) {sComment = comment;}

    @Override
    public Calendar getCommitDate() {return commitDate;}
    public void setCommitDate(Calendar commitDate) {this.commitDate = commitDate;}
}
