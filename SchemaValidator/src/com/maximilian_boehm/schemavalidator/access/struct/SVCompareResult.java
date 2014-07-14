package com.maximilian_boehm.schemavalidator.access.struct;


public interface SVCompareResult {

    public void getResulType();
    public SVSchemaCondition getConditionInput();
    public SVSchemaCondition getConditionCompare();
}
