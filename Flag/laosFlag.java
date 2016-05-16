import java.awt.*;

class laosFlag extends Canvas
{
    public laosFlag(){
        setSize(400, 400);
        setBackground(Color.white);
    }

    public static void print(){
        laosFlag GP = new laosFlag();  
        Frame aFrame = new Frame();
        aFrame.setSize(700, 500);
        aFrame.add(GP);
        aFrame.setVisible(true);
    }

    public void paint(Graphics g){
        g.setColor(Color.red);
        g.fillRect(100, 50, 300, 50);
        g.setColor(Color.blue);
        g.fillRect(100, 100, 300, 100);
        g.setColor(Color.red);
        g.fillRect(100, 200, 300, 50);
        g.setColor(Color.white);
        g.fillOval(210, 110, 80, 80);
        g.setColor(Color.black);
        g.drawRect(100,50,300,200);
    }
}