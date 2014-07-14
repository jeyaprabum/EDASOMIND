package com.maximilian_boehm.schemavalidator.model;

import java.util.Comparator;
import java.util.TreeSet;

import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResult;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchema;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaCondition;

public class SVSchemaImpl implements SVSchema{

    TreeSet<SVSchemaCondition> setCondition = new TreeSet<>(new Comparator<SVSchemaCondition>() {
        @Override
        public int compare(SVSchemaCondition o1, SVSchemaCondition o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    });

    @Override
    public void addCondition(SVSchemaCondition condition) {
        setCondition.add(condition);
    }

    @Override
    public void printConditions() {
        for(SVSchemaCondition ssc:setCondition)
            System.out.println(ssc.getKey());
    }

    @Override
    public SVCompareResult compare(SVSchema schema) {
        SVSchemaImpl schemaI = (SVSchemaImpl)schema;


        return null;
    }

    @Override
    public boolean containsCondition(SVSchemaCondition condition) {
        return setCondition.contains(condition);
    }


}
