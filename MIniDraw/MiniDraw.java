// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 10
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;
import java.awt.Color;
import java.io.*;
import java.util.*;
import javax.swing.JColorChooser;

/** The MiniDraw program allows the user to create, save, and reload files
specifying drawings consisting of a list of simple shapes.
The program allows the user to
- add a new shape to the drawing
- remove a shape from the drawing
- move a shape to a different position
- set the colour for the next shape
- save the current drawing to a file
- load a previous drawing from a file.
The shapes include lines, rectangles, ovals, and dots

Classes
The MiniDraw class handles all the user interaction:
buttons, mouse actions, file opening and closing.
It stores the current drawing in an ArrayList of Shape .

The Shape interface specifies the Shape type
The shape classes all implement Shape and represent different kinds of shapes.

Files:
A drawing is stored in a file containing one line for each shape,
Each line must start with the name of the type of shape,
followed by a specification of the shape,
First the colour (three integers for red, blue, and green)
Then the position (x and y)
The other values will depend on the shape.

User Interface:
There are buttons for dealing with the whole drawing (New, Open, Save),
buttons for specifying the next shape to draw, and
button for setting the color,
buttons for moving, removing and resizing shapes.
 */

public class MiniDraw{
    // Fields
    private ArrayList<Shape> shapes = new ArrayList<Shape>();    // the collection of shapes

    // suggested fields to store mouse positions, current action, current shape, current colour, etc

    private double pressedX;                 //where the mouse was pressed
    private double pressedY;  
    private String currentAction = "Line";   // current action ("Move", etc) or shape ("Line" etc)
    private Shape currentShape = null;      //  the current shape (or null if no shape)
    private Color currentColor = Color.red;

    /** Constructor sets up the GUI:
     *  sets the mouse listener and adds all the buttons
     *  For New, Open, Save, and Color, call the appropriate method (see below)
     *   to perform the action immediately.
     *  For other buttons, store the button name in the currentAction field
     */
    public MiniDraw(){
        UI.setMouseMotionListener(this::doMouse);
        UI.addButton("Open",this::openDrawing);
        UI.addButton("New",this::newDrawing);
        UI.addButton("Set Color",this::selectColor);
        UI.addButton("Line",this::line);
        UI.addButton("Rectangle",this::rect);
        UI.addButton("Oval",this::oval);
        UI.addButton("Dot",this::dot);
        UI.addButton("Draw polygon",this::polygon);
        UI.addButton("Polygon done",this::done);
        UI.addButton("Move",this::move);
        UI.addButton("Resize",this::resize);
        UI.addButton("Delete",this::delete);
        UI.addButton("Quit",UI::quit);
    }

    // Respond to mouse events 

    /** When mouse is pressed, remember the position in fields
     *  and also find the shape it is on (if any), and store
     *  the shape in a field (use the findShape(..) method)
     *  When the Mouse is released, depending on the currentAction,
     *  - perform the action (move, delete, or resize).
     *    move and resize are done on the shape where the mouse was pressed,
     *    delete is done on the shape where the mouse was released 
     *  - construct the shape and add to the shapes ArrayList,
     *    (though the polygon is more complicated)
     *  - redraw the drawing.
     *  It is easiest to call other methods (see below) to actually do the work,
     *  otherwise this method gets too big!
     */
    
    public void doMouse(String mouseAction, double x, double y){
        if (mouseAction.equals("pressed"))
        {
        this.pressedX = x;
        this.pressedY = y;
        }
        
        if (mouseAction.equals("released")){
        this.currentShape = this.findShape(this.pressedX, this.pressedY);
        if(this.currentAction == "Move")
        {
        this.moveShape(x, y);
        }
        else if(this.currentAction == "Delete")
        {
        this.deleteShape(x, y);
        }
        else if(this.currentAction == "Resize")
        {
        this.resizeShape(x, y);
        }
        else
        {
        this.addShape(this.pressedX, this.pressedY, x, y);
        }
        this.drawDrawing();
        }
        
        if (this.currentAction == "Polygon"&&mouseAction.equals("clicked")){
            this.addPosition(x, y);
        }
    }
    
    // -----------------------------------------------------
    // Methods that actually do the work  
    // -------------------------------------------------------

    /** Draws all the shapes in the list on the graphics pane
     *  First clears the graphics pane, then draws each shape,
     *  Finally repaints the graphics pane
     */
    public void drawDrawing(){
        UI.clearGraphics();
        for(Shape s:shapes)
        {
            s.redraw();
        }
        UI.repaintGraphics();
    }   

    /** Checks each shape in the list to see if the point (x,y) is on the shape.
     *  It returns the topmost shape for which this is true.
     *     Returns null if there is no such shape.
     */
    public Shape findShape(double x, double y){
        for(int i=0; i<shapes.size(); i++)
        {
            Shape b = this.shapes.get(i);
            if(b.on(x, y)==true)
            {
                return b;
            }
        }
        return null;  
    }

    /** Sets the current color.
     * Asks user for a new color using a JColorChooser (see MiniPaint, Assig 6)
     * As long as the color is not null, it remembers the color 
     */
    private void selectColor()
    {
        this.currentColor = JColorChooser.showDialog(null,"Brush color",Color.white);
    }

    /** Start a new drawing -
     *  initialise the shapes ArrayList and clear the graphics pane. 
     */
    public void newDrawing()
    {
        this.saveDrawing();
        UI.clearGraphics();
        this.shapes.clear();
    }

    public void line(){
        this.currentAction = "Line";
    }

    public void rect(){
        this.currentAction = "Rectangle";
    }

    public void oval(){
        this.currentAction = "Oval";
    } 

    public void dot(){
        this.currentAction = "Dot";
    } 

    public void move(){
        this.currentAction = "Move";
    }

    public void delete(){
        this.currentAction = "Delete";
    }

    public void resize(){
        this.currentAction = "Resize";
    }
    
    /** Create a list of x positions and y positions of all the vertices.*/
    private int vertices = 0;
    private double[] x = new double[0];
    private double[] y = new double[0];    
    public void polygon()
    {
        this.vertices = UI.askInt("How many vertices?");
        this.x = new double[this.vertices];
        this.y = new double[this.vertices]; 
        this.currentAction = "Polygon";
    } 
    
    /** Response to mouse action.*/
    private int count = 0;
    public void addPosition(double addX, double addY)
    {      
      this.x[count] = addX;
      this.y[count] = addY;
      count++;        
    }

    /** Construct a new Shape object of the appropriate kind
     *    (depending on currentAction) using the appropriate constructor
     *    of the Line, Rectangle, Oval, or Dot classes.
     *    Adds the shape to the end of the collection.
     */
    public void addShape(double x1, double y1, double x2, double y2)
    {
        Trace.printf("Drawing shape %s, at (%.2f, %.2f)-(%.2f, %.2f)\n",
            this.currentAction, x1, y1, x2, y2);  //for debugging
        if(currentAction == "Line")
        {
            Line s = new Line(x1, y1, x2, y2, this.currentColor);
            this.shapes.add(s);
        }
        if(currentAction == "Rectangle")
        {
            Rectangle r = new Rectangle(x1, y1, x2-x1, y2-y1, this.currentColor);
            this.shapes.add(r);
        }
        if(currentAction == "Oval")
        {
            Oval o = new Oval(x1, y1, x2-x1, y2-y1, this.currentColor);
            this.shapes.add(o);            
        }
        if(currentAction == "Dot")
        {
            Dot d = new Dot(x1, y1, this.currentColor);
            this.shapes.add(d);
        }
    }
    
      /** Add a polygon [challenge].*/
    public void addPolygon()
    {
        if(currentAction == "Polygon")
        {
            Polygon p = new Polygon(this.x, this.y, this.vertices, this.currentColor);
            this.shapes.add(p);
        }
    }

    /** Finish a polygon.*/
    public void done()
    {
         this.addPolygon();
         this.drawDrawing();
         this.count = 0;
         currentAction = null;
    }

    /** Moves the current shape (if there is one)
     *    to where the mouse was released.
     *    Ie, change its position by (toX-fromX) and (toY-fromY)
     */
    public void moveShape(double changeX, double changeY){
        Trace.printf("Moving shape by (%.2f, %.2f)\n", changeX, changeY);  //for debugging       
        if(this.currentShape!=null)
        {
            this.currentShape.moveBy(changeX, changeY);   
        }
    }

    /** Finds the shape that was under the mouseReleased position (x, y)
     *    and then removes it from the ArrayList of shapes. 
     *  If not pressed on any shape, then do nothing.
     */
    public void deleteShape(double x, double y){
        Trace.printf("Deleting shape under (%.2f, %.2f)\n", x, y);  //for debugging
        if(this.currentShape!=null)
        {         
            this.shapes.remove(this.currentShape);            
        }
    }

    // METHODS FOR THE COMPLETION PART
    /** Resizes the current shape. A simple way of doing it is to
     *    resize the shape by the amount that the mouse was moved
     *    (ie from (fromX, fromY) to (toX, toY)). 
     *    If the mouse is moved to the right, the shape should
     *    be made that much wider on each side; if the mouse is moved to
     *    the left, the shape should be made that much narrower on each side
     *    If the mouse is moved up, the shape should be made
     *    that much higher top and bottom; if the mouse is moved down, the shape 
     *    should be made that much shorter top and bottom.
     *    The effect is that if the user drags from the top right corner of
     *    the shape, the shape should be resized to whereever the dragged to.
     */
    public void resizeShape(double changeX, double changeY){
        Trace.printf("Changing size of shape by (%.2f, %.2f) \n", changeX, changeY);  //for debugging
        if(this.currentShape!=null)
        {    
            this.currentShape.resize(changeX, changeY);            
        }
    }

    /** Ask the user to select a file and save the current drawing to the file. */
    public void saveDrawing(){
        try{
            PrintStream s = new PrintStream(new File(UIFileChooser.save()));
            for(Shape x:shapes)
            {
                s.println(x.toString());
            }
        }
        catch(IOException e){UI.println("File failure: "+e);}
    }

    /**
     * Ask the user for a file to open,
     * then read all the shape descriptions into the current drawing.
     * For each line of the file, it will read the first token to find out which
     * kind of shape and read the rest of the line into a string.
     * It will then call the appropriate constructor, passing the string as an argument.
     */
    public void openDrawing(){
        try{
            Scanner sc = new Scanner(new File(UIFileChooser.open()));
            while(sc.hasNext())
            {
                String detail = sc.nextLine();
                if(detail.contains("Line"))
                {
                    Line l = new Line(detail);
                    this.shapes.add(l);
                }
                if(detail.contains("Rectangle"))
                {
                    Rectangle r = new Rectangle(detail);
                    this.shapes.add(r);
                }
                if(detail.contains("Dot"))
                {
                    Dot d = new Dot(detail);
                    this.shapes.add(d);
                }
                if(detail.contains("Oval"))
                {
                    Oval o = new Oval(detail);
                    this.shapes.add(o);
                }
                if(detail.contains("Polygon"))
                {
                    Polygon p = new Polygon(detail);
                    this.shapes.add(p);
                }
            }
            this.drawDrawing();
        }
        catch(IOException e){UI.println("File failure: "+e);}
    }

    public static void main(String args[]){
        new MiniDraw();
    }
}