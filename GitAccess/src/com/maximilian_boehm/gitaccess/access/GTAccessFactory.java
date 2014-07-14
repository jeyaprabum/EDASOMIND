package com.maximilian_boehm.gitaccess.access;

import com.maximilian_boehm.gitaccess.model.GTHomeImpl;

/**
 * Factory for Retrieving the Home of GitAccess
 */
public class GTAccessFactory {

    /**
     * Get GitAccess-Home
     * @return
     */
    public static GTHome getHome(){
        return new GTHomeImpl();
    }

}
