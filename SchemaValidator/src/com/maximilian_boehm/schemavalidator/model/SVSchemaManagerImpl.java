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


    List<SVSchema> listSchema = new ArrayList<SVSchema>();


    @Override
    public void addSchemaByFile(File f, Calendar date) throws Exception {
        JPClass jpClass = JPAccessFactory.getHome().getParsedClass(f);

        SVSchema schema = new SVSchemaImpl();
        schema.setDate(date);

        for(JPField field:jpClass.getFields()){
            if(field.hasAnnotations())
                for(JPAnnotation anno:field.getAnnotations()){
                    if(anno.getType().equals("AlsoLoad")){
                        String value = anno.getAttributes().get("value");
                        SVFieldCondition cond = new SVFieldCondition();
                        cond.setKey(value);
                        cond.setField(field);

                        schema.addCondition(cond);
                    }
                }


            SVFieldCondition cond = new SVFieldCondition();
            cond.setKey(field.getName());
            cond.setField(field);

            schema.addCondition(cond);

        }
        listSchema.add(schema);

        schema.printConditions();
    }

    @Override
    public List<SVCompareResultTable> compareSchemata() throws Exception {
        List<SVCompareResultTable> listResults = new ArrayList<SVCompareResultTable>();

        // Iterator wird angefordert
        Iterator<SVSchema> it = listSchema.iterator();
        SVSchema schemaprevious = null;
        while (it.hasNext()){
            SVSchema schemaCurrent = it.next();

            if(schemaprevious==null)
                schemaprevious = schemaCurrent;
            else {
                listResults.add(schemaprevious.compare(schemaCurrent));
                schemaprevious = schemaCurrent;
            }
        }

        List<String> listPrevDeleted = new ArrayList<>();
        ListIterator<SVCompareResultTable> li = listResults.listIterator(listResults.size());

        Map<SVCompareResultTable, List<SVCompareResult>> mapAddAfterwards = new HashMap<>();

        while (li.hasPrevious()){
            SVCompareResultTable resultTable = li.previous();
            //System.out.println("COMPARE ");
            //System.out.println(resultTable.getDateNewFile().getTime());
            //System.out.println(resultTable.getDateOldFile().getTime());
            for(SVCompareResult result:resultTable.getResults()){
                if(result.getType() == SVCompareResultType.REMOVE_FIELD)
                    listPrevDeleted.add(result.getFieldName());


                if(result.getType() == SVCompareResultType.NEW_FIELD && listPrevDeleted.contains(result.getFieldName())){
                    SVCompareResultImpl resultReintroduce = new SVCompareResultImpl();
                    resultReintroduce.setFieldName(result.getFieldName());
                    resultReintroduce.setType(SVCompareResultType.REINTRODUCE);


                    if(mapAddAfterwards.get(resultTable)==null)
                        mapAddAfterwards.put(resultTable, new ArrayList<SVCompareResult>());
                    mapAddAfterwards.get(resultTable).add(resultReintroduce);
                    //System.out.println("ACHTUNG: WIEDEREINFUEHREN: "+result.getFieldName());
                }
                //System.out.println(result.getType()+": "+result.getFieldName());
            }
            //System.out.println("=====================");
        }

        for(SVCompareResultTable tableResult:mapAddAfterwards.keySet()){
            List<SVCompareResult> toaddlist = mapAddAfterwards.get(tableResult);
            tableResult.getResults().addAll(toaddlist);
        }

        return listResults;
    }


}
