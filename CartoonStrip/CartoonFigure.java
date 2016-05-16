// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 3 
 * Name: Roma and Monique
 */

import ecs100.*;

/**
 * A CartoonFigure object is a cartoon figure, displayed on the screen
 * that can 
 *   boil water,
 *   toast some bread,
 *   reheat food in the microwave,
 *   bake at medium temperature,
 *   watch TV,
 *   use a desktop computer,
 *   wash a load of clothes or others,
 *   vacuum,
 *   get their power bill,
 *   pay their power bill,
 *   go but only if they have paid their power bill
 */

public class CartoonFigure {

    /* Fields representing the state of a CartoonFigure */
    private String figureName = "girl1";
    private double figureX = -100;   // top left corner of figure
    private double figureY = -100;
    private int figureHeight;
    private double energyUsage = 0;   // in KWh

    /* Fields containing dimensions of CartoonFigures */
    private double figureWidth = 100;
    private double textDY = 20;

    /* Fields containing dimensions and positions of appliances */
    private double applianceHeight = 100;
    private double applianceDX = figureWidth+50;

    /* Field containing the price of KWH and GST */
    private double price = 0.2034;
    private double gst = 0.15;

    /** Constructor
     *
     * @param name the name of the character of the CartoonFigure (for example "girl1"). 
     *        Note that this determines the characters appearance. 
     * @param x    the x coordinate (left)
     * @param y    the y coordinate (top)
     */
    public CartoonFigure(String name, double x, double y){
        this.figureName = name;
        this.figureX = x;
        this.figureY = y;
        if (this.figureName.contains("girl")) {
            this.figureHeight = 300;
        }
        //Adjust the size of custom images:
        else if(this.figureName.contains("dejavu")){
            this.figureHeight = 510;
            this.figureWidth = 800;
        }
        else if(this.figureName.contains("cat")){
            this.figureHeight = 400;
            this.figureWidth = 200;
        }
        else {
            this.figureHeight = 310;
        }
        this.drawFigure();
    }

    /**
     * Makes the CartoonFigure boil water
     *
     * @param  quantity the quantity of water to boil in litres
     */
    public void boilWater(double quantity) {
        this.drawAppliance("kettle", (100*208/231));
        // We assume the water is at 15C at first
        // 4.186 joules to raise the temperature of 1 gram of water by 1 C
        // 1 KWH = 1/3,600,000 joules
        double energy = 4.186 * quantity * (100-15) / 3600;
        this.energyUsage += energy;
        String txt = String.format("%.3f", energy);
        UI.printMessage("Boiling " + quantity + " litre of water uses " + txt + " KWH");
    }

    /**
     * Makes the CartoonFigure toast some bread
     *
     */
    public void toast() {
        this.drawAppliance("toaster", (100*249/233));
        double energy =  0.055;  // roughly 3 minutes at 1100 watts
        this.energyUsage += energy;
        UI.printMessage("Toasting uses " + energy + " KWH");
    }

    /**
     * Makes the CartoonFigure reheat food in the microwave
     *
     * @param  time the time in minutes the microwave is on
     * @param  wattage the wattage of the device
     */
    public void reheatFood(int time, int wattage) {
        this.drawAppliance("microwave", (100*266/185));
        double energy = (double)wattage / 1000 * time / 60;
        this.energyUsage += energy;
        String txt = String.format("%.3f", energy);
        UI.printMessage("Reheating food in a " + wattage + " watt microwave for " + time + " minutes uses " + txt + " KWH");
    }

    /**
     * Makes the CartoonFigure bake at medium temperature
     *
     * @param  time the time in minutes the oven is on
     */
    public void bake(int time) {
        this.drawAppliance("oven", (100*284/187));
        double energy = 3.5 * time / 60;
        this.energyUsage += energy;
        String txt = String.format("%.3f", energy);
        UI.printMessage("Baking for " + time + " minutes uses " + txt + " KWH");
    }

    /**
     * Makes the CartoonFigure watch TV
     *
     * @param  time the time in minutes the TV is on
     * @param  size the screen size in inches (above 20)
     */
    public void watchTV(int time, int size) {
        if (size < 20) size = 20;
        this.drawAppliance("tv", (100*729/531));
        double energy = (double)(15+(size-20)/2)/350 * time / 60;    // a rough approximation
        this.energyUsage += energy;
        String txt = String.format("%.3f", energy);
        UI.printMessage("Watching TV for " + time + " minutes on a " + size + " inches screen uses " + txt + " KWH");
    }

    /**
     * Makes the CartoonFigure use the computer (desktop)
     *
     * @param  time the time in minutes the computer and its monitor are on
     */
    public void useComputer(int time) {
        this.drawAppliance("computer", (100*651/648));
        double energy = 3.5 * time / 60;
        this.energyUsage += energy;
        String txt = String.format("%.3f", energy);
        UI.printMessage("Using the computer " + time + " minutes uses " + txt + " KWH");
    }
    
    //Add two new appliances:
    public void useLamp(int time) {
        this.drawAppliance("lamp", (100*651/648));
        double energy = 2.5 * time / 60;
        this.energyUsage += energy;
        String txt = String.format("%.3f", energy);
        UI.printMessage("Using the lamp " + time + " minutes uses " + txt + " KWH");
    }
    
      public void useGuitar(int time) {
        this.drawAppliance("guitar", (100*651/648));
        double energy = 2.5 * time / 60;
        this.energyUsage += energy;
        String txt = String.format("%.3f", energy);
        UI.printMessage("Using the guitar " + time + " minutes uses " + txt + " KWH");
    }

    /**
     * Makes the CartoonFigure wash a load of clothes or others
     *
     * @param  frontType  true if the washing machine is a front loader, false if it's a top loader
     * @param  temperatureSetting the setting for the temperature (can be "cold", "warm", or "hot")
     */
    public void washLoad(boolean frontType, String temperatureSetting) {
        this.drawAppliance("washing-machine", (100*196/230));
        // http://michaelbluejay.com/electricity/laundry.html
        // http://econutssoap.com/laundry-science-101-hot-water-vs-cold-water/
        double energy;
        if (frontType) {
            if (temperatureSetting.equalsIgnoreCase("cold"))  energy = 0.34;
            else if (temperatureSetting.equalsIgnoreCase("warm"))  energy = 1.3;
            else energy = 1.8;
        }
        else {
            if (temperatureSetting.equalsIgnoreCase("cold"))  energy = 0.34;
            else if (temperatureSetting.equalsIgnoreCase("warm"))  energy = 1.9;
            else energy = 2.8;
        }
        this.energyUsage += energy;
        String txt = String.format("%.3f", energy);
        UI.printMessage("Washing this load with a " + temperatureSetting + " temperature setting uses " + txt + " KWH");
    }

    /**
     * Makes the CartoonFigure vacuum
     *
     * @param  time the time in minutes spent vacuuming
     */
    public void vacuum(int time) {
        this.drawAppliance("vacuum-cleaner", (100*200/319));
        double energy = 0.5 * time / 60;
        this.energyUsage += energy;
        String txt = String.format("%.3f", energy);
        UI.printMessage("Vacuuming for " + time + " minutes uses " + txt + " KWH");
    }

    /**
     * Makes the CartoonFigure get their power bill
     *
     * @return The characters power bill.
     */
    public double getPowerBill() {
        double powerBill = (this.energyUsage * this.price * (1 + this.gst));
        String txt = String.format("Power Bill: $%.2f", powerBill);
        UI.eraseRect(this.figureX, this.figureY+this.figureHeight, this.figureWidth+10, this.textDY+1);
        UI.drawString(txt, this.figureX, this.figureY+this.figureHeight+this.textDY);
        UI.sleep(1000);
        return powerBill;
    }

    /**
     * Makes the CartoonFigure pay their power bill
     *
     */
    public void payPowerBill() {
        this.energyUsage = 0;
        UI.eraseRect(this.figureX, this.figureY+this.figureHeight, this.figureWidth+10, this.textDY+1);
        UI.drawString("Power Bill: $0.00", this.figureX, this.figureY+this.figureHeight+this.textDY);
        UI.sleep(1000);
    }

    /**
     * Makes the CartoonFigure leave but only if they have paid their power bill
     *
     */
    public void go() {
        if (this.energyUsage == 0)
            UI.eraseRect(this.figureX, this.figureY, this.figureWidth+1, this.figureHeight+this.textDY+1);
        else {
            this.getPowerBill();
            UI.printMessage("Cannot go due to outstanding power bill");
            UI.println("Cannot go due to outstanding power bill");
        }
    }

    /** Helper method that draws the CartoonFigure
     */
    private void drawFigure(){
        String fname = null;
        fname = "figures/" + this.figureName + ".jpg"; 
        UI.drawImage(fname, this.figureX, this.figureY, this.figureWidth, this.figureHeight);
        UI.sleep(100);
    }

    /** Helper method that draws the appliance
     *  All the public methods that use an appliance call drawAppliance
     */
    private void drawAppliance(String appliance, double width){
        String fname = null;
        fname = "appliances/" + appliance + ".jpg"; 
        UI.drawImage(fname, this.figureX+this.applianceDX, this.figureY, width, this.applianceHeight);
        UI.sleep(1000);
        UI.eraseRect(this.figureX+this.applianceDX, this.figureY, width+1, this.applianceHeight+1);
    }

}

