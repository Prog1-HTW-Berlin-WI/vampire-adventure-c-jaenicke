package model;

import java.util.Random;
import java.util.UUID;

/**
 * Class Vampire
 * 
 */
public class Vampire {
    private String id;
    private String name;
    private int age;
    private int grandness;
    private int hunger = 10;
    private int energy = 100;
    private int power = 10;
    private Vampire creator;
    private boolean isDrinkingBlood;
    private boolean inFight;
    private boolean canControlInstincts;
    private boolean finallyDead;
    Party playerParty;

    /**
     * Simple 
     * 
     * @param name
     * @param age
     * @param playerParty
     */
    public Vampire(String name, int age, Party playerParty) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
        this.playerParty = playerParty;
        // CreatorVampire will not be set in the constructor
    }

    /**
     * 
     * @return String
     */
    public String getID() {
        return this.id;
    }

    /**
     * 
     * @param creator
     */
    public void setCreator(Vampire creator) {
        this.creator = creator;
    }

    /**
     * 
     * @return creator
     */
    public Vampire getCreator() {
        return this.creator;
    }

    /**
     * 
     * @return string
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 
     * @return int
     */
    public int getAge() {
        return this.age;
    }

    /**
     * 
     * @return
     */
    public int getGrandness() {
        return this.grandness;
    }

    /**
     * call when a vampire survives a night
     */
    public void increaseGrandness() {
        this.grandness = this.grandness + 1;
    }

    /**
     * 
     * @return int
     */
    public int getHunger() {
        return this.hunger;
    }

    /**
     * 
     * @param hunger
     */
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    /**
     * 
     * @return boolean
     */
    public boolean getIsDrinkingBlood() {
        return this.isDrinkingBlood;
    }

    /**
     * 
     * @param value
     */
    public void setIsDrinkingBlood(boolean value) {
        this.isDrinkingBlood = value;
    }

    /**
     * 
     * @return boolean
     */
    public boolean getInFight() {
        return this.inFight;
    }

    /**
     * 
     * @param value
     */
    public void setInFight(boolean value) {
        this.inFight = value;
    }

    /**
     * 
     * @return boolean
     */
    public boolean getCanControllInstinct() {
        return this.canControlInstincts;
    }

    /**
     * 
     * @param value
     */
    public void setCanControllInstinct(boolean value) {
        this.canControlInstincts = value;
    }

    /**
     * 
     * @return int
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     * 
     * @param energy
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * 
     * @return boolean
     */
    public boolean getFinallyDead() {
        return this.finallyDead;
    }

    /**
     * 
     * @param value
     */
    public void setFinallyDead(boolean value) {
        this.finallyDead = value;
    }

    /**
     * get the attack damage of a vampire
     * increases with grandness
     * 
     * @return
     */
    public int getPower() {
        return (this.power + this.grandness);
    }

    /**
     * 
     * @param power
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * calm a human down
     * 60% of being successful
     * 25% of human defending
     * 
     * @param human
     */
    public void attackHuman(Human human) {
        Random rand = new Random();
        int chance = rand.nextInt(11);

        if (chance <= 6) {
            if (human.defend() == false) {
                human.setCalm(true);
            }

        }
    }

    /**
     * when drinking form a human
     * 
     * @param amount
     */
    public void drinkBlood(int amount, Human human) {
        if (human.getAmountOfBlood() < amount) {
            this.hunger = this.hunger - human.getAmountOfBlood();
        } else {
            this.hunger = this.hunger - amount;
        }
    }

    /**
     * when attacked by a vampire hunter
     * 
     * @param damage
     */
    public void takeDamage(int damage) {
        this.energy = this.energy - damage;
    }

}
