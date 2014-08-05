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

    // members
    Map<String, SVSchemaCondition> map = new HashMap<>();
    private Calendar Date;


    /**
     * @return
     */
    public Map<String, SVSchemaCondition> getMap(){
        return map;
    }

    /* (non-Javadoc)
     * @see com.maximilian_boehm.schemavalidator.access.struct.SVSchema#addCondition(com.maximilian_boehm.schemavalidator.access.struct.SVSchemaCondition)
     */
    @Override
    public void addCondition(SVSchemaCondition condition) {
        map.put(condition.getKey(), condition);
    }

    /**
     * @param sKey
     * @return
     */
    public SVSchemaCondition getCondition(String sKey){
        return map.get(sKey);
    }

    /**
     * @param sKey
     * @return
     */
    public boolean hasCondition(String sKey){
        return map.containsKey(sKey);
    }

    /* (non-Javadoc)
     * @see com.maximilian_boehm.schemavalidator.access.struct.SVSchema#getDate()
     */
    @Override
    public Calendar getDate() {
        return Date;
    }

    /* (non-Javadoc)
     * @see com.maximilian_boehm.schemavalidator.access.struct.SVSchema#setDate(java.util.Calendar)
     */
    @Override
    public void setDate(Calendar date) {
        Date = date;
    }

    /* (non-Javadoc)
     * @see com.maximilian_boehm.schemavalidator.access.struct.SVSchema#printConditions()
     */
    @Override
    public void printConditions() {
        for(Map.Entry<String,SVSchemaCondition> entry : map.entrySet()) {
            String key = entry.getKey();
            SVFieldCondition value = (SVFieldCondition)entry.getValue();

            System.out.println(value.getField().getType() + " "+ key);
        }
        System.out.println("------------------------");
    }

    /* (non-Javadoc)
     * @see com.maximilian_boehm.schemavalidator.access.struct.SVSchema#compare(com.maximilian_boehm.schemavalidator.access.struct.SVSchema)
     */
    @Override
    public SVCompareResultTable compare(SVSchema schema) {
        SVSchemaImpl schemaI = (SVSchemaImpl)schema;
        //System.out.println("schema "+getDate().getTime() +" vs. "+schemaI.getDate().getTime());
        SVCompareResultTableImpl tableImpl = new SVCompareResultTableImpl();
        tableImpl.setDateNewFile(getDate());
        tableImpl.setDateOldFile(schemaI.getDate());

        // Iterate over Conditions from Schema #1
        for(Map.Entry<String,SVSchemaCondition> entry:this.getMap().entrySet()) {
            // Get Key
            String sKey = entry.getKey();
            // Get Conditioin
            SVFieldCondition value = (SVFieldCondition)entry.getValue();
            // ######################################
            // Case 1: Field is new
            // ######################################
            if(!schemaI.hasCondition(sKey)){
                SVCompareResultImpl result = new SVCompareResultImpl();
                result.setType(SVCompareResultType.ADD_FIELD);
                result.setFieldName(sKey);
                result.setOldField(value.getField());
                tableImpl.addResult(result);
            }
            // ######################################
            // Case 2: Field is still here, maybe another data-type?
            // ######################################
            else {
                SVFieldCondition valueCompare =  (SVFieldCondition)schemaI.getCondition(sKey);
                // Is it another data-type?
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


        // Iterate over Conditions from Schema #1
        for(Map.Entry<String,SVSchemaCondition> entry:schemaI.getMap().entrySet()) {
            String sKey = entry.getKey();
            SVFieldCondition value = (SVFieldCondition)entry.getValue();
            // ######################################
            // Case 1: Field isn't here anymore
            // ######################################
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
}
