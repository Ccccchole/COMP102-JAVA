// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;
import java.awt.Color;

/** The snake is created at a random position with a random direction.
 * The constructor does not have any parameters.
 * It can move
 *  - makes it go forward one step in its current direction.
 *  - if outside arena boundaries, makes it go backward one step, and then turn to a new (random)
 *         direction.
 *  The walls of the arena are determined by the constants:
 *    FrogSnakeGame.TopWall, FrogSnakeGame.BotWall, FrogSnakeGame.LeftWall and FrogSnakeGame.RightWall
 * It can report its current position (x and y) with the
 *  getX() and getY() methods.
 *  draw() will make it draw itself,
 *  erase() will make it erase itself
 */

public class Snake
{
    private double left;
    private double top;
    private double dir;

    public Snake()
    {
        this.left = FrogGame.LeftWall+Math.random()*(FrogGame.RightWall-40);
        this.top = FrogGame.TopWall+Math.random()*(FrogGame.BotWall-50);
        this.dir = Math.random();
        this.draw();
    }

    public void move()
    {
        this.erase();
        if(this.dir>=0.75&&this.left+Frog.SPEED<=FrogGame.RightWall)
        {
            this.left = this.left+Frog.SPEED;
        }
        else if(this.dir>=0.75&&this.left+Frog.SPEED>FrogGame.RightWall)
        {
            this.left = this.left-Frog.SPEED;
            this.dir = Math.random();
        }
        else if(this.dir<0.75&&this.dir>=0.5&&this.left-Frog.SPEED>=FrogGame.LeftWall)
        {
            this.left = this.left-Frog.SPEED;
        }
        else if(this.dir<0.75&&this.dir>=0.5&&this.left-Frog.SPEED<FrogGame.RightWall)
        {
            this.left = this.left+Frog.SPEED;
            this.dir = Math.random();
        }
        else if(this.dir<0.5&&this.dir>=0.25&&this.top+Frog.SPEED<=FrogGame.BotWall)
        {
            this.top = this.top+Frog.SPEED;
        }
        else if(this.dir<0.5&&this.dir>=0.25&&this.top+Frog.SPEED>FrogGame.BotWall)
        {
            this.top = this.top-Frog.SPEED;
            this.dir = Math.random();
        }
        else if(this.dir<0.25&&this.top-Frog.SPEED>=FrogGame.TopWall)        
        {
            this.top = this.top-Frog.SPEED;
        }
        else if(this.dir<0.25&&this.top-Frog.SPEED<FrogGame.TopWall)        
        {
            this.top = this.top+Frog.SPEED;
            this.dir = Math.random();
        }
        this.draw();
    }

    public double getX()
    {
        return (this.left+40)/2;
    }

    public double getY()
    {
        return (this.top+50)/2;
    }

    public void draw()
    {
        UI.drawImage("snake.jpg", this.left, this.top, 40, 50);
    }

    public void erase()
    {
        UI.eraseImage("snake.jpg", this.left, this.top);
    }

}