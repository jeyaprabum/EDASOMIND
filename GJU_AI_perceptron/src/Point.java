
/**
 * Write a description of class Point here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.Serializable;

public class Point implements Serializable
{
    private  double x;
    private double y;
    private int value;
    
    public Point(double x, double y, int value)
    {
        this.x = x;
        this.y = y;
        this.value = value;
    } 
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public String toString()
    {
        return "x: "+x+", y: "+y+", above:"+ value;
    }
}
