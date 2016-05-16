// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 8 
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;

/**
 * A person has a name, a gender, a date of birth, and the names of their mother and father.
 * The class has three constructors; you can pass in
 *  the five values it needs (name, gender, dob, mother name, father name)  OR
 *  a single String read from a file containing the five values,  OR
 *  a Scanner, and the constructor will read the five values from the Scanner
 */
public class Person{
    // Fields
    private String name;
    private String gender;
    private int dob;      
    private String mother;   
    private String father;   

    // Constructors
    /**
     * Construct a new Person object, given the five values.
     */
    public Person(String n, String g, int dob, String m, String f){
        this.gender = g;
        this.name = n;
        this.dob = dob;
        if (! m.equals("?")) this.mother = m;
        if (! f.equals("?")) this.father = f;
    }

    // Methods
    /**
     * Returns true if and only if the Person has the specified name
     */
    public boolean hasName(String n){
        return (this.name.equalsIgnoreCase(n));
    }

    /**
     * Returns the name of the person
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the gender of the person
     */
    public String getGender(){
        return this.gender;
    }

    /**
     * Returns the DoB of the person
     */
    public int getDoB(){
        return this.dob;
    }

    /**
     * Returns the name of the mother of the person (null if not known)
     */
    public String getMotherName(){
        return this.mother;
    }

    /**
     * Returns the name of the father of the person (null if not known)
     */
    public String getFatherName(){
        return this.father;
    }

    /**
     * Returns true if this person is the same as the given person
     */
    public boolean equals(Person other){
        return (this.name.equals(other.name) && this.dob==other.dob);
    }

    /**
     * Returns a string describing the Person
     */
    public String toString(){
        return this.name+"("+this.gender+"), born:"+this.dob;
    }

}