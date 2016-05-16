// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 8 
 * Name:Jinglu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** Three methods that use arrays
 *
 * duckGame():
 *     construct array of objects,
 *     call methods on them,
 *     remove them
 *  Uses an array with nulls.  Relevant to DominoGame
 *
 * sentenceGame():
 *     construct array of objects,
 *     move them around
 *  Uses an array.  Relevant to DominoGame
 *
 * countScores():
 *     read integers from file and construct an array of counts
 *  Uses an array of counts.
 */

public class ArrayExercise{

    /**
     * Constructor to set up an interface with buttons to call all the methods
     */
    public ArrayExercise(){
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Duck Game", this::doDuckGame);
        UI.addButton("Scores", this::doCountScores);
        UI.addButton("Quit", UI::quit);
    }

    /**
     * Simple duck shooting game
     *  Uses the Duck class, which has a constructor and two methods:
     *   - To construct a duck, specify its horizontal position: eg new Duck(100);
     *   - jiggle() makes the duck jump up and down a few times
     *   - shoot() turns it upside down. You can't jiggle a duck after you have shot it.
     * 
     * The duckGame method should
     * - Construct an array to hold 5 Ducks,
     * - Fill the array with Ducks placed across the screen (at 200, 300, 400, 500, 600)
     *       The Duck constructor has one parameter - the position of the duck.
     *       the position of the i'th duck should be 200+(i*100)
     * - Have a loop that repeats 5 times:
     *   - Ask the user for the number of a duck to shoot ( 0 to 4)
     *   - IF the answer is valid and that element of the array contains a duck, THEN
     *      shoot the duck (call the shoot method on the duck), 
     *      remove it from the array (put null in the array element)
     *     ELSE
     *      tell the user they missed
     *   - jiggle all the remaining ducks (each array element that isn't null)
     */
    public void doDuckGame(){
        Duck[] ducks = new Duck[5];
        // make five Ducks and put them in the array.
        for(int i = 0; i<ducks.length; i++)
        {
            ducks[i] = new Duck(200+(i*100));
        }

        // repeat five times:
        for (int loop=0; loop<5; loop++){
            int index = UI.askInt("Which duck should I shoot?");
            // shoot the duck, (if it is still in the array)
            // and remove it from the array.
            // make each remaining duck jiggle
            if(ducks[index]!=null)
            {
                ducks[index].shoot();
                ducks[index] = null;
            }
            for(int i = 0; i<ducks.length; i++){
                if(ducks[i]!=null)
                {
                    ducks[i].jiggle();
                }
            }
        }
        // 
        UI.println("That's all your shots");
    }

    /**
     * Reads and counts a sequence of scores from a file.
     * The scores are all numbers between 1 and 10, inclusive.
     * The method keeps a count for each score in an array:
     *  count[n] has the count of the number of n's.
     *  When it reads a score, it increments that count for that score
     * At the end, it prints the counts for each score.
     */
    public void doCountScores(){
        UI.println("Counting number of occurrences of each score from 0 to 10");
        int[] counts = new int[11]; // the array of counts
        try{
            Scanner scan = new Scanner(new File("scores.txt"));
            while(scan.hasNext())
            {
                int num = scan.nextInt();
                for(int i = 1; i<counts.length; i++)
                {
                    if(i==num)
                    {
                        counts[i]++;   
                    }
                }
            }
            for(int j = 1; j<counts.length; j++)
            {
                UI.println(counts[j]);
            }
            scan.close();
        } catch(IOException e){UI.println("Fail: " + e);}
    }

    public static void main(String[] args){
        new ArrayExercise();
    }        

}