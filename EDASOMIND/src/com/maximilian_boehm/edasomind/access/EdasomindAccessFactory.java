package com.maximilian_boehm.edasomind.access;

import com.maximilian_boehm.edasomind.model.EdasomindHomeImpl;

public class EdasomindAccessFactory {

    public static EdasomindHome getHome() {
        return new EdasomindHomeImpl();
    }

}
