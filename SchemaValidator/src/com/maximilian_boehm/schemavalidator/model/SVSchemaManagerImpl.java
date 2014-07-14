package com.maximilian_boehm.schemavalidator.model;

import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResult;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchema;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaManager;

public class SVSchemaManagerImpl implements SVSchemaManager{

    @Override
    public SVSchema createInputSchema() {
        // TODO Auto-generated method stub
        return new SVSchemaImpl();
    }

    @Override
    public SVSchema createSchema2Compare() {
        // TODO Auto-generated method stub
        return createInputSchema();
    }

    @Override
    public SVCompareResult compare(SVSchema schema, SVSchema schema2compare) {
        return schema.compare(schema2compare);
    }

}
