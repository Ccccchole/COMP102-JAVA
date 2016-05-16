import java.awt.*;

class hospitalSign extends Canvas
{
    public hospitalSign(){
        setSize(400, 400);
        setBackground(Color.white);
    }

    public static void print(){
        hospitalSign GP = new hospitalSign();  
        Frame aFrame = new Frame();
        aFrame.setSize(700, 500);
        aFrame.add(GP);
        aFrame.setVisible(true);
    }

    public void paint(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(100, 50, 300, 300);
        g.setColor(Color.white);
        g.fillRect(175, 110, 40, 180);
        g.setColor(Color.white);
        g.fillRect(175, 180, 150, 40);
        g.setColor(Color.white);
        g.fillRect(285, 110, 40, 180);
        g.setColor(Color.black);
        g.drawRect(100,50,300,300);
    }
}