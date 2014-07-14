package com.maximilian_boehm.schemavalidator.access.struct;

public class SVSimpleCondition implements SVSchemaCondition{

    private String key;

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        SVSimpleCondition sc = (SVSimpleCondition)obj;
        return getKey().equals(sc.getKey());
    }
}
