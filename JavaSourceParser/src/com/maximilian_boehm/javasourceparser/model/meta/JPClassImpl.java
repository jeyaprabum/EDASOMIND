package com.maximilian_boehm.javasourceparser.model.meta;

import java.util.ArrayList;
import java.util.List;

import com.maximilian_boehm.javasourceparser.access.struct.JPAnnotation;
import com.maximilian_boehm.javasourceparser.access.struct.JPClass;
import com.maximilian_boehm.javasourceparser.access.struct.JPField;
import com.maximilian_boehm.javasourceparser.model.meta.base.JPAnnotationHolderImpl;

public class JPClassImpl extends JPAnnotationHolderImpl implements JPClass{

    private String ClassName;
    private String PackageName;
    private List<JPField>      listFields;

    public JPClassImpl() {
        listFields = new ArrayList<JPField>();
    }

    @Override
    public String getClassName() {
        return ClassName;
    }
    public void setClassName(String className) {
        ClassName = className;
    }


    @Override
    public String getPackageName() {
        return PackageName;
    }
    public void setPackageName(String packageName) {
        PackageName = packageName;
    }


    public void addField(JPField field){
        listFields.add(field);
    }

    public void setFields(List<JPField> listFields) {
        this.listFields = listFields;
    }
    @Override
    public List<JPField> getFields() throws Exception {
        return listFields;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {

            appendLB(sb, getPackageName());

            if(hasAnnotations())
                for(JPAnnotation anno:getAnnotations()){
                    appendLB(sb, "@"+anno.getType());
                    for(String s:anno.getAttributes().keySet())
                        appendLB(sb, "  "+s+"="+anno.getAttributes().get(s));
                }

            appendLB(sb, getClassName()+"{");

            for(JPField field:getFields()){
                if(field.hasAnnotations())
                    for(JPAnnotation anno:field.getAnnotations()){
                        appendLB(sb, "   "+"@"+anno.getType());
                        if(anno.hasAttributes())
                            for(String s:anno.getAttributes().keySet())
                                appendLB(sb, "   "+"  "+s+"="+anno.getAttributes().get(s));
                    }
                appendLB(sb, "   "+field.getType()+" "+field.getName());
            }
            appendLB(sb, "}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void appendLB(StringBuilder sb, String s){sb.append(s+System.getProperty("line.separator"));}


}
