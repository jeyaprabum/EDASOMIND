package com.maximilian_boehm.schemavalidator.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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


}
