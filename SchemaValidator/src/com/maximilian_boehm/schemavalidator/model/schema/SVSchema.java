package com.maximilian_boehm.schemavalidator.model.schema;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Max
 *
 */
public class SVSchema{

    // members
    Map<String, SVFieldCondition> map = new HashMap<>();
    private Calendar Date;


    /**
     * @return
     */
    public Map<String, SVFieldCondition> getMap(){
        return map;
    }

    /**
     * @param condition
     */
    public void addCondition(SVFieldCondition condition) {
        map.put(condition.getKey(), condition);
    }

    /**
     * @param sKey
     * @return
     */
    public SVFieldCondition getCondition(String sKey){
        return map.get(sKey);
    }

    /**
     * @param sKey
     * @return
     */
    public boolean hasCondition(String sKey){
        return map.containsKey(sKey);
    }

    /**
     * @return
     */
    public Calendar getDate() {
        return Date;
    }

    /**
     * @param date
     */
    public void setDate(Calendar date) {
        Date = date;
    }

    /**
     * 
     */
    public void printConditions() {
        for(Map.Entry<String,SVFieldCondition> entry : map.entrySet()) {
            String key = entry.getKey();
            SVFieldCondition value = entry.getValue();

            System.out.println(value.getField().getType() + " "+ key);
        }
        System.out.println("------------------------");
    }


}
