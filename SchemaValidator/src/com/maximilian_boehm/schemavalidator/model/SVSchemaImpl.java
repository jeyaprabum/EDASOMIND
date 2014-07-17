package com.maximilian_boehm.schemavalidator.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResultTable;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResultType;
import com.maximilian_boehm.schemavalidator.access.struct.SVFieldCondition;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchema;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaCondition;

public class SVSchemaImpl implements SVSchema{

    Map<String, SVSchemaCondition> map = new HashMap<>();

    public Map<String, SVSchemaCondition> getMap(){
        return map;
    }

    @Override
    public void addCondition(SVSchemaCondition condition) {
        map.put(condition.getKey(), condition);
    }

    public SVSchemaCondition getCondition(String sKey){
        return map.get(sKey);
    }

    public boolean hasCondition(String sKey){
        return map.containsKey(sKey);
    }

    @Override
    public void printConditions() {
        for(Map.Entry<String,SVSchemaCondition> entry : map.entrySet()) {
            String key = entry.getKey();
            SVFieldCondition value = (SVFieldCondition)entry.getValue();

            //System.out.println(value.getField().getType() + " "+ key);
        }
        //System.out.println("------------------------");
    }

    @Override
    public SVCompareResultTable compare(SVSchema schema) {
        SVSchemaImpl schemaI = (SVSchemaImpl)schema;
        //System.out.println("schema "+getDate().getTime() +" vs. "+schemaI.getDate().getTime());
        SVCompareResultTableImpl tableImpl = new SVCompareResultTableImpl();
        tableImpl.setDateNewFile(getDate());
        tableImpl.setDateOldFile(schemaI.getDate());

        for(Map.Entry<String,SVSchemaCondition> entry:map.entrySet()) {
            String sKey = entry.getKey();
            SVFieldCondition value = (SVFieldCondition)entry.getValue();

            // Case 1: Field isn't here anymore
            if(!schemaI.hasCondition(sKey)){
                SVCompareResultImpl result = new SVCompareResultImpl();
                result.setType(SVCompareResultType.NEW_FIELD);
                result.setFieldName(sKey);
                result.setOldField(value.getField());

                tableImpl.addResult(result);
            } else {

                SVFieldCondition valueCompare =  (SVFieldCondition)schemaI.getCondition(sKey);
                // Datentyp geändert
                if(!valueCompare.getField().getType().equals(value.getField().getType())){
                    SVCompareResultImpl result = new SVCompareResultImpl();
                    result.setType(SVCompareResultType.CHANGE_FIELD);
                    result.setFieldName(sKey);
                    result.setOldField(value.getField());
                    result.setNewField(valueCompare.getField());

                    tableImpl.addResult(result);
                }
            }
        }

        for(Map.Entry<String,SVSchemaCondition> entry:schemaI.getMap().entrySet()) {
            String sKey = entry.getKey();
            SVFieldCondition value = (SVFieldCondition)entry.getValue();

            // Case 1: Field isn't here anymore
            if(!hasCondition(sKey)){
                SVCompareResultImpl result = new SVCompareResultImpl();
                result.setType(SVCompareResultType.REMOVE_FIELD);
                result.setFieldName(sKey);
                result.setOldField(value.getField());

                tableImpl.addResult(result);
            }
        }

        return tableImpl;
    }


    private Calendar Date;

    @Override
    public Calendar getDate() {
        return Date;
    }

    @Override
    public void setDate(Calendar date) {
        Date = date;
    }

}
