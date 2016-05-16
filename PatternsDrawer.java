// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 4, 
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;

/** PatternsDrawer
Draw various patterns. */

public class PatternsDrawer{

    public static final double boardLeft = 50;   // Top left corner of the board
    public static final double boardTop = 50;
    public static final double boardSize = 300;  // The size of the board on the window

    /** Draw a square grid board with white squares.
     *  Asks the user for the number of squares on each side
     *
     * CORE
     */
    public void drawGridBoard(){
        UI.clearGraphics();
        int row = UI.askInt("How many rows:");
        int col = UI.askInt("how many cols:");
        int index1 = 1;
        while(index1<=col){
            int index2 = 1;
            while(index2<=row){
                int XX = 20 + index1*10;
                int YY = 20 + index2*10;
                UI.drawRect(XX, YY, 10, 10);
                index2++;
            }
            index1++;
        }
    }

    /** Illusion
     * a pattern that makes dark circles appear in the intersections
     * when you look at it. 
     **/
    public void drawIllusion(){
        UI.clearGraphics();
        int num = UI.askInt("How many rows:");
        int row = 0;
        while(row<=num){
            int col = 0;
            while(col<=num){
                int XX = row*10;
                int YY = col*10 - row*10;
                UI.fillRect(XX, YY, 8, 8);
                col++;
            }
            row++;
        }
        
    }

    /** Draw a checkered board with alternating black and white squares
     *    Asks the user for the number of squares on each side
     *
     * COMPLETION
     */
    public void drawCheckersBoard()
    {
        UI.clearGraphics();
        int rows = UI.askInt("How many rows:");
        int row = 1;
        while(row<=rows){
            int col = 1;
            while(col<=rows){
                int XX = row*10;
                int YY = col*10;
                if((col%2==0&&row%2==0)||(col%2==1&&row%2==1)){
                    UI.fillRect(XX, YY, 10, 10);
                    col++;}
                else{
                    UI.drawRect(XX, YY, 10, 10);
                    col++;}
            }
            row++;
        }
    }

    /** Draw a board made of concentric circles, 2 pixel apart
     *  Asks the user for the number of squares on each side
     */
    public void drawConcentricBoard()
    {
        UI.clearGraphics();
        int circle = UI.askInt("How many rows:");
        int cell = 1;
        while(cell<=circle){
            int number = 1;
            while(number<=circle){

                int XX = cell*30;
                int YY = number*30;
                int xx = 0;
                while(xx<= 30){
                    UI.drawOval(XX+xx/2, YY+xx/2, 30-xx, 30-xx);
                    xx=xx+circle;

                }
                number++;
            }
            cell++;
        }

    }

    /** ---------- The code below is already written for you ---------- **/

    public PatternsDrawer(){
        UI.initialise();
        UI.addButton("Clear",UI::clearPanes);
        UI.addButton("Core: Grid", this::drawGridBoard);
        UI.addButton("Core: Illusion", this::drawIllusion);
        UI.addButton("Completion: Checkers", this::drawCheckersBoard);
        UI.addButton("Challenge: Concentric", this::drawConcentricBoard);
        UI.addButton("Quit",UI::quit);
    }   
}