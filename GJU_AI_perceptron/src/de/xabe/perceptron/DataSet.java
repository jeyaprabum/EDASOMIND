package de.xabe.perceptron;


/**
 * Write a description of class CreateData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.HashSet;
import java.io.*;

public class DataSet
{
    private double slope=1;
    private double intercept=0;
    
    public DataSet(double slope, double intercept) {
        if (intercept > 1) {
            this.intercept = 1;
        } else if (intercept < -1) {
            this.intercept = -1;
        } else {
            this.intercept = intercept;
        }
        this.slope = slope;
    }
    
    public int computeValue(double x, double y)
    {
        double yF = slope * x + intercept;
        if (slope >= 0) {
            if (y <= yF) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (y >= yF) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    
    public void creteDataSet(String fileName, int size)
    {
        if(size <10) {
            size = 100;
        }
        int i;
        double x,y;
        Random r = new Random();
        HashSet<Point> set = new HashSet<Point>();
        for (i = 1; i<= size; i++) {
            x = r.nextDouble() * 2 - 1.0f;
            y = r.nextDouble() * 2 - 1.0;
            set.add(new Point(x,y,computeValue(x,y)));
        }
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(set);
            out.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Data Set created and saved to file "+fileName+"\n\n");
    }
    
    public static HashSet<Point> readSet(String fileName)
    {
        HashSet<Point> set = null;
        if ((new File(fileName)).exists() == false) {
            System.out.println("There is no file "+fileName+"\n\n");
            return null;
        }
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            Object obj =  in.readObject();
            set = (HashSet<Point>) obj;
            in.close();
            System.out.println("Set read from "+fileName+"\n\n");
        }
        catch (Exception ex) {
            System.out.println("There is a problem in restoring the object of type HashSet<Point> from file "+fileName+"\n\n");
        }
        return set;
    }
}
