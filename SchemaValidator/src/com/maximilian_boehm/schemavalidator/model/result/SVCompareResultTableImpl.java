package com.maximilian_boehm.schemavalidator.model.result;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResult;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResultTable;

public class SVCompareResultTableImpl implements SVCompareResultTable {


    List<SVCompareResult> listResults = new ArrayList<SVCompareResult>();
    private Calendar DateOldFile;
    private Calendar DateNewFile;

    @Override
    public boolean hasResults() {
        return !getResults().isEmpty();
    }

    @Override
    public List<SVCompareResult> getResults() {
        return listResults;
    }

    public void addResult(SVCompareResult result){
        listResults.add(result);
    }

    @Override
    public Calendar getDateOldFile() {
        return DateOldFile;
    }

    public void setDateOldFile(Calendar dateOldFile) {
        DateOldFile = dateOldFile;
    }

    @Override
    public Calendar getDateNewFile() {
        return DateNewFile;
    }

    public void setDateNewFile(Calendar dateNewFile) {
        DateNewFile = dateNewFile;
    }



}
