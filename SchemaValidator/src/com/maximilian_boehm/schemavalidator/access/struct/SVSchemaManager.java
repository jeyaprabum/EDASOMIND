package com.maximilian_boehm.schemavalidator.access.struct;

import java.io.File;
import java.util.Calendar;
import java.util.List;

public interface SVSchemaManager {

    public void addSchemaByFile(File f, Calendar date) throws Exception;
    public List<SVCompareResultTable> compareSchemata() throws Exception;

}
