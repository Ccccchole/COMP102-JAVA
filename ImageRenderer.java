// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 5
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** Renders plain ppm images onto the graphics panel
 *  ppm images are the simplest possible colour image format.
 */

public class ImageRenderer{
    public static final int top = 20;   // top edge of the image
    public static final int left = 20;  // left edge of the image
    public static final int pixelSize = 2;  

    /** Core:
     * Renders a ppm image file.
     * Asks for the name of the file, then calls renderImageHelper.
     */
    public void renderImageCore(){
        try{
            Scanner scan = new Scanner(new File(UIFileChooser.open()));
            renderImageHelper(scan);

        }
        catch(Exception e){UI.printf("File failure: ",e);}
    }

    /** Core:
     * Renders a ppm image file.
     * Renders the image at position (left, top).
     * Each pixel of the image is rendered by a square of size pixelSize
     * Assumes that
     * - the colour depth is 255,
     * - there is just one image in the file (not "animated"), and
     * - there are no comments in the file.
     * The first four tokens are "P3", number of columns, number of rows, 255
     * The remaining tokens are the pixel values (red, green, blue for each pixel)
     */
    public void renderImageHelper(Scanner sc){
        String magicNum = sc.nextLine(); 
        if(magicNum.equals("P3")){
            UI.println("Magic number: " + magicNum);
            int width =sc.nextInt();
            int height = sc.nextInt();
            UI.println("width: " + width + ", height: " + height);
            int colorDepth = sc.nextInt();
            UI.println("Colour Depth: " + colorDepth);
            int y = top;
            while(y<height*2+top){
                int x = left;
                while(x<width*2+left){
                    int red = sc.nextInt();
                    int green = sc.nextInt();
                    int blue = sc.nextInt();
                    Color penColor = new Color(red, green, blue);
                    UI.setColor(penColor);
                    UI.fillRect(x, y, pixelSize, pixelSize);
                    x=x+pixelSize;
                }
                y=y+pixelSize;
            } 
        }
        else{
            UI.println("Wrong number!");
        }
    }

    /** Completion
     * Renders a ppm image file which may be animated (multiple images in the file)
     * Asks for the name of the file, then renders the image at position (left, top).
     * Each pixel of the image is rendered by a square of size pixelSize
     * Renders each image in the file in turn with 200 mSec delay.
     * Repeats the sequence 3 times.
     */

    public void renderAnimatedImage(){
        UI.clearGraphics();
        String name = UI.askString("Which file?");
        int count = 0;
        while(count<=2){
            try{
                Scanner scan = new Scanner(new File(name));
                while(scan.hasNext()){
                    renderImageHelper(scan);
                    UI.sleep(200);
                }
            }
            catch(Exception e){UI.println("File failure: "+e);}
            count++;
        }
    }

    /** ---------- The code below is already written for you ---------- **/
    // Constructor
    public ImageRenderer() {
        UI.initialise();
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Render (core)", this::renderImageCore );
        UI.addButton("Render (compl)", this::renderAnimatedImage );
        UI.addButton("Quit", UI::quit );
        UI.setWindowSize(850, 700);
        UI.setDivider(0.0);
    }

}
