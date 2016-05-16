// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment
 * Name:Jinglu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;
import java.awt.Color;

/** Frog
 *  A new frog starts at the given position, with the given direction, and 
 *     has either a "light" or "dark" shade.
 *  Frogs can turn in 4 directions: left, right, up, and down. 
 *  Frogs move around at a constant speed in an arena with an enclosing wall,
 *     following its direction, until it hits a wall. In which case it stops moving.
 *  Frog can grow in size, and (for the completion) can also shrink by resetting their size
 *      to the orginal value.
 *
 *  The walls of the arena are determined by the constants:
 *    FrogGame.TopWall, FrogGame.BotWall, FrogGame.LeftWall and FrogGame.RightWall
 */

public class Frog 
{
    // Constants
    public static final int INITIAL_SIZE = 30;
    public static final int GROWTH_RATE = 1;
    public static final int SPEED = 2;

    // Fields
    private double left;
    private double top;
    private String dir;
    private String shade;
    private int size = 50;

    //Constructor 
    /** 
     * Make a new frog
     * The parameters specify the initial position of the top left corner,
     *   the direction, and the shade of the Frog ("light" or "dark")
     * We assume that the position is within the boundaries of the arena
     */
    public Frog(double left, double top, String dir, String shade)  
    {
        this.left = left+FrogGame.LeftWall;  
        this.top = top+FrogGame.TopWall;
        this.dir = dir;
        this.shade = shade;
        this.draw();
    }

    /**
     * Turn right
     */
    public void turnRight()
    {
        this.dir = "Right";
    }

    /**
     * Turn left
     */
    public void turnLeft()
    {
        this.dir = "Left";
    }

    /**
     * Turn up
     */
    public void turnUp()
    {
        this.dir = "Up";
    }

    /**
     * Turn down
     */
    public void turnDown()
    {
        this.dir = "Down";
    }

    /**
     * Moves the frog: 
     *   use SPEED unit forward in the correct direction
     *   by changing the position of the frog.
     * Make sure that the frog does not go outside the arena, by making sure 
     *  - the top of the frog is never smaller than FrogGame.TopWall
     *  - the bottom of the frog is never greater than FrogGame.BotWall
     *  - the left of the frog is never smaller than FrogGame.LeftWall
     *  - the right of the frog is never smaller than FrogGame.RightWall
     */
    public void move() 
    {
        this.erase();
        if(this.dir.equalsIgnoreCase("Right")&&this.left+this.SPEED+this.size<=FrogGame.RightWall)
        {
            this.left = this.left+this.SPEED;
        }
        else if(this.dir.equalsIgnoreCase("Left")&&this.left-this.SPEED>=FrogGame.LeftWall)
        {
            this.left = this.left-this.SPEED;
        }
        else if(this.dir.equalsIgnoreCase("Up")&&this.top-this.SPEED>=FrogGame.TopWall)
        {
            this.top = this.top-this.SPEED;
        }
        else if(this.dir.equalsIgnoreCase("Down")&&this.top+this.SPEED+this.size<=FrogGame.BotWall)
        {
            this.top = this.top+this.SPEED;
        }
        this.draw();
    }

    /**
     * Check whether the frog is touching the given point, eg, whether the
     *   given point is included in the area covered by the frog.
     * Return true if the frog is on the top of the position (x, y)
     * Return false otherwise
     */
    public boolean touching(double x, double y) 
    {
        if(x>=this.left&&x<=(this.left+this.size)&&y>=this.top&&y<=(this.top+this.size))
        {
            return true;
        }       
        else
        {
            return false;
        }
    }

    /**
     * The Frog has just eaten a bug
     * Makes the frog grow larger by GROWTH_RATE.
     */
    public void grow()
    {
        this.erase();
        this.size = this.size+this.GROWTH_RATE;
        this.draw();
    }

    /**
     * The Frog has just bumped into a snake
     * Makes the frog size reset to its original size
     * ONLY NEEDED FOR COMPLETION
     */
    public void shrink()
    {
         this.erase();
         this.size = 50;
         this.draw();
    }

    /**
     * Draws the frog at the current position.
     */
    public void draw()
    {
        UI.drawImage(this.shade+"frog.jpg", this.left, this.top, this.size, this.size);
    }

    public void erase()
    {
        UI.eraseImage(this.shade+"frog.jpg", this.left, this.top);
    }

}