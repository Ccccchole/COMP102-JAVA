// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 2 
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

/** Program for calculating how much you can save on your power bill */
// http://www.powerwise.co.nz/why-choose-led-light-bulbs/led-light-bulb-facts/

public class PowerCalculator
{

    private double priceKWh;        
    private int incandescentLifeSpan; 
    private int ledLifeSpan;        
    private double incandescentPrice;  
    private int oldKW;
    private int newKW;
    private int numBulbs;

    //Ask the user how many watts the old bulbs used, how many watts the new bulbs use, and the number of such bulbs to replace.
    public PowerCalculator(int oldKW, int newKW, int numBulbs)
    {
        priceKWh = 0.2034;        
        incandescentLifeSpan = 1000; 
        ledLifeSpan = 25000;        
        incandescentPrice = 0.99;  
        this.oldKW = oldKW;
        this.newKW = newKW;
        this.numBulbs = numBulbs;
    }

    //Print the number of kilowatts they are now saving whenever the lights are on. 
    public double savingKWh()
    {
        return (oldKW-newKW)*numBulbs/1000.0;
    }

    /*
    Ask the user how many hours each day the light is on, how many days it's used per week; 
    how many weeks it's used per year and the cost of each new LED bulb.
    Print the total kilowatt-hours of energy saved each year. 
    Print the total dollars saved on their power bill each year.
    Print the numbers of years it will take to break even on paying the extra for the LED light bulbs. 
    */
    public void calculateCostSaveCore(double hoursEachDay,double daysPerWeek, double weeksPerYear,double $LEDbulb)
    {
        double savingKWhPerYear = savingKWh()*hoursEachDay*daysPerWeek*weeksPerYear;
        double saving$PerYear = savingKWhPerYear*priceKWh;
        System.out.println("Saving KWh Per Year: "+savingKWhPerYear);
        System.out.println("Saving $ Per Year: "+saving$PerYear);
        System.out.println("Numbers of years to break even: " + ($LEDbulb*numBulbs)/saving$PerYear);
    }
    
    public void calculateCostSaverCompletion(double hoursEachDay,double daysPerWeek, double weeksPerYear,double $LEDbulb,double numYears)
    {
        double savingKWhPerYear = savingKWh()*hoursEachDay*daysPerWeek*weeksPerYear;
        double saving$PerYear = savingKWhPerYear*priceKWh;
        System.out.println("Saving KWh Per Year: "+savingKWhPerYear);
        System.out.println("Saving $ Per Year: "+saving$PerYear);
        System.out.println("Numbers of years to break even: " + ($LEDbulb*numBulbs)/saving$PerYear);
        //The cost of running and replacing incandescent bulbs.
        double hoursInTotal = numBulbs*hoursEachDay*daysPerWeek*weeksPerYear*numYears;
        double $incandescentCost = (numYears*8760/incandescentLifeSpan)*numBulbs*incandescentPrice + priceKWh*(oldKW/1000.0)*hoursInTotal;
        System.out.println("The cost of running and replacing incandescent bulbs: "+$incandescentCost);
        //The cost of changing to and then using LED bulbs.
        double $LEDcost = (numYears*8760/ledLifeSpan)*numBulbs*$LEDbulb + priceKWh*(newKW/1000.0)*hoursInTotal;
        System.out.println("The cost of changing to and then using LED bulbs: "+$LEDcost);
        //The savings from changing to LED bulbs.
        double savings = $incandescentCost - $LEDcost;
        System.out.println("The savings from changing to LED bulbs: "+savings);
    }
}
