package com.maximilian_boehm.schemavalidator.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.maximilian_boehm.javasourceparser.access.JPAccessFactory;
import com.maximilian_boehm.javasourceparser.access.struct.JPClass;
import com.maximilian_boehm.javasourceparser.access.struct.JPField;
import com.maximilian_boehm.javasourceparser.access.struct.base.JPAnnotation;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResult;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResultTable;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResultType;
import com.maximilian_boehm.schemavalidator.access.struct.SVFieldCondition;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchema;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaCondition;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaManager;

public class SVSchemaManagerImpl implements SVSchemaManager{

    // member
    private final List<SVSchema> listSchema = new ArrayList<SVSchema>();
    private final boolean bDebug = false;

    /* (non-Javadoc)
     * @see com.maximilian_boehm.schemavalidator.access.struct.SVSchemaManager#addSchemaByFile(java.io.File, java.util.Calendar)
     */
    @Override
    public void addSchemaByFile(File f, Calendar date) throws Exception {
        JPClass jpClass = JPAccessFactory.getHome().getParsedClass(f);
        addSchemaByFile(jpClass, date);
    }

    /* (non-Javadoc)
     * @see com.maximilian_boehm.schemavalidator.access.struct.SVSchemaManager#addSchemaByFile(com.maximilian_boehm.javasourceparser.access.struct.JPClass, java.util.Calendar)
     */
    @Override
    public void addSchemaByFile(JPClass jpClass, Calendar date) throws Exception {
        // Create new Schema
        SVSchema schema = new SVSchemaImpl();
        // Set date
        schema.setDate(date);

        // iterate over fields
        for(JPField field:jpClass.getFields()){
            // has the field annotations?
            if(field.hasAnnotations())
                // iterate over annotations
                for(JPAnnotation anno:field.getAnnotations()){
                    // is it the "AlsoLoad"-Annotation?
                    if(anno.getType().equals("AlsoLoad")){
                        // retrieve the field which should get loaded additionally
                        String value = anno.getAttributes().get("value");
                        // create a new condition
                        SVFieldCondition cond = new SVFieldCondition();
                        cond.setKey(value);
                        cond.setField(field);
                        // Add the condition to the schema
                        schema.addCondition(cond);
                    }
                }

            // create condition for the variable
            SVFieldCondition cond = new SVFieldCondition();
            // set name of variable as key
            cond.setKey(field.getName());
            cond.setField(field);

            // add condition to schema
            schema.addCondition(cond);
        }
        // add the schema to the list of conditions
        listSchema.add(schema);

        // print the conditions
        if(bDebug)
            schema.printConditions();
    }

    /* (non-Javadoc)
     * @see com.maximilian_boehm.schemavalidator.access.struct.SVSchemaManager#compareSchemata()
     */
    @Override
    public List<SVCompareResultTable> compareSchemata() throws Exception {
        // Create new List for ResultTables
        List<SVCompareResultTable> listResults = new ArrayList<SVCompareResultTable>();

        // Get Iterator from the Schema-List
        ListIterator<SVSchema> it = listSchema.listIterator(listSchema.size());

        // ##################################################
        // ##################################################
        // Create Pairs of Schema for Comparison, e.g.
        //    Schema 1 & Schema 2
        //    Schema 2 & Schema 3
        //    Schema 3 & Schema 4
        // And so on ...
        // During this pairing, compare them against each other
        // ##################################################
        // ##################################################

        // placeholder for prev. schema
        SVSchemaImpl schemaprevious = null;
        // as long, as there is a schema
        while (it.hasPrevious()){
            // set current schema
            SVSchemaImpl schemaCurrent = (SVSchemaImpl)it.previous();

            // no previous schema yet?
            if(schemaprevious==null)
                // set previous schema to current
                schemaprevious = schemaCurrent;
            else {
                // Already a previous schema set?
                // set a pair to compare (previous & current
                compare(schemaCurrent, schemaprevious, listResults);
                // set previous schema to current
                schemaprevious = schemaCurrent;
            }
        }

        // return list of tables with results
        return listResults;
    }

    /**
     * @param schemaNEW
     * @param schemaOLD
     * @param listResults
     */
    public void compare(SVSchemaImpl schemaNEW, SVSchemaImpl schemaOLD, List<SVCompareResultTable> listResults) {
        //System.out.println("schema "+schemaOLD.getDate().getTime() +" vs. "+schemaNEW.getDate().getTime());
        SVCompareResultTableImpl tableImpl = new SVCompareResultTableImpl();
        tableImpl.setDateNewFile(schemaNEW.getDate());
        tableImpl.setDateOldFile(schemaOLD.getDate());

        // Iterate over Conditions from the NEW Schema
        for(Map.Entry<String,SVSchemaCondition> entry:schemaNEW.getMap().entrySet()) {
            // Get Key
            String sKey = entry.getKey();
            // Get Condition
            SVFieldCondition value = (SVFieldCondition)entry.getValue();

            // Field not present in old schema?
            if(!schemaOLD.hasCondition(sKey)){
                // ######################################
                // Case 1: Field got reintroduced
                // ######################################
                if(wasFieldPreviouslyRemoved(sKey, listResults))
                    addResult(tableImpl, sKey, null, null, SVCompareResultType.REINTRODUCE);

                // ######################################
                // Case 2: Field got added
                // ######################################
                addResult(tableImpl, sKey, null, value.getField(), SVCompareResultType.ADD_FIELD);

            }
            // ######################################
            // Case 3: Field exists, but maybe another data-type?
            // ######################################
            else {
                SVFieldCondition valueCompare =  (SVFieldCondition)schemaOLD.getCondition(sKey);
                // Is it another data-type?
                if(!valueCompare.getField().getType().equals(value.getField().getType()))
                    addResult(tableImpl, sKey, valueCompare.getField(), value.getField(), SVCompareResultType.CHANGE_FIELD);
            }
        }

        // Remove all entries from schemaNEW from schemaOLD because they already
        // have been handled
        schemaOLD.getMap().entrySet().removeAll(schemaNEW.getMap().entrySet());

        // Iterate over Conditions from Schema #1
        for(Map.Entry<String,SVSchemaCondition> entry:schemaOLD.getMap().entrySet()) {
            String sKey = entry.getKey();
            SVFieldCondition value = (SVFieldCondition)entry.getValue();
            // ######################################
            // Case 4: Field isn't here anymore
            // ######################################
            addResult(tableImpl, sKey, null, value.getField(), SVCompareResultType.REMOVE_FIELD);
        }

        listResults.add(tableImpl);
    }

    /**
     * Check if the field has been removed previously
     * @param sField
     * @param listResults
     * @return
     */
    private boolean wasFieldPreviouslyRemoved(String sField, List<SVCompareResultTable> listResults) {
        // Iterate over all result-tables
        for(SVCompareResultTable resultTable:listResults)
            // are there results in the table?
            if(resultTable.hasResults())
                // iterate over the results
                for(SVCompareResult result:resultTable.getResults())
                    // was the field removed?
                    if(result.getFieldName().equals(sField) && result.getType() == SVCompareResultType.REMOVE_FIELD)
                        return true;
        return false;
    }

    /**
     * Create and add a new result
     * @param tableImpl
     * @param sKey
     * @param fieldNEW
     * @param fieldOLD
     * @param type
     */
    private void addResult(SVCompareResultTableImpl tableImpl, String sKey, JPField fieldNEW, JPField fieldOLD, SVCompareResultType type){
        SVCompareResultImpl result = new SVCompareResultImpl();
        result.setType(type);
        result.setFieldName(sKey);
        result.setOldField(fieldOLD);
        result.setNewField(fieldNEW);
        tableImpl.addResult(result);
    }

}
