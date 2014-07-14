package com.maximilian_boehm.schemavalidator.access.struct;

public interface SVSchema {

    public void addCondition(SVSchemaCondition condition);
    public SVCompareResult compare(SVSchema schema);
    public boolean containsCondition(SVSchemaCondition condition);
    public void printConditions();

}
