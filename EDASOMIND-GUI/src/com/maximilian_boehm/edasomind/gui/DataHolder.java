package com.maximilian_boehm.edasomind.gui;

import java.util.ArrayList;
import java.util.List;

import com.maximilian_boehm.edasomind.access.struct.EdasomindResultList;

public class DataHolder {

    private final List<DataHolderListener> listListener = new ArrayList<DataHolderListener>();
    private EdasomindResultList resultList = null;

    public void setResult(EdasomindResultList result){
        resultList = result;
        for(DataHolderListener dhl:listListener)
            dhl.dataChanged();
    }

    public EdasomindResultList getResult() {
        return resultList;
    }

    public int getResultRowCount() {
        return resultList==null ? 0 : resultList.size();
    }

    public void registerListener(DataHolderListener listener){
        listListener.add(listener);
    }


}
