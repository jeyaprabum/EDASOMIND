package com.maximilian_boehm.edasomind.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.maximilian_boehm.edasomind.access.struct.EdasomindSignificance;

public class EDASOMINDTableCellRenderer extends DefaultTableCellRenderer{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    DataHolder dataholder = new DataHolder();
    public EDASOMINDTableCellRenderer(DataHolder dh) {
        dataholder = dh;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(dataholder.getResult().get(row).getSignificance() == EdasomindSignificance.HIGH){

            c.setForeground(Color.WHITE);
            c.setBackground(Color.RED);
        }
        else if(dataholder.getResult().get(row).getSignificance() == EdasomindSignificance.MIDDLE){
            c.setForeground(Color.black);
            c.setBackground(Color.ORANGE);
        }
        else{
            c.setBackground(Color.GREEN);
            c.setForeground(Color.black);
        }
        return c;
    }

    @Override
    protected void setValue(Object value) {
        super.setValue(value);
    }

}
