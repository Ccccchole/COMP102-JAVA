// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 3
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

import java.awt.*;

class franceFlag extends Canvas
{   
    public franceFlag()
    {
        setSize(400, 400);
        setBackground(Color.white);
    }

    public static void print(){
        franceFlag GP = new franceFlag();  
        Frame aFrame = new Frame();
        aFrame.setSize(700, 500);
        aFrame.add(GP);
        aFrame.setVisible(true);
    }

    public void paint(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(100, 50, 100, 200);
        g.setColor(Color.white);
        g.fillRect(200, 50, 100, 200);
        g.setColor(Color.red);
        g.fillRect(300, 50, 100, 200);
        g.setColor(Color.black);
        g.drawRect(100,50,300,200);
    }
}