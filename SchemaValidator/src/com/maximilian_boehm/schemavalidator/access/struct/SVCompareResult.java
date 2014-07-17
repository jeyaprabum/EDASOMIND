package com.maximilian_boehm.schemavalidator.access.struct;

import com.maximilian_boehm.javasourceparser.access.struct.JPField;

public interface SVCompareResult {

    public String getFieldName();
    public SVCompareResultType getType();
    public JPField getOldField();
    public JPField getNewField();

}
