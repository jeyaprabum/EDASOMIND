package com.maximilian_boehm.schemavalidator.access.struct;

import com.maximilian_boehm.javasourceparser.access.struct.JPField;

/**
 * One Condition in the schema
 */
public class SVFieldCondition implements SVSchemaCondition{

    // members
    private String key;
    private JPField field;

    /**
     * @return
     */
    public JPField getField() {
        return field;
    }

    public void setField(JPField field) {
        this.field = field;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    @Override
    public boolean equals(Object obj) {
        SVFieldCondition sc = (SVFieldCondition)obj;
        return getKey().equals(sc.getKey());
    }
}
