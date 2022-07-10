package model;

import java.util.Random;

/**
 * Class Creator Vampire
 * 
 * Represents a playable character
 * Extends the class Vampire
 */
public class CreatorVampire extends Vampire {
    // never really used, since the Party class takes this position
    private Vampire descendants[] = new Vampire[10];

    /**
     * constructor, uses super constructor Vampire class
     * 
     * @param name
     * @param age
     * @param playerParty
     */
    public CreatorVampire(String name, int age, Party playerParty) {
        super(name, age, playerParty);
    }

    /**
     * list all descendants of a vampire and their stats
     */
    public void listAllDescendants() {
        // refresh list of descendants
        descendants = playerParty.getMembers();

        System.out.println("Your descendants are:");
        for (int i = 0; i < playerParty.getMembers().length; i++) {
            if (descendants[i] != null) {
                System.out.println("\nName: " + descendants[i].getName());
                System.out.println("\tAge: " + descendants[i].getAge());
                System.out.println("\tGrandness: " + descendants[i].getGrandness());
                System.out.println("\tHunger: " + descendants[i].getHunger());
                System.out.println("\tEnergy: " + descendants[i].getEnergy());
                System.out.println("\tPower: " + descendants[i].getPower());
            }
        }
    }

    /**
     * command a minion vampire to drink an amount of blood from a human
     * gets called by creatorVampire
     * 
     * @param vampire
     * @param amount
     * @param human
     */
    public void commandToDrinkBlood(Vampire vampire, int amount, Human human) {
        if (amount <= human.getAmountOfBlood()) {
            vampire.drinkBlood(amount, human);
            human.looseBlood(amount, vampire, playerParty);
        } else {
            vampire.drinkBlood(human.getAmountOfBlood(), human);
            human.looseBlood(human.getAmountOfBlood(), vampire, playerParty);
        }
    }

    /**
     * command a minion vampire to attack and subdue a human
     * gets called by creatorVampire
     * 
     * @param vampire
     * @param human
     */
    public void commandToAttack(Vampire vampire, Human human) {
        vampire.attackHuman(human);
    }

    /**
     * sacrifice a minion vampire to flee a vampireHunter
     * 
     * @param vampire
     * @param vampireHunter
     * @return boolean if sacrifice was successful
     */
    public boolean sacrifice(Vampire vampire) {
        Random flee = new Random();
        int chance = flee.nextInt(1);

        playerParty.deleteMember(vampire);
        System.out.println("\tYour minion " + vampire.getName() + " died while trying to defend you.");

        if (chance == 0) {
            System.out.println("\tYou managed to escape the vampire hunter!");
            return true;
        } else {
            System.out.println("\tYou failed to escape the vampire hunter!");
            return false;
        }
    }

    /**
     * try to flee vampireHunter without sacrificing anything
     * 
     * @param vampire
     * @return if fleeing was successful as a boolean
     */
    public boolean flee() {
        Random flee = new Random();
        int chance = flee.nextInt(100);

        if (chance <= 40) {
            System.out.println("\tYou managed to escape the vampire hunter!");
            return true;
        } else {
            System.out.println("\tYou failed to escape the vampire hunter!");
            return false;
        }
    }

}