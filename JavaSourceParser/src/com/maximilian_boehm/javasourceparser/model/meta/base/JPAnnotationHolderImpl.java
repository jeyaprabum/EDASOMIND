package com.maximilian_boehm.javasourceparser.model.meta.base;

import java.util.ArrayList;
import java.util.List;

import com.maximilian_boehm.javasourceparser.access.struct.base.JPAnnotation;

/**
 * Implementation of an AnnotationHolder
 */
public class JPAnnotationHolderImpl {

    private List<JPAnnotation> listAnnotations;

    /**
     * Add annotation
     * @param annotation
     */
    public void addAnnotation(JPAnnotation annotation){
        if(listAnnotations==null) listAnnotations = new ArrayList<JPAnnotation>();
        listAnnotations.add(annotation);
    }

    /**
     * Set a list of annotations
     */
    public void setAnnotations(List<JPAnnotation> listAnnotations) {
        this.listAnnotations = listAnnotations;
    }

    /**
     * @return all annotations as list
     */
    public List<JPAnnotation> getAnnotations() {
        return listAnnotations;
    }

    /**
     * @return true if there are annotations
     */
    public boolean hasAnnotations(){
        return getAnnotations()!=null;
    }
}