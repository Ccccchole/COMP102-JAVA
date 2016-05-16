import java.awt.*;

class uaeFlag extends Canvas
{
    public uaeFlag(){
        setSize(400, 400);
        setBackground(Color.white);
    }

    public static void print(){
        uaeFlag GP = new uaeFlag();  
        Frame aFrame = new Frame();
        aFrame.setSize(700, 500);
        aFrame.add(GP);
        aFrame.setVisible(true);
    }

    public void paint(Graphics g){
        g.setColor(Color.red);
        g.fillRect(100, 50, 100, 210);
        g.setColor(Color.green);
        g.fillRect(200, 50, 320, 70);
        g.setColor(Color.white);
        g.fillRect(200, 120, 320, 70);
        g.setColor(Color.black);
        g.fillRect(200, 190, 320, 70);
        g.setColor(Color.black);
        g.drawRect(100, 50, 420, 210);
    }
}