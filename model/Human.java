package model;

import java.util.Random;
import java.util.UUID;

/**
 * Class Human
 * 
 * Represents a person to feed on
 * 
 * @author @c-jaenicke Christoph Nicklas Jänicke
 * @author @ilkbi Ilkaan Bingöl
 */
public class Human {
    private String id;
    private String name;
    private int age;
    private int amountOfBlood;
    private boolean calm = false; // if the human has been sedated by a vampire
    private boolean deceased = false; // if human is dead, or has become a vampire

    /**
     * constructor
     * 
     * @param name
     * @param age
     */
    public Human(String name, int age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;

        Random flee = new Random();
        int chance = flee.nextInt(3);
        this.amountOfBlood = (chance + 6);
    }

    /**
     * 
     * @return id as String
     */
    public String getId() {
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
     * @return age as int
     */
    public int getAge() {
        return this.age;
    }

    /**
     * 
     * @return int, amount of blood human has
     */
    public int getAmountOfBlood() {
        return this.amountOfBlood;
    }

    /**
     * 
     * @param amountOfBlood as int
     */
    public void setAmountOfBlood(int amountOfBlood) {
        this.amountOfBlood = amountOfBlood;
    }

    /**
     * 
     * @return boolean, if the human is calm
     */
    public boolean getCalm() {
        return this.calm;
    }

    /**
     * 
     * @param calm as boolean
     */
    public void setCalm(boolean calm) {
        this.calm = calm;
    }

    /**
     * 
     * @return boolean, if the human is dead or a vampire
     */
    public boolean getDeceased() {
        return this.deceased;
    }

    /**
     * 
     * @param value as boolean
     */
    public void setDeceased(boolean value) {
        this.deceased = value;
    }

    /**
     * the human defending himself when a vampire attacks
     * 
     * @return if the defense was successful as boolean
     */
    public boolean defend() {
        Random rand = new Random();
        int chance = rand.nextInt(100);

        if (chance <= 25) {
            System.out.println("\tThe human defended himself!");
            return true;
        } else {
            System.out.println("\tThe human couldn't defend himself!");
            return false;
        }
    }

    /**
     * chance to flee from a vampire is 20%
     * 
     * @return if the escape was successful as boolean
     */
    public boolean flee() {
        Random flee = new Random();
        int chance = flee.nextInt(100);

        if (chance <= 20) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * turn human into vampire
     * 
     * @param vampire
     */
    public void turnIntoVampire(Vampire vampire, Party playerParty) {
        System.out.println("\tThe human is turning into a vampire!");
        Vampire newVampire = new Vampire(this.name, this.age, playerParty);
        newVampire.setCreator(vampire);
        if (playerParty.getFull() == true) {
            System.out.println(
                    "\tLooks like you cant command any more vampires! The now vampire storms off into the night.");
        } else {
            playerParty.addMember(newVampire);
            System.out.println("\tThe now vampire, " + this.getName() + ", joins your group!");
        }
    }

    /**
     * loose amount of blood and turn human into vampire or die
     * 
     * @param amount
     * @param vampire
     */
    public void looseBlood(int amount, Vampire vampire, Party playerParty) {
        this.amountOfBlood = this.amountOfBlood - amount;

        if (this.amountOfBlood <= 0) {
            this.deceased = true;
            System.out.println("\tThe human has died! You drank all of its blood!");
        } else if (this.amountOfBlood < 5) {
            this.deceased = true;
            this.turnIntoVampire(vampire, playerParty);
        } else {
            this.calm = false;
            System.out.println("\tYou drink its blood and leave.");
        }
    }

}
