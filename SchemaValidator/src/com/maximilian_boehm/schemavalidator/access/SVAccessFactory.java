package com.maximilian_boehm.schemavalidator.access;

import com.maximilian_boehm.schemavalidator.model.SVHomeImpl;


public class SVAccessFactory {

    public static SVHome getSVHome() {
        return new SVHomeImpl();
    }

}
