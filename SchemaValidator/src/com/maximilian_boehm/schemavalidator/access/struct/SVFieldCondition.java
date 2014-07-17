package com.maximilian_boehm.schemavalidator.access.struct;

import com.maximilian_boehm.javasourceparser.access.struct.JPField;

public class SVFieldCondition implements SVSchemaCondition{

    private String key;
    private JPField field;

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

    @Override
    public void setValue(Object o) {
        // TODO Auto-generated method stub

    }

    @Override
    public Object getValue() {
        // TODO Auto-generated method stub
        return null;
    }
}
