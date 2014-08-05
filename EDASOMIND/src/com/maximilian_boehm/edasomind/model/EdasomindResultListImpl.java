package com.maximilian_boehm.edasomind.model;

import java.util.ArrayList;

import com.maximilian_boehm.edasomind.access.struct.EdasomindResult;
import com.maximilian_boehm.edasomind.access.struct.EdasomindResultList;


public class EdasomindResultListImpl extends ArrayList<EdasomindResult> implements EdasomindResultList {

    private static final long serialVersionUID = 1L;

    public EdasomindResult createResult(){
        EdasomindResult result = new EdasomindResultImpl();
        add(result);
        return result;
    }

    @Override
    public String toString() {
        String s = "";
        for(EdasomindResult result:this)
            s+= result.getCalendarFrom().getTime()+" to "+result.getCalenderTo().getTime()+": "+result.getSignificance()+": "+result.getMessage()+"\n";
        return s;
    }

}
