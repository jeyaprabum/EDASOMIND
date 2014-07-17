package com.maximilian_boehm.schemavalidator.access;

import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaManager;

public interface SVHome {

    public SVSchemaManager createSchemaManager() throws Exception;
}
