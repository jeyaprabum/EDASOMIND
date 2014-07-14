package com.maximilian_boehm.schemavalidator.access.struct;

public interface SVSchemaManager {


    public SVSchema createInputSchema();
    public SVSchema createSchema2Compare();
    public SVCompareResult compare(SVSchema schema, SVSchema schemacompare);

}
