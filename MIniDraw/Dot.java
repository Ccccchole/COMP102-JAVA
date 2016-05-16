// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 10
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;
import java.util.*;
import java.awt.Color;
import java.io.*;

/** Dot represents a small circle shape of a fixed size (5 pixels)
 *   Implements the Shape interface.
 *   Needs fields to record the position, and colour
 */

public class Dot implements Shape
{
    private double left;
    private double top;
    private double size = 5;
    private Color currentColor = Color.yellow;

    /** Constructor with explicit values
     *  Arguments are the x and y of the top left corner,
     *  the width and height, and the color. 
     */
    public Dot(double x, double y, Color col)
    {
        this.left = x;
        this.top = y;
        this.currentColor = col;
    }

    public Dot(String description) 
    {
        Scanner data = new Scanner(description);
        String Dot = data.next();
        int red = data.nextInt();
        int green = data.nextInt();
        int blue = data.nextInt();
        this.currentColor = new Color(red, green, blue);
        this.left = data.nextDouble();
        this.top = data.nextDouble();
    }

    /** Returns true if the point (u, v) is on top of the shape */
    public boolean on(double u, double v) 
    {
        if(u>=this.left&&u<=this.left+this.size&&v>=this.top&&v<=this.top+this.size)
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

    /** Draws the dot on the graphics pane. */
    public void redraw() {
        UI.setColor(this.currentColor);
        UI.fillOval(this.left, this.top, this.size, this.size);
    }

    /** [Completion] Changes the width and height of the shape by the
     *  specified amounts.
     *  The amounts may be negative, which means that the shape
     *  should get smaller, at least in that direction.
     *  The shape should never become smaller than 1 pixel in width or height
     *  The center of the shape should remain the same.
     */
    public void resize (double changeWd, double changeHt) {
        if(changeWd-this.left>=1)
        {            
            this.size = changeWd-this.left;
        }
    }

    /** Returns a string description of the dot in a form suitable for
     *  writing to a file in order to reconstruct the dot later.
     *  The first word of the string must be Dot.
     */
    public String toString() 
    {
        {
            return ("Dot "+currentColor.getRed()+" "+currentColor.getGreen()+" "+currentColor.getBlue()+" "+this.left+" "+this.top);
        }
    }
}