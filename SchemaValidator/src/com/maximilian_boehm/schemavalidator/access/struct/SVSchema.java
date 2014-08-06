package com.maximilian_boehm.schemavalidator.access.struct;

import java.util.Calendar;

public interface SVSchema {

    public void addCondition(SVSchemaCondition condition);
    public void printConditions();
    public void setDate(Calendar c);
    public Calendar getDate();
}
