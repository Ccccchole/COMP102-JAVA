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

/** Rectangle represents a solid rectangle shape
 *   Implements the Shape interface.
 *   Needs fields to record the position, size, and colour
 */

public class Rectangle implements Shape {
    private double left;
    private double top;
    private double width;
    private double height;
    private Color currentColor = Color.blue;

    /** Constructor with explicit values
     *  Arguments are the x and y of the top left corner,
     *  the width and height, and the color. 
     */
    public Rectangle(double x, double y, double wd, double ht, Color col) {
        this.left = x;
        this.top = y;
        this.width = wd;
        this.height = ht;
        this.currentColor = col;
    }

    /** [Completion] Constructor which reads values from a String
     *  that contains the specification of the Rectangle. 
     *  The format of the String is determined by the toString method.
     *     The first 3 integers specify the color;
     *     the following four numbers specify the position and the size.
     */
    public Rectangle(String description) {
        Scanner data = new Scanner(description);
        String Rectangle = data.next();
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
    public boolean on(double u, double v) {
        if(u>=this.left&&u<=this.left+this.width&&v>=this.top&&v<=this.top+this.height)
        {
            return true;
        }
        return false;
    }

    /** Changes the position of the shape by dx and dy.
     *  If it was positioned at (x, y), it will now be at (x+dx, y+dy)
     */
    public void moveBy(double dx, double dy) {
        this.left = this.left+dx;
        this.top = this.top+dy;
    }

    /** Draws the rectangle on the graphics pane. 
     *  It draws a black border and fills it with the color of the rectangle.
     */
    public void redraw() {
        UI.setColor(Color.black);
        UI.drawRect(this.left, this.top, this.width, this.height);
        UI.setColor(this.currentColor);
        UI.fillRect(this.left, this.top, this.width, this.height);
    }

    /** [Completion] Changes the width and height of the shape by the
     *  specified amounts.
     *  The amounts may be negative, which means that the shape
     *  should get smaller, at least in that direction.
     *  The shape should never become smaller than 1 pixel in width or height
     *  The center of the shape should remain the same.
     */
    public void resize (double changeWd, double changeHt) {
        if(changeWd-this.left>=1&&changeHt-this.height>=1)
        {            
                this.width = changeWd-this.left;
                this.height = changeHt-this.top;  
        }
    }

    /** Returns a string description of the rectangle in a form suitable for
     *  writing to a file in order to reconstruct the rectangle later.
     *  The first word of the string must be Rectangle.
     */
    public String toString() 
    {
        return ("Rectangle "+currentColor.getRed()+" "+currentColor.getGreen()+" "+currentColor.getBlue()+" "+this.left+" "+this.top+" "+this.width+" "+this.height);
    }
}