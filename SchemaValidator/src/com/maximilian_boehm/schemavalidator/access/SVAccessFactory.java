package com.maximilian_boehm.schemavalidator.access;

import com.maximilian_boehm.schemavalidator.model.SVHomeImpl;


/**
 * Factory for Accessing SchemaValidator
 */
public class SVAccessFactory {

    // singleton-member
    private static SVHome home = null;

    /**
     * @return home
     */
    public static SVHome getSVHome() {
        if(home==null) home = new SVHomeImpl();
        return home;
    }

}
