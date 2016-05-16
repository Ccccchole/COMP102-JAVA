// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 3
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

/**
 * Reads a date from the user as three integers, and then checks that the date is valid
 */

public class DateValidator
{
    private int day;
    private int month;
    private int year;

    public DateValidator()
    { 
        day = 1;
        month = 1;
        year = 1;
    }

    /**
     * Asks user for a date, then determines whether the date
     *    specified by the three integers is a valid date.
     * For the Core, you may assume that
     * - All months have 31 days, numbered 1 to 31
     * - The months run from 1 to 12
     * - Years start from 1 
     */
    public void validateDateCore(int day, int month, int year)
    {
        if(day>=1&&day<=31&&month>=1&&month<=12&&year>=1)
        {
            System.out.println(day+" / "+month+" / "+year+" / "+"is a valid date.");
        }
        else
        {
            System.out.println(day+" / "+month+" / "+year+" / "+"is NOT a valid date.");   
        }
    }

    /**
     * Asks user for a date, then determines whether the date
     *    specified by the three integers is a valid date.
     * For the Completion, you should also check that
     * - Months have the correct number of days
     * - On leap years February should have 29 days.
     *    A year is a leap year if:
     *       - The year can be evenly divided by 4 but not 100
     *       - The year can be evenly divided by 400 
     */
    public void validateDateCompletion(int day, int month, int year)
    {
        if(month==1||month==3||month==5||month==7||month==8||month==10||month==12&&day>=1&&day<=31&&year>=1)
        {
            System.out.println(day+" / "+month+" / "+year+" / "+"is a valid date.");
        }
        else if(month==4||month==6||month==9||month==11&&day>=1&&day<=30&&year>=1)
        {
            System.out.println(day+" / "+month+" / "+year+" / "+"is a valid date.");
        }
        //Calculating Leap Year.
        else if(day>=1&&day<=29&&month==2&&(year%4==0)&&(year%100!=0)||(year%400==0)&&year>=1)
        {
            System.out.println(day+" / "+month+" / "+year+" / "+"is a valid date.");
        }
        else if(day>=1&&day<=28&&month==2&&year>=1)
        {
            System.out.println(day+" / "+month+" / "+year+" / "+"is a valid date.");
        }
        else
        {
            System.out.println(day+" / "+month+" / "+year+" / "+"is NOT a valid date.");   
        }
    }

    /**
     * For the challenge, your program should be extended to deal with the transition from the Julian to Gregorian calendar. 
     * The program should look at the date, determine whether this should be a Julian or Gregorian date, and test it appropriately. 
     * You will need to find the rules of the Julian calendar yourselves. 
     * 
     */
    public void validateDateChallenge()
    {
    System.out.println("I will do it when I get time.");
    }
}

