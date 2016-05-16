// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 3
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;

/** TricolourFlagDrawer: draws a series of tricolour flags */
public class TricolourFlagDrawer{

    public static final double width = 200;
    public static final double height = 133;

    /**
     * asks user for a position and three colours, then calls the 
     * drawThreeColourFlagCore method, passing the appropriate arguments
     *
     * CORE
     */
    public void testCore(){
        double left = UI.askDouble("left of flag");
        double top = UI.askDouble("top of flag");
        UI.println("Now choose the colours");
        Color stripe1 = JColorChooser.showDialog(null, "First Stripe", Color.white);
        Color stripe2 = JColorChooser.showDialog(null, "Second Stripe", Color.white);
        Color stripe3 = JColorChooser.showDialog(null, "Third Stripe", Color.white);
        this.drawThreeColourFlagCore(left, top, stripe1, stripe2, stripe3 );
    }

    /**
     * draws a three colour flag consisting of three vertical equal-width stripes
     * at the given position
     *
     * CORE
     */
    public void drawThreeColourFlagCore(double x, double y, Color S1, Color S2, Color S3){
        //Draw Rect1:
        UI.setColor(S1);
        UI.fillRect(x, y, 400, 80);
        //Draw Rect2:
        UI.setColor(S2);
        UI.fillRect(x, y + 80, 400, 80);
        //Draw Rect3:
        UI.setColor(S3);
        UI.fillRect(x, y + 160, 400, 80);
    }

    /**
     * draws multiple flag made up of three equal size stripes by calling the
     * drawThreeColourFlagCompletion method, passing the appropriate arguments
     *
     * COMPLETION
     */

    public void testCompletion(){
        boolean Horizontal = UI.askBoolean("Horizontal?");
        double left = UI.askDouble("left of flag");
        double top = UI.askDouble("top of flag");
        UI.println("Now choose the colours");
        Color stripe1 = JColorChooser.showDialog(null, "First Stripe", Color.white);
        Color stripe2 = JColorChooser.showDialog(null, "Second Stripe", Color.white);
        Color stripe3 = JColorChooser.showDialog(null, "Third Stripe", Color.white);
        this.drawThreeColourFlagCompletion(Horizontal, left, top, stripe1, stripe2, stripe3 );
    }

    /**
     * draws a three colour flag consisting of three equal-size stripes
     * at the given position
     * The stripes can be either vertical or horizontal
     *
     * COMPLETION
     */
    public void drawThreeColourFlagCompletion(boolean HV, double x, double y, Color S1, Color S2, Color S3)
    {
        //Horizonta or Vertical:
        if(HV==true){
            //Draw Rect1:
            UI.setColor(S1);
            UI.fillRect(x, y, 400, 80);
            //Draw Rect2:
            UI.setColor(S2);
            UI.fillRect(x, y + 80, 400, 80);
            //Draw Rect3:
            UI.setColor(S3);
            UI.fillRect(x, y + 160, 400, 80);
        }
        else{
            //Draw Rect1:
            UI.setColor(S1);
            UI.fillRect(x, y, 100, 200);
            //Draw Rect2:
            UI.setColor(S2);
            UI.fillRect(x + 100, y, 100, 200);
            //Draw Rect3:
            UI.setColor(S3);
            UI.fillRect(x + 200, y, 100, 200);
        };
    }

    
    /**
     * Print a 3x3 grid of nine different countries flag 
     * with their names centred below them. 
     *
     * CHALLENGE
     */
    public void testChallenge(){
        double left=100;
        double top=20;
        this.drawThreeColourFlagChallenge(true, 10, 10, Color.black, Color.yellow, Color.red, "Belgium");              
        this.drawThreeColourFlagChallenge(true, 120, 10, Color.red, Color.yellow, Color.green.darker(), "Guineau");    
        this.drawThreeColourFlagChallenge(true, 230, 10, Color.blue, Color.white, Color.red, "  France");               
        this.drawThreeColourFlagChallenge(false, 10, 120, Color.red, Color.white, Color.blue, "Netherlands");               
        this.drawThreeColourFlagChallenge(false, 120, 120, Color.white, Color.blue, Color.red, "    Russia");              
        this.drawThreeColourFlagChallenge(false, 220, 120, Color.black, Color.red, Color.yellow, "  Germany");           
        this.drawThreeColourFlagChallenge(true, 10, 230, Color.black, Color.white, Color.blue, "Moresnet");               
        this.drawThreeColourFlagChallenge(true, 120, 230, Color.red, Color.white, Color.red, "Savona");   
        this.drawThreeColourFlagChallenge(true, 230, 230, Color.red, Color.white, Color.black, "  Yemen");   
    }

    public void drawThreeColourFlagChallenge(boolean HV, double x, double y, Color S1, Color S2, Color S3, String msg)
    {
        //Horizonta or Vertical:
        if(HV==true){
            //Draw Rect1:
            UI.setColor(S1);
            UI.fillRect(x, y, 100, 20);
            //Draw Rect2:
            UI.setColor(S2);
            UI.fillRect(x, y + 20, 100, 20);
            //Draw Rect3:
            UI.setColor(S3);
            UI.fillRect(x, y + 40, 100, 20);
            UI.drawString(msg, x + 25, y + 80);
        }
        else{
            //Draw Rect1:
            UI.setColor(S1);
            UI.fillRect(x, y, 30, 60);
            //Draw Rect2:
            UI.setColor(S2);
            UI.fillRect(x + 30, y, 30, 60);
            //Draw Rect3:
            UI.setColor(S3);
            UI.fillRect(x + 60, y, 30, 60);
            UI.drawString(msg, x + 10, y + 80);
        };

    }

    /** ---------- The code below is already written for you ---------- **/
    /** Constructor: set up user interface */
    public TricolourFlagDrawer(){
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes );
        UI.addButton("Core", this::testCore );
        UI.addButton("Completion", this::testCompletion );
        UI.addButton("Challenge", this::testChallenge );
        UI.addButton("Quit", UI::quit );
    }

}
