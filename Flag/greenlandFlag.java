import java.awt.*;

class greenlandFlag extends Canvas
{
    public greenlandFlag(){
        setSize(400, 400);
        setBackground(Color.white);
    }

    public static void print(){
        greenlandFlag GP = new greenlandFlag();  
        Frame aFrame = new Frame();
        aFrame.setSize(700, 500);
        aFrame.add(GP);
        aFrame.setVisible(true);
    }

    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(100, 50, 300, 100);
        g.setColor(Color.red);
        g.fillRect(100, 150, 300, 100);
        g.setColor(Color.red);
        g.fillArc(150, 90, 120, 120, 0, 180);
        g.setColor(Color.white);
        g.fillArc(150, 90, 120, 120, 0, -180);
        g.setColor(Color.black);
        g.drawRect(100,50,300,200);
    }
}
