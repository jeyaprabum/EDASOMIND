package com.maximilian_boehm.javasourceparser.access;

import com.maximilian_boehm.javasourceparser.model.JPHomeImpl;

/**
 * Factory for Accessing JavaSourceParser
 */
public class JPAccessFactory {

    // singleton-member
    private static JPHome home = null;

    /**
     * Get Home of JavaSourceParser
     * @return home
     */
    public static JPHome getHome() {
        if(home==null) home = new JPHomeImpl();
        return home;
    }
}
