// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 3 
 * Name:Jinglu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;

/** Program to create simple animated cartoon strips using the
 *  CartoonFigure class.  
 */

public class CartoonStrip{

    /** animate creates two cartoon figures on the window.
     *  Then animates them according to a fixed script by calling a series
     *  of methods on the figures.
     */
    public void animate(){
        CartoonFigure lulu = new CartoonFigure("girl1", 50, 7);
        CartoonFigure lala = new CartoonFigure("girl2", 350, 7);
        //lulu's doing...
        lulu.boilWater(1);
        lulu.reheatFood(3, 100);
        lulu.watchTV(20, 20);
        lulu.washLoad(true, "20");
        lulu.getPowerBill();
        lulu.payPowerBill();
        lulu.go();
        //lala's doing...
        lala.toast();
        lala.bake(5);
        lala.useComputer(20);
        lala.vacuum(10);
        lala.getPowerBill();
        lala.payPowerBill();
        lala.go();
    }

    /** For the completion, the story must be a gamebook story
     *      where the user is asked to make decisions about what the characters will do.
     *   At the end it must print out the users power bill for the story that they chose.
     */
    public void animateChallenge(){
        UI.clearPanes();
        //Intro..
        UI.print("Are you ready?");
        UI.println();
        UI.print("Pls type anything to continue");
        UI.println();
        UI.askToken("or click 'quit' to leave the game.");
        UI.clearText();
        UI.initialise();
        CartoonFigure dejavu = new CartoonFigure("dejavu", 10, 30);
        UI.print("Meow meow! We are de & ja & vu, the Three Musketeers! ");
        UI.println();
        UI.print("As we live in the most peaceful era ever,");
        UI.println();
        UI.print("we no longer use swords but start our new career...");
        UI.println();
        UI.print("Male Housekeeper! YAY!");
        UI.println();
        /*
         * Apply askToken() to stop execution. Any other methods to pause the game?
         * It stops printing how much power they use while it's waiting for text input. 
         */
        UI.askToken("Pls type anything to continue.");
        UI.clearGraphics();
        UI.clearText();

        //First character. All have custom images:
        CartoonFigure de = new CartoonFigure("cat1", 10, 30);
        UI.addButton("Toast",de::toast);
        UI.addButton("Bake", ()->{de.bake(20);});
        UI.addButton("Get Fees",de::getPowerBill);
        UI.addButton("Pay Money",de::payPowerBill);
        UI.addButton("Go",de::go);
        UI.addButton("Quit", UI::quit);
        UI.print("de, the smoky face cat, is ready to serve you..");
        UI.println();
        UI.print("He's good at toasting and baking.^_^");
        UI.println();
        UI.print("What would you like him to do?");
        UI.println();
        UI.print("Don't forget to get/pay fees and let him go.");
        UI.println();
        UI.askToken("After finishing, pls type anything to continue.");
        UI.clearText();
        UI.initialise();

        //Second character:
        UI.print("Now it's ja's turn.");
        CartoonFigure ja = new CartoonFigure("cat2", 10, 30);
        UI.addButton("Boil Water",()->{ja.boilWater(50);});
        UI.addButton("Code", ()->{ja.useComputer(20);});
        UI.addButton("Get Fees",ja::getPowerBill);
        UI.addButton("Pay Money",ja::payPowerBill);
        UI.addButton("Go",de::go);
        UI.addButton("Quit", UI::quit );
        UI.println();
        UI.print("ja, the grumpy cat{don't judge him}, is ready to serve you..");
        UI.println();
        UI.print("He's good at coding and boiling water.^_^");
        UI.println();
        UI.print("What would you like him to do?");
        UI.println();
        UI.print("Don't forget to get/pay fees and let him go.");
        UI.println();
        UI.askToken("After finishing, pls type anything to continue.");
        UI.initialise();
        UI.clearText();

        //Third character:
        UI.print("Now it's vu's turn.");
        CartoonFigure vu = new CartoonFigure("cat3", 10, 30);
        //Add two new appliances:
        UI.addButton("Lighting Design",()->{vu.useLamp(20);});
        UI.addButton("Play Guitar", ()->{vu.useGuitar(30);});
        UI.addButton("Get Fees",vu::getPowerBill);
        UI.addButton("Pay Money",vu::payPowerBill);
        UI.addButton("Go",vu::go);
        UI.addButton("Quit", UI::quit );
        UI.println();
        UI.print("vu, the ginger rock star, is ready to serve you..");
        UI.println();
        UI.print("He's good at lighting design and playing guitarr.^_^");
        UI.println();
        UI.print("What would you like him to do?");
        UI.println();
        UI.print("Don't forget to get/pay fees and let him go.");
        UI.println();
        UI.askToken("After finishing, click 'quit' & go out & have a nice day!");
    }

    /** ---------- The code below is already written for you ---------- **/
    /** Constructor: set up user interface */
    public CartoonStrip(){
        UI.initialise();
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Animate", this::animate );
        UI.addButton("Animate Challenge", this::animateChallenge );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(0);       
    }

}