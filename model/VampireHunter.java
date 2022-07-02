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

    public VampireHunter(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getExperiencePoints() {
        return this.experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = this.experiencePoints + experiencePoints;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean getAlive() {
        return this.alive;
    }

    public void setAlive(boolean value) {
        this.alive = value;
    }

    public void addExperiencePoints() {
        this.experiencePoints = this.experiencePoints + 1;
    }

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

    public void takeDamage(int amount) {
        System.out.println("You hit the vampire hunter! He lost " + amount + " energy.");
        this.setEnergy(this.getEnergy() - amount);

        if (this.getEnergy() <= 0) {
            this.setAlive(false);
            System.out.println("The vampire hunter has died!");
        }
    }

}