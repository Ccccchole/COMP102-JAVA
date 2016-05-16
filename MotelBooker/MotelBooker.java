// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 9
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** This MotelBooker program will allow the user to recored and modify bookings
 *  for a set of motel units for a week at a time.
 *  The program will first display an empty grid of bookings.
 *  The user can then:
 *  - create a new week of bookings 
 *  - load a week of bookings from a file,
 *  - save bookings to a file,
 *  - use the mouse to select a cell (unit/day) 
 *  - add a booking to the selected cell (if not currently booked)
 *  - delete the booking in the selected cell
 *  - ask the program to find a sequence of days on which some unit is available.
 *
 * The booking chart is represented by a (2D) array of Booking objects, indexed
 *   by the unit number (0 .. NUM_UNITS-1) and the day (0 .. NUM_DAYS-1)
 *
 * A unit that has no booking on a given day is represented by a null value in
 *   the corresponding element of the array.
 */

public class MotelBooker{

    //constants
    public static final int NUM_UNITS = 10;
    public static final int NUM_DAYS = 7;

    // The booking data
    private Booking[][] bookings = new Booking[NUM_UNITS][NUM_DAYS];

    // Fields for the interface
    private int selectedUnit = 0;
    private int selectedDay = 0;
    private String currentName;
    private String currentPhone;

    // constants for the layout of the grid
    public static final int GRID_LEFT = 60;    // left edge of the grid
    public static final int GRID_TOP = 40;     // top edge of the grid
    public static final int CELL_WIDTH = 90;   // width of cells in the grid
    public static final int CELL_HEIGHT = 35;  // height of cells in the grid

    /** Construct a new MotelBooker object
     *  set up the GUI (mouse, buttons)
     *  displays the booking grid
     */
    public MotelBooker(){
        UI.initialise();
        UI.setMouseListener(this::doMouse);

        UI.addButton("New", this::doNew);
        UI.addButton("Load", this::doLoad);
        UI.addButton("Save", this::doSave);
        UI.addTextField("Name", this::setName);
        UI.addTextField("Phone", this::setPhone);
        UI.addButton("Book", this::doBook);
        UI.addButton("Delete", this::doDelete);
        UI.addButton("Search", this::doSearch);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(1000,450);
        this.redisplay();
    }

    /**
     * If the user clicks on a cell, it makes this cell be the selected cell:
     *  Works out the day from the x position
     *  Works out the unit from the y position
     *  Checks that the day and unit are within bounds
     *  Stores the unit and day in the appropriate fields
     *        Redisplays the booking chart
     */

    public void doMouse(String action, double x, double y) {
        if (action.equals("released")){
            int day =  (int)((x-GRID_LEFT) / CELL_WIDTH);
            int unit = (int)((y-GRID_TOP) / CELL_HEIGHT);
            if (day>=0 && day <NUM_DAYS && unit>=0 && unit < NUM_UNITS){ 
                this.selectedUnit = unit;
                this.selectedDay = day;
                this.redisplay();
            }
        }
    }

    public void setName(String value){
        this.currentName = value;
    }

    public void setPhone(String value){
        this.currentPhone = value;
    }

    /**
     * Make a new array of bookings
     */
    public void doNew(){
        this.bookings = new Booking[NUM_UNITS][NUM_DAYS];
        this.redisplay();
    }

    /** Display the Booking chart on the graphics pane
    - Displays the days and units in the margins
    - Displays all the bookings
    - Hilights the current day and unit
    (by displaying it with a different background colour)
    NOTE: should use the displayCell method to draw each cell. */
    private void redisplay(){
        // display all the slots with the activities
        // redisplay (and highlight) the current slot
        UI.clearGraphics();
        this.displayTitles();
        for(int row = 0; row<NUM_UNITS; row++)
        {
            for(int col = 0; col<NUM_DAYS; col++)
            {
                this.displayCell(row, col, Color.white); 
            }
        }
        this.displayCell(this.selectedUnit, this.selectedDay, Color.yellow);
        UI.repaintGraphics();
    }

    /**
     * Displays one cell (rectangular region of the grid) on the
     * graphics pane, along with any Booking in it.
     * The first two arguments specify the unit and the day
     * The third argument is the colour of the background. 
     */
    private void displayCell(int unit, int day, Color c){
        int x = GRID_LEFT + day*CELL_WIDTH;   // left of cell
        int y = GRID_TOP + unit*CELL_HEIGHT;  // top of cell
        UI.setColor(c);
        UI.fillRect(x, y, CELL_WIDTH, CELL_HEIGHT);
        UI.setColor(Color.black);
        UI.drawRect(x, y, CELL_WIDTH, CELL_HEIGHT);   // outline
        if ( this.bookings[unit][day] != null){
            UI.drawString(this.bookings[unit][day].getName(), x+2, y+CELL_HEIGHT/2);
            UI.drawString(this.bookings[unit][day].getPhone(), x+2, y+CELL_HEIGHT-2);
        }
    }

    /** 
     * Delete the booking in the currently selected cell, then redisplay
     */
    public void doDelete() {
        this.bookings[this.selectedUnit][this.selectedDay]=null;
        this.redisplay();
    }

    /*
     * Add a booking in the selected cell, unless
     * - The cell already has a booking or
     * - The user has not entered a name or a phone number in the text fields
     *   in which case, it should display an appropriate message in the text pane
     * Redisplays all the bookings.
     */
    public void doBook() {
        if(this.bookings[this.selectedUnit][this.selectedDay]==null&&this.currentName!=null&&this.currentPhone!=null)
        {
            this.bookings[this.selectedUnit][this.selectedDay]=new Booking(this.currentName, this.currentPhone);
            this.redisplay();
        }
        else if(this.bookings[this.selectedUnit][this.selectedDay]!=null)
        {
            UI.println("Sorry it's booked.");
        }
        else if(this.currentName==null||this.currentPhone==null)        
        { 
            UI.println("Please enter you name and phone.");
        }
    }

    /**
     * Load a set of bookings from a file and redisplay.
     *  Saved files are of the form:
     *        unit day
     *        name
     *        phone
     *        unit day
     *        name
     *        phone
     *        .....
     *  Asks the user to select a file.
     *  For each entry, 
     *   It should read the unit and the day as integers, and then the booking details.
     *  Be careful:  after reading the unit and day, the scanner will be pointing at the end of the
     *   line, not at the beginning of the line with the name!
     */
    public void doLoad() {
        try{
            Scanner sc = new Scanner(new File(UIFileChooser.open()));
            while(sc.hasNext())
            {
                int unit = sc.nextInt();
                int day = sc.nextInt();
                String x = sc.nextLine();
                String name = sc.nextLine();
                String phone = sc.nextLine();
                this.bookings[unit][day] = new Booking(name, phone);              
            }
            sc.close();
        }
        catch(IOException e)
        {
            UI.println("File failure: "+e);
        }
        this.redisplay();
    }

    /**
     * Save the current set of bookings to a file in a form that can be
     *  loaded again later (ie, the same form as used in doLoad above).
     *         Asks the user to choose a file.
     */
    public void doSave() {
        try{
            PrintStream bk = new PrintStream(new File(UIFileChooser.save()));
            for(int row = 0; row<NUM_UNITS; row++)
            {
                for(int col = 0; col<NUM_DAYS; col++)
                {
                    if(this.bookings[row][col]!=null)
                    {
                        bk.println(row+" "+col);
                        String name = this.bookings[row][col].getName();
                        bk.println(name);
                        String phone = this.bookings[row][col].getPhone();
                        bk.println(phone);
                    }
                }
            }
            bk.close();
        }
        catch(IOException e)
        {
            UI.println("File failure: "+e);
        }
    }

    /** [COMPLETION ONLY]
     * Ask for the number of days required, and then
     *         search for a sequence of that many days for some unit
     *         that are all free.
     *         Highlight the cells in red. 
     */
    public void doSearch() {
        int daysWanted = UI.askInt("Number of days required"); 
        for(int row = 0; row<NUM_UNITS; row++)
        {     
            int count = 0;
            for(int col = 0; col<NUM_DAYS; col++)
            {     
                if(this.bookings[row][col]==null)
                {
                    count++;
                }     
                //Make sure no booking in between.
                if(this.bookings[row][col]!=null)
                {
                    count = 0;
                }
                if(count>=daysWanted)
                {
                    for(int i = col; i>=col-daysWanted+1; i--)
                    {
                        this.displayCell(row, i, Color.red); 
                    }
                }   
            }
        }
    }

    // ==========================================================
    //                   HELPER METHOD
    //        These are already completed for you.
    // ==========================================================

    private String[] dayNames = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    /**
     * Display units and days on the margins of the grid 
     */
    private void displayTitles(){
        // display the titles on the margins
        for (int day = 0; day < NUM_DAYS; day++) {
            int x = GRID_LEFT + day*CELL_WIDTH;
            UI.drawString(this.dayNames[day], x+10, GRID_TOP-5);
        }
        for (int unit=0; unit< NUM_UNITS; unit++) {
            int y = GRID_TOP + unit*CELL_HEIGHT;
            UI.drawString("Unit "+(unit), GRID_LEFT-50, y+CELL_HEIGHT*2/3);
        }
    }

    // Main
    public static void main(String[] arguments){
        new MotelBooker();
    }        

}