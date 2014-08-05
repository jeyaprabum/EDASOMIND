package com.maximilian_boehm.edasomind.gui;

import java.awt.Container;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import com.maximilian_boehm.edasomind.access.EdasomindAccessFactory;
import com.maximilian_boehm.edasomind.access.struct.EdasomindResultList;

public class EDASOMIND_GUI  {

    JTextArea field = null;
    TableModel tm = null;
    DataHolder dh = new DataHolder();

    public DataHolder getDataHolder() {
        return dh;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI() {
        //Create and set up the window.
        final JFrame frame = new JFrame("EDASOMIND");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Select File");
        button.setSize(300, 100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                FileDialog dialog = new FileDialog(frame, "Load Java-File",FileDialog.LOAD);
                dialog.setFile("*.java");
                dialog.setVisible(true);

                File f = new File(dialog.getDirectory() + dialog.getFile());
                try {
                    EdasomindResultList result = EdasomindAccessFactory.getHome().analyzeFile(f);
                    dh.setResult(result);
                    //field.setText(result.toString());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        GridLayout l = new GridLayout(2,1);
        frame.setLayout(l);
        Container c = frame.getContentPane();


        tm = new TableModel(dh);
        JTable table = new JTable(tm );
        TableCellRenderer ren = new EDASOMINDTableCellRenderer(dh);
        table.setDefaultRenderer( Object.class, ren );


        c.add(button);
        c.add( new JScrollPane( table ) );
        //c.add(field);



        String className = "com.apple.eawt.FullScreenUtilities";
        String methodName = "setWindowCanFullScreen";

        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getMethod(methodName, new Class<?>[] {
                    Window.class, boolean.class });
            method.invoke(null, frame, true);
        } catch (Throwable t) {
            System.err.println("Full screen mode is not supported");
            t.printStackTrace();
        }



        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EDASOMIND_GUI().createAndShowGUI();
            }
        });
    }
}