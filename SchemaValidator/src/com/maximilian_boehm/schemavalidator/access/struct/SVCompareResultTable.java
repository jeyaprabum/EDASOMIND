package com.maximilian_boehm.schemavalidator.access.struct;

import java.util.Calendar;
import java.util.List;

public interface SVCompareResultTable {

    public boolean hasResults();
    public List<SVCompareResult> getResults();

    public Calendar getDateOldFile();
    public Calendar getDateNewFile();

}
