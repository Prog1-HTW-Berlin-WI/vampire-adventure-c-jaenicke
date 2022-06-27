package model;

/**
 * Class Vampire
 * 
 * Represents a minion
 */
public class Vampire {
    // TODO add attributes

    private String name;
    private int age;
    private CreatorVampire creator;

    // TODO add methods
    public Vampire(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void setCreator(CreatorVampire creator){
        this.creator = creator;
    }

    public CreatorVampire getCreator(){
        return this.creator;
    }

    // no setName, since name never changes

    public String getName(){
        return this.name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }
}
