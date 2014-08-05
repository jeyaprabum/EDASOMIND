package com.maximilian_boehm.schemavalidator.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.maximilian_boehm.javasourceparser.access.JPAccessFactory;
import com.maximilian_boehm.javasourceparser.access.struct.JPAnnotation;
import com.maximilian_boehm.javasourceparser.access.struct.JPClass;
import com.maximilian_boehm.javasourceparser.access.struct.JPField;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResult;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResultTable;
import com.maximilian_boehm.schemavalidator.access.struct.SVCompareResultType;
import com.maximilian_boehm.schemavalidator.access.struct.SVFieldCondition;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchema;
import com.maximilian_boehm.schemavalidator.access.struct.SVSchemaManager;

public class SVSchemaManagerImpl implements SVSchemaManager{

    // member
    private final List<SVSchema> listSchema = new ArrayList<SVSchema>();
    private final boolean bDebug = true;

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
        Iterator<SVSchema> it = listSchema.iterator();

        // ##################################################
        // ##################################################
        // Step #1
        // Create Pairs of Schema for Comparison, e.g.
        //    Schema 1 & Schema 2
        //    Schema 2 & Schema 3
        //    Schema 3 & Schema 4
        // And so on ...
        // During this pairing, compare them against each other
        // ##################################################
        // ##################################################

        // placeholder for prev. schema
        SVSchema schemaprevious = null;
        // as long, as there is a schema
        while (it.hasNext()){
            // set current schema
            SVSchema schemaCurrent = it.next();

            // no previous schema yet?
            if(schemaprevious==null)
                // set previous schema to current
                schemaprevious = schemaCurrent;
            else {
                // Already a previous schema set?
                // set a pair to compare (previous & current
                listResults.add(schemaprevious.compare(schemaCurrent));
                // set previous schema to current
                schemaprevious = schemaCurrent;
            }
        }

        // list for storing previous deleted fields
        List<String> listPrevDeleted = new ArrayList<>();

        // Get Iterator
        ListIterator<SVCompareResultTable> li = listResults.listIterator(listResults.size());

        // Save the results to add afterwards concerning concurrency problems
        Map<SVCompareResultTable, List<SVCompareResult>> mapAddAfterwards = new HashMap<>();

        // Run through results in backward order
        while (li.hasPrevious()){
            // Get resultTable
            SVCompareResultTable resultTable = li.previous();

            // Debug
            debug("COMPARE ");
            debug(resultTable.getDateNewFile().getTime());
            debug(resultTable.getDateOldFile().getTime());

            // Iterate over single results
            for(SVCompareResult result:resultTable.getResults()){
                // Is it a remove-field?
                if(result.getType() == SVCompareResultType.REMOVE_FIELD)
                    // Remember it!
                    listPrevDeleted.add(result.getFieldName());

                // Are you a new field and have you been deleted previously?
                if(result.getType() == SVCompareResultType.ADD_FIELD && listPrevDeleted.contains(result.getFieldName())){
                    // Create new result
                    SVCompareResultImpl resultReintroduce = new SVCompareResultImpl();
                    // Set name of affected field
                    resultReintroduce.setFieldName(result.getFieldName());
                    // set type
                    resultReintroduce.setType(SVCompareResultType.REINTRODUCE);

                    // ResultTable doesn't exist yet in map? this is necessary because of concurrency problems
                    if(mapAddAfterwards.get(resultTable)==null)
                        // create it!
                        mapAddAfterwards.put(resultTable, new ArrayList<SVCompareResult>());

                    // add new result
                    mapAddAfterwards.get(resultTable).add(resultReintroduce);

                    debug("Reintroducing was detected: "+result.getFieldName());
                }
                debug(result.getType()+": "+result.getFieldName());
            }
            debug("=====================");
        }

        // Add new Results afterwards to main table
        for(SVCompareResultTable tableResult:mapAddAfterwards.keySet())
            tableResult.getResults().addAll(mapAddAfterwards.get(tableResult));

        // return list of tables with results
        return listResults;
    }

    private void debug(Object o){
        if(bDebug)System.out.println(o);
    }

}
