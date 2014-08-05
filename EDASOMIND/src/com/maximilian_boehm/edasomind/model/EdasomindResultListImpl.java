package com.maximilian_boehm.edasomind.model;

import java.util.ArrayList;

import com.maximilian_boehm.edasomind.access.struct.EdasomindResult;
import com.maximilian_boehm.edasomind.access.struct.EdasomindResultList;
import com.maximilian_boehm.edasomind.access.struct.EdasomindSignificance;


/**
 * List for Results
 */
public class EdasomindResultListImpl extends ArrayList<EdasomindResult> implements EdasomindResultList {

    private static final long serialVersionUID = 1L;

    /**
     * Create new result
     * @return
     */
    public EdasomindResult createResult(){
        EdasomindResult result = new EdasomindResultImpl();
        add(result);
        return result;
    }

    /**
     * Add a new result for testing purpose
     * @param sig
     * @param calFrom
     * @param calTo
     * @param sMsg
     */
    public void addTestResult(EdasomindSignificance sig, long calFrom, long calTo, String sMsg){
        EdasomindResultImpl result = new EdasomindResultImpl();
        result.setAllData(sig, calFrom, calTo, sMsg);
        add(result);
    }

    @Override
    public String toString() {
        String s = "";
        for(EdasomindResult result:this)
            s+= result.getCalendarFrom().getTime()+" to "+result.getCalendarTo().getTime()+": "+result.getSignificance()+": "+result.getMessage()+"\n";
        return s;
    }

}
