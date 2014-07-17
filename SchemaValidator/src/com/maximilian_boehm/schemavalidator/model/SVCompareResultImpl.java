package com.maximilian_boehm.schemavalidator.model;

import com.maximilian_boehm.javasourceparser.access.struct.JPField;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResult;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResultType;

public class SVCompareResultImpl implements SVCompareResult{

    public SVCompareResultImpl() {
    }

    public SVCompareResultImpl(SVCompareResultType t, String s) {
        setFieldName(s);
        setType(t);
    }

    private JPField oldField;
    private JPField newField;
    private SVCompareResultType type;
    private String FieldName;

    public void setOldField(JPField oldField) {
        this.oldField = oldField;
    }

    public void setNewField(JPField newField) {
        this.newField = newField;
    }

    public void setType(SVCompareResultType type) {
        this.type = type;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    @Override
    public JPField getOldField() {
        return oldField;
    }

    @Override
    public JPField getNewField() {
        return newField;
    }

    @Override
    public SVCompareResultType getType() {
        return type;
    }

    @Override
    public String getFieldName() {
        return FieldName;
    }

    @Override
    public boolean equals(Object obj) {
        SVCompareResultImpl svcr = (SVCompareResultImpl)obj;
        return getFieldName().equals(svcr.getFieldName()) && getType() == svcr.getType();
    }

    @Override
    public String toString() {

        return getType()+": "+getFieldName();
    }

}
