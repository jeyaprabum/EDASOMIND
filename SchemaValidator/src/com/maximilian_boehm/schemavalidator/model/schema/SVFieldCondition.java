package com.maximilian_boehm.schemavalidator.model.schema;

import com.maximilian_boehm.javasourceparser.access.struct.JPField;

/**
 * One Condition in the schema
 */
public class SVFieldCondition{

    // members
    private String key;
    private JPField field;

    /**
     * @return
     */
    public JPField getField() {
        return field;
    }

    /**
     * @param field
     */
    public void setField(JPField field) {
        this.field = field;
    }

    /**
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        SVFieldCondition sc = (SVFieldCondition)obj;
        return getKey().equals(sc.getKey());
    }
}
