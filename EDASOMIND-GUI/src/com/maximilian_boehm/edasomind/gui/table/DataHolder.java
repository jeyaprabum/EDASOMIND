package com.maximilian_boehm.edasomind.gui.table;

import java.util.ArrayList;
import java.util.List;

import com.maximilian_boehm.edasomind.access.struct.EdasomindResultList;

public class DataHolder {

    // List for all listener
    private final List<DataHolderListener> listListener = new ArrayList<DataHolderListener>();
    // Save result
    private EdasomindResultList resultList = null;

    /**
     * Set Result
     * @param result
     */
    public void setResult(EdasomindResultList result){
        resultList = result;
        // notify all listener
        for(DataHolderListener dhl:listListener)
            dhl.dataChanged();
    }

    /**
     * get result
     * @return
     */
    public EdasomindResultList getResult() {
        return resultList;
    }

    /**
     * get number of results
     * @return
     */
    public int getResultRowCount() {
        return resultList==null ? 0 : resultList.size();
    }

    /**
     * Register a listener
     * @param listener
     */
    public void registerListener(DataHolderListener listener){
        listListener.add(listener);
    }


}
