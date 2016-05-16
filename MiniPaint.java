// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 6
 * Name:Jingu Xu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;

public class MiniPaint{
    // fields to remember:
    //  - the shape that will be drawn when the mouse is next released.
    //  - whether the shape should be filled or not
    //  - the position the mouse was pressed, 
    //  - the name of the image file
    private String shape;
    private boolean filled;
    private double left;
    private double top;
    private String name;
    private boolean remove;

    //Constructor
    /** Sets up the user interface - mouselistener and buttons */

    public MiniPaint(){
        UI.setMouseMotionListener(this::doMouse);
        UI.addButton("Line", this::line);
        UI.addButton("Rectangle", this::rect);
        UI.addButton("Oval", this::oval);
        UI.addButton("Image", this::image);
        UI.addButton("Color", this::color);
        UI.addButton("Fill/NoFill", this::fill);
        UI.addButton("Eraser/Brush", this::eraser);
        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Quit", UI::quit);
        filled = true;
        remove = false;
    }

    /**
     * Respond to mouse events
     * When pressed, remember the position.
     * When released, draw the current shape using the pressed position
     *  and the released position.
     * Uses the value in the field to determine which kind of shape to draw.
     * Although you could do all the drawing in this method,
     *  it may be better to call some helper methods for the more
     *  complex actions (and then define the helper methods!)
     */
    public void doMouse(String action, double x, double y) {
        //Draw line:
        if(action.equals("pressed")&&shape.equals("Line"))
        {
            left = x;
            top = y;
        }

        else if(action.equals("released")&&shape.equals("Line"))
        {   
            UI.setLineWidth(5);
            UI.drawLine(left, top, x, y);
        }

        //Draw filled rect from any corner:
        else if(action.equals("pressed")&&shape.equals("Rectangle")&&filled == true)
        {
            left = x;
            top = y;
        }
        else if(action.equals("released")&&shape.equals("Rectangle")&&filled == true&&x>left&&y>top)
        {            
            UI.setLineWidth(5);
            UI.fillRect(left, top,x-left, y-top);
        }         
        else if(action.equals("released")&&shape.equals("Rectangle")&&filled == true&&x<left&&y>top)
        {            
            UI.setLineWidth(5);
            UI.fillRect(x, top, left-x, y-top);
        }         
        else if(action.equals("released")&&shape.equals("Rectangle")&&filled == true&&x<left&&y<top)
        {          
            UI.setLineWidth(5);
            UI.fillRect(x, y, left-x, top-y);
        }         
        else if(action.equals("released")&&shape.equals("Rectangle")&&filled == true&&x>left&&y<top)
        {            
            UI.setLineWidth(5);
            UI.fillRect(left, y, x-left, top-y);
        }  

        //Draw unfilled rect from any corner:
        else if(action.equals("pressed")&&shape.equals("Rectangle")&&filled == false)
        {
            left = x;
            top = y;
        }
        else if(action.equals("released")&&shape.equals("Rectangle")&&filled == false&&x>left&&y>top)
        {            
            UI.setLineWidth(5);
            UI.drawRect(left, top,x-left, y-top);
        }       
        else if(action.equals("released")&&shape.equals("Rectangle")&&filled == false&&x<left&&y>top)
        {            
            UI.setLineWidth(5);
            UI.drawRect(x, top, left-x, y-top);
        }         
        else if(action.equals("released")&&shape.equals("Rectangle")&&filled == false&&x<left&&y<top)
        {            
            UI.setLineWidth(5);
            UI.drawRect(x, y, left-x, top-y);
        }         
        else if(action.equals("released")&&shape.equals("Rectangle")&&filled == false&&x>left&&y<top)
        {            
            UI.setLineWidth(5);
            UI.drawRect(left, y, x-left, top-y);
        }  

        //Draw filled oval from any corner:
        else if(action.equals("pressed")&&shape.equals("Oval")&&filled == true)
        {
            left = x;
            top = y;
        }
        else if(action.equals("released")&&shape.equals("Oval")&&filled == true&&x>left&&y>top)
        {            
            UI.setLineWidth(5);
            UI.fillOval(left, top,x-left, y-top);
        }
        else if(action.equals("released")&&shape.equals("Oval")&&filled == true&&x<left&&y>top)
        {            
            UI.setLineWidth(5);
            UI.fillOval(x, top, left-x, y-top);
        }         
        else if(action.equals("released")&&shape.equals("Oval")&&filled == true&&x<left&&y<top)
        {            
            UI.setLineWidth(5);
            UI.fillOval(x, y, left-x, top-y);
        }         
        else if(action.equals("released")&&shape.equals("Oval")&&filled == true&&x>left&&y<top)
        {            
            UI.setLineWidth(5);
            UI.fillOval(left, y, x-left, top-y);
        }  

        //Draw unfilled oval:
        else if(action.equals("pressed")&&shape.equals("Oval")&&filled == false)
        {
            left = x;
            top = y;
        }
        else if(action.equals("released")&&shape.equals("Oval")&&filled == false&x>left&&y>top)
        {            
            UI.setLineWidth(5);
            UI.drawOval(left, top,x-left, y-top);
        }
        else if(action.equals("released")&&shape.equals("Oval")&&filled == false&&x<left&&y>top)
        {         
            UI.setLineWidth(5);
            UI.drawOval(x, top, left-x, y-top);
        }         
        else if(action.equals("released")&&shape.equals("Oval")&&filled == false&&x<left&&y<top)
        {          
            UI.setLineWidth(5);
            UI.drawOval(x, y, left-x, top-y);
        }         
        else if(action.equals("released")&&shape.equals("Oval")&&filled == false&&x>left&&y<top)
        {          
            UI.setLineWidth(5);
            UI.drawOval(left, y, x-left, top-y);
        }  

        //Draw image:
        else if(action.equals("pressed")&&shape.equals("Image"))
        {
            left = x;
            top = y;
        }

        else if(action.equals("released")&&shape.equals("Image"))
        {            
            UI.drawImage(UIFileChooser.open(),left, top,x-this.left, y-this.top);
        }

        //Use eraser:
        else if(action.equals("clicked")&&remove==true)
        {
            UI.setLineWidth(30);
            UI.eraseLine(left, top, x, y);  
        }
    }

    public void line()
    {
        shape = "Line";
    }

    public void rect()
    {
        shape = "Rectangle";
    }

    public void oval()
    {
        shape = "Oval";
    }

    public void image()
    {
        shape = "Image";
    }

    //Set color:
    public void color()
    {
        UI.setColor(JColorChooser.showDialog(null,"Brush color",Color.white));
    }

    //Set filled/unfilled:
    public void fill()
    {
        boolean unFill = false;
        boolean fill = true;
        if(filled == true)
        {
            filled = unFill;
            UI.clearText();
            UI.println("No fill");
        }
        else
        {
            filled = fill;
            UI.clearText();
            UI.println("Fill");
        }
    }

    //Set eraser/brush:
    public void eraser()
    {
        boolean brush = true;
        boolean eraser = false;
        if(remove == false)
        {
            remove = brush;
            UI.clearText();
            UI.println("Eraser");
        }
        else
        {
            remove = eraser;
            UI.clearText();
            UI.println("Brush");
        }
    }

    // Main:  constructs a new MiniPaint object
    public static void main(String[] arguments){
        new MiniPaint();
    }        
}