package com.maximilian_boehm.edasomind.gui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.table.AbstractTableModel;

import com.maximilian_boehm.edasomind.access.struct.EdasomindResult;

public class TableModel extends AbstractTableModel implements DataHolderListener{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    DataHolder dataholder = new DataHolder();
    public TableModel(DataHolder dh) {
        dataholder = dh;
        dh.registerListener(this);
    }

    @Override
    public void dataChanged(){
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return dataholder.getResultRowCount();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        if(column==0) return "Commit";
        else if(column==1) return "Commit";
        else if(column==2) return "Priorität";
        else if(column==3) return "Nachricht";
        else System.err.println("AAH"); return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(dataholder.getResult()==null) return null;

        EdasomindResult result = dataholder.getResult().get(rowIndex);
        if(columnIndex==0) return format(result.getCalendarFrom());
        else if(columnIndex==1) return format(result.getCalenderTo());
        else if(columnIndex==2) return result.getSignificance();
        else if(columnIndex==3) return result.getMessage();
        else System.err.println("AAH"); return null;

    }
    public String format(Calendar calendar){
        SimpleDateFormat fmt = new SimpleDateFormat();
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }

}
