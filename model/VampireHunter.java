package model;

import java.util.Random;
import java.util.UUID;

/**
 * Class VampireHunter
 * 
 * Represents an enemy
 */
public class VampireHunter {
    private String id;
    private String name;
    private int experiencePoints = 0;
    private int energy = 1000;
    private boolean alive = true;

    /**
     * constructor
     * 
     * @param name
     */
    public VampireHunter(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    /**
     * 
     * @return id as String
     */
    public String getID() {
        return this.id;
    }

    /**
     * 
     * @return name as String
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return number of experiencePoints as int
     */
    public int getExperiencePoints() {
        return this.experiencePoints;
    }

    /**
     * 
     * @param experiencePoints
     */
    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = this.experiencePoints + experiencePoints;
    }

    /**
     * 
     * @return amount of energy as int
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
     * @return if the vampireHunter is still alive as boolean
     */
    public boolean getAlive() {
        return this.alive;
    }

    /**
     * 
     * @param value
     */
    public void setAlive(boolean value) {
        this.alive = value;
    }

    /**
     * add an experience point
     */
    public void addExperiencePoints() {
        this.experiencePoints = this.experiencePoints + 1;
    }

    /**
     * attack a vampire
     * 
     * @param vampire
     */
    public void attack(Vampire vampire) {
        Random rand = new Random();
        int chance = rand.nextInt(2);

        if (chance == 1) {
            vampire.takeDamage(3);
            System.out.println("The vampire hunter managed to hit you! You loose 3 energy.");
        } else {
            System.out.println("The vampire hunters misses!");
        }
    }

    /**
     * take damage dealt by a vampire
     * 
     * @param amount
     */
    public void takeDamage(int amount) {
        System.out.println("You hit the vampire hunter! He lost " + amount + " energy.");
        this.setEnergy(this.getEnergy() - amount);

        if (this.getEnergy() <= 0) {
            this.setAlive(false);
            System.out.println("The vampire hunter has died!");
        }
    }

}