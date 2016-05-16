// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 8 
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;
import java.util.*;
import java.io.*;

/** Reads a genealogy database from a file, and allows the user
to display information about people in the database.
Each line of the file (except the first) contains information
about one person:
- their name
- the year of their birth
- the name of their mother (or ? if the mother is unknown)
- the name of their father (or ? if the father is unknown)

The program will read the data into an list of Person objects.

The program then allows the user to print out
- the names of all the people in the database
(note, names are just the first name - no spaces)
- date of birth of a given person
- parents of a person (if known) and their dates of birth
- the number of (known) children of a person and all their names
and dates of birth.

 */

public class Genealogy{
    // Fields
    private ArrayList<Person> db = new ArrayList<Person>();  // list of Person objects recording the parents of a person
    private Person currentPerson;  // The person currently specified by the user.

    /** Construct a new Genealogy object
     * and set up the GUI
     */
    public Genealogy(){
        UI.addButton("Load DB", this::doLoad);
        UI.addButton("All Names", this::printAllNames);
        UI.addTextField("Name", this::doName);
        UI.addButton("Birth", this::printPerson);
        UI.addButton("Parents", this::printParents);
        UI.addButton("Children", this::printChildren);
        UI.addButton("GrandChildren", this::grandChildren);
        UI.addButton("Clear", UI::clearText);
        UI.addButton("Quit", UI::quit);
    }

    /**
     * Looks up the name in the database, and stores the Person
     * in the currentPerson field, if there is such a person.
     */
    public void doName(String value){
        Person p = this.getPerson(value);
        if (p==null){
            UI.println(value + " not in database");
        }
        else {
            currentPerson = p;
            UI.printf("found %s in database\n", value);
            UI.println("----------------------");
        }
    }

    /** Load the database */
    public void doLoad(){
        this.loadDatabase(UIFileChooser.open("Choose database file"));
    }

    /**
     * Reads the data from the database file into an ArrayList
     * Reads the data on each line andconstructs a Person object,
     *  and puts the Person object into list
     * The method may assume that the database is correctly formatted,
     *  and does not need to do any checking of the input.
     */
    public void loadDatabase(String filename){
        UI.printf("Reading Database from %s ....\n", filename);
        try{
            Scanner sc = new Scanner(new File(filename));
            while(sc.hasNext())
            {
                this.db.add(new Person(sc.next(), sc.next(), sc.nextInt(), sc.next(), sc.next()));
            }
            sc.close();
        }
        catch(IOException e){UI.println("File failure: "+e);}
    }

    /**
     * Print out names of all the people in the database
     */
    public void printAllNames(){
        UI.println("All names:");
        for(int i = 0; i<this.db.size(); i++)
        {
            UI.println(this.db.get(i).toString());
        }
        UI.println("----------------------");
    }

    /**
     * Looks for and returns the Person with the given name in the database.
     * returns null if not found
     */
    public Person getPerson(String name){
        for(Person x: this.db)
        {
            if(x.getName().equals(name))
            {
                return x;
            }
        }
        return null;
    }

    /**
     * Prints the name and year of birth of the currently selected person.
     * If no current person, prints a message.
     *  [Note, the toString() method of the Person class returns a string
     * containing the name and year of birth of the person.]
     */
    public void printPerson(){
        if(this.currentPerson!=null)
        {
            UI.println(this.currentPerson.toString());
        }
        else
        {
            UI.println("No current person.");
        }
        UI.println("----------------------");
    }

    /**
     * Prints the names of the mother and the father if they are known
     * (or appropriate messages if they are unknown).
     */
    public void printParents(){
        if(this.currentPerson.getMotherName()!=null&&this.currentPerson.getFatherName()!=null)
        {
            UI.println(this.currentPerson.getMotherName());
            UI.println(this.currentPerson.getFatherName());
        }
        else
        {
            UI.println("No record.");
        }
        UI.println("----------------------");
    }

    /**
     * Prints the number of children of the current person,
     * followed by the names and years of birth all the children.
     * Searches the array for Persons who have the currently specified
     *  person as one of their parents.
     * Any such person is added to an ArrayList.
     * It then prints out the information from the array of children.
     */
    private ArrayList<Person> cd = new ArrayList<Person>();
    public void printChildren(){
        for(Person x: this.db)
        {
            if((x.getMotherName()!=null&&x.getMotherName().equals(this.currentPerson.getName()))||(x.getFatherName()!=null&&x.getFatherName().equals(this.currentPerson.getName())))
            {
                this.cd.add(x);
            }
        }
        UI.println(this.cd.size());
        for(int i = 0; i<this.cd.size(); i++)
        {
            UI.println(this.cd.get(i).toString());           
        }
    }

    /** COMPLETION:
     * Prints (to textArea) names of all grandchildren (if any) 
     *  of the currently specified person
     */
    private ArrayList<Person> gd = new ArrayList<Person>();
    public void grandChildren(){
        for(Person y: this.db)
        {
            for(int i=0; i<cd.size(); i++){
                if((y.getMotherName()!=null&&y.getMotherName().equals(this.cd.get(i).getName()))||(y.getFatherName()!=null&&y.getFatherName().equals(this.cd.get(i).getName())))
                {
                    this.gd.add(y);
                }
            }
        }
        
        for(int i = 0; i<this.gd.size(); i++)
        {
            UI.println(this.gd.get(i).getName());
        }
        UI.println("----------------------");
    }

    // Main
    public static void main(String[] arguments){
        Genealogy g = new Genealogy();
    }   
}