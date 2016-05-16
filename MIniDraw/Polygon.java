// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 10
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

import java.util.*;
import ecs100.*;
import java.awt.Color;
import java.io.*;

/** Polygon represents a polygon made of a sequence of straight lines.
 *  Implements the Shape interface.
 *  Has a field to record the colour of the line and two fields to store
 *  lists of the x coordinates and the y coordinates of all the vertices
 */

public class Polygon implements Shape{
    private int vertices;
    private double[] left = new double[vertices];
    private double[] top = new double[vertices];
    private Color currentColor;

    public Polygon(double[] x, double[] y, int v, Color col)
    {
        this.left = x;
        this.top = y;
        this.vertices = v;
        this.currentColor = col;
    }

    /** [Completion] Constructor which reads values from a String
     *  that contains the specification of the polygon. 
     *  The format of the String is determined by the toString method.
     *     The first 3 integers specify the color;
     *     the following numbers specify the position and the size.
     */
    public Polygon(String description) {
        Scanner data = new Scanner(description);
        String Polygon = data.next();
        this.vertices = data.nextInt();
        int red = data.nextInt();
        int green = data.nextInt();
        int blue = data.nextInt();
        this.currentColor = new Color(red, green, blue);
        double[] x = new double[this.vertices];
        double[] y = new double[this.vertices];
        for(int i = 0; i<this.vertices; i++)
        {
            x[i] = data.nextDouble();
            y[i] = data.nextDouble();
        }        
        this.left = x;
        this.top = y;
    }

    /** Returns true if the point (u, v) is on top of the shape */
    private double maxX = Double.MIN_VALUE;
    private double minX = Double.MAX_VALUE;
    private double maxY = Double.MIN_VALUE;
    private double minY = Double.MAX_VALUE;
    public boolean on(double u, double v) {
        for(int i = 0; i<this.vertices; i++)
        {
            if(this.left[i]>maxX)
            {
                this.maxX = this.left[i];
            }
            if(this.left[i]<minX)
            {
                this.minX = this.left[i];
            }
            if(this.top[i]>maxY)
            {
                this.maxY = this.top[i];
            }
            if(this.top[i]<minY)
            {
                this.minY = this.top[i];
            }
        }
        if(u>=this.minX&&u<=this.maxX&&v>=this.minY&&v<=this.maxY)
        {
            return true;
        }
        return false;
    }

    /** Changes the position of the shape by dx and dy.
     *  If it was positioned at (x, y), it will now be at (x+dx, y+dy)
     */
    public void moveBy(double dx, double dy) {
        for(int i = 0; i<this.vertices; i++)
        {
            this.left[i] = this.left[i]+dx;
            this.top[i] = this.top[i]+dy;
        }
    }

    /** Draws the polygon on the graphics pane. 
     *  It draws a black border and fills it with the color of the polygon.
     */
    public void redraw() {
        UI.setColor(Color.black);
        UI.drawPolygon(this.left, this.top, this.vertices);
        UI.setColor(this.currentColor);
        UI.fillPolygon(this.left, this.top, this.vertices);
    }

    /** [Completion] Changes the width and height of the shape by the
     *  specified amounts.
     *  The amounts may be negative, which means that the shape
     *  should get smaller, at least in that direction.
     *  The shape should never become smaller than 1 pixel in width or height
     *  The center of the shape should remain the same.
     */
    public void resize (double changeWd, double changeHt) {
        if(changeWd-this.minX>=1&&changeHt-this.minY>=1)
        {
            for(int i = 0; i<this.vertices; i++)
            {
                if(this.left[i]==this.maxX)
                {
                    this.left[i] = changeWd;
                }
                if(this.top[i]==this.maxY)
                {
                    this.top[i] = changeHt;
                }
            }
        }
    }

    /** Returns a string description of the polygon in a form suitable for
     *  writing to a file in order to reconstruct the polygon later.
     *  The first word of the string must be Polygon. 
     */
    public String toString() 
    {
        ArrayList<String> positionList = new ArrayList<String>();
        for(int i = 0; i<this.vertices; i++)
        {
            positionList.add(this.left[i]+" "+this.top[i]);
        }
        String list = positionList.toString();
        String list1 = list.replace(",", "");
        String list2 = list1.replace("[", "");
        String list3 = list2.replace("]", "");
        return ("Polygon "+this.vertices+" "+currentColor.getRed()+" "+currentColor.getGreen()+" "+currentColor.getBlue()+" "+list3);
    }
}