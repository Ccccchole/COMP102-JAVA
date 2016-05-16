// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 8
 * Name:Jinglu Xu
 * E-mail:mixlulu@gmail.com
 */

import ecs100.*;
import java.util.*;
import java.io.*;

/**
 *  Creates an array that holds Person objects, with data read from the
 *   file "family.txt".
 *  Then allows the user to ask four questions about the people:
 *    What are their names
 *    Who is the mother of Catherine
 *    Who is the mother of Michael
 *    Who are the children of Michael
 *   Note: The family tree represented in the file has five people:
 *    Carole and Michael are the parents of Catherine, Philippa and James.
 *    Carole and Michael's parents are unknown.
 */

public class PersonExercise{

    ArrayList<Person> data = new ArrayList<Person>();

    /**
     * Add Buttons to the UI
     */
    public PersonExercise(){
        UI.addButton("Load DB", this::doLoad);
        UI.addButton("All Names", this::allNames);
        UI.addButton("Mother of Catherine", this::doMother1);
        UI.addButton("Mother of Michael", this::doMother2);
        UI.addButton("Children of Michael", this::doChildren);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1); // expand Text pane
    }

    public void doMother1(){
        this.findMotherOf("Catherine");
    }

    public void doMother2(){
        this.findMotherOf("Michael");
    }

    public void doChildren(){
        this.findChildrenOfFather("Michael");
    }

    /**
     * Read each line of the file, (name, gender, dob, mother's name, father's name)
     *  constructing a Person object for the line
     *  and putting it in the ArrayList
     * Close the file and print "Loaded" when finished.
     */
    public void doLoad(){
        try{
            Scanner scan = new Scanner(new File("family.txt"));
            this.data.clear();
            while (scan.hasNext()){
                this.data.add(new Person(scan.next(), scan.next(), scan.nextInt(), scan.next(), scan.next()));
            }
            scan.close();
            UI.println("Loaded");
        }
        catch(IOException e){UI.println("Error while reading database file");}

    }

    /**
     * Print out the name, gender, and year of birth of each person in the array.
     * Note, the Person class has a toString method that is useful.
     */
    public void allNames(){
        for(int i = 0; i<this.data.size(); i++)
        {
            UI.println(this.data.get(i).toString());
        }
    }

    /**
     * Steps through the list to find a person with the specified name
     * Then prints out that person's mother, if known
     * otherwise print out "Mother unknown"
     */
    public void findMotherOf(String name){
        for(Person p: this.data)
        {
            if(p.getName().equals(name))
            {
                if(p.getMotherName()!=null)
                {
                    UI.println(p.getMotherName());
                }
                else
                {
                    UI.println("Mother unknown");
                }
            }
        }

    }

    /**
     * Steps through the list to find each person whose father is the specified name
     * Prints out the name of each such person
     */
    public void findChildrenOfFather(String name){
        for(Person p: this.data)
        {
            if(p.getFatherName()!=null&&p.getFatherName().equals(name))
            {
                UI.println(p.getName());
            }
        }

    }

    public static void main(String[] args){
        new PersonExercise();
    }

}