// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for Assignment 9
 * Name:Jinglu
 * E-mail:mixlulu@gmail.com
 */

/** Represents a booking.
This is a very simple class.  It needs
- two fields (Strings) for storing the name and phone number
- a constructor (which is passed a name and a phone number
- two methods (getName() and getPhone()) to return the name and phone number
 */
public class Booking{
    private String name;
    private String phone;
    public Booking(String name, String phone)
    {
        this.name = name;
        this.phone = phone;
    }

    public String getName()
    {
        return name;
    }

    public String getPhone()
    {
        return phone;
    }
}
