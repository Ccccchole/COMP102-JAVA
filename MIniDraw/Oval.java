// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 10
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

import java.util.*;
import ecs100.*;
import java.awt.Color;
import java.io.*;

/** Oval represents an oval shape
 *  Implements the Shape interface.
 *  Needs fields to record the position, size, and colour
 */

public class Oval implements Shape{ 
    private double left;
    private double top;
    private double width;
    private double height;
    private Color currentColor;

    /** Constructor with explicit values
     *  Arguments are the x and y of the top left corner,
     *  the width and height, and the color.  
     */
    public Oval (double x, double y, double wd, double ht, Color col){
        this.left = x;
        this.top = y;
        this.width = wd;
        this.height = ht;
        this.currentColor = col;
    }

    /** [Completion] Constructor which reads values from a String
     *  that contains the specification of the Oval. 
     *  The format of the String is determined by the toString method.
     *     The first 3 integers specify the color;
     *     the following four numbers specify the position and the size.
     */
    public Oval(String description) {
        Scanner data = new Scanner(description);
        String Oval = data.next();
        int red = data.nextInt();
        int green = data.nextInt();
        int blue = data.nextInt();
        this.currentColor = new Color(red, green, blue);
        this.left = data.nextDouble();
        this.top = data.nextDouble();
        this.width = data.nextDouble();
        this.height = data.nextDouble();
    }

    /** Returns true if the point (u, v) is on top of the shape */
    public boolean on(double u, double v)
    {
        // an easy approximation is to pretend it is the enclosing rectangle.
        // It is nicer to do a little bit of geometry and get it right
        if(u>=this.left&&u<=this.left+this.width&&v>=this.top&&v<=this.top+this.height)
        {
            return true;
        }
        return false;
    }

    /** Changes the position of the shape by dx and dy.
     *  If it was positioned at (x, y), it will now be at (x+dx, y+dy)
     */
    public void moveBy(double dx, double dy)
    {
        this.left = this.left+dx;
        this.top = this.top+dy;
    }

    /** Draws the oval on the graphics pane. 
     *  It draws a black border and fills it with the color of the oval.
     */
    public void redraw(){
        UI.setColor(Color.black);
        UI.drawOval(this.left, this.top, this.width, this.height);
        UI.setColor(this.currentColor);
        UI.fillOval(this.left, this.top, this.width, this.height);
    }

    /** [Completion] Changes the width and height of the shape by the
     *  specified amounts.
     *  The amounts may be negative, which means that the shape
     *  should get smaller, at least in that direction.
     *  The shape should never become smaller than 1 pixel in width or height
     *  The center of the shape should remain the same.
     */
    public void resize(double changeWd, double changeHt){
        if(changeWd-this.left>=1&&changeHt-this.height>=1)
        {            
            this.width = changeWd-this.left;
            this.height = changeHt-this.top;  
        }
    }

    /** Returns a string description of the oval in a form suitable for
     *  writing to a file in order to reconstruct the oval later.
     *  The first word of the string must be Oval.
     */
    public String toString()
    {
        {
            return ("Oval "+currentColor.getRed()+" "+currentColor.getGreen()+" "+currentColor.getBlue()+" "+this.left+" "+this.top+" "+this.width+" "+this.height);
        }
    }
}