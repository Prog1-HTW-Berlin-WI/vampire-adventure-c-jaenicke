package model;

import java.util.Random;

/**
 * Class Creator Vampire
 * 
 * Represents a playable character
 * Extends the class Vampire
 */
public class CreatorVampire extends Vampire {

    private Vampire descendants[] = new Vampire[10];

    public CreatorVampire(String name, int age, Party playerParty) {
        super(name, age, playerParty);
    }

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

    public void commandToDrinkBlood(Vampire vampire, int amount, Human human) {
        if (amount <= human.getAmountOfBlood()) {
            vampire.drinkBlood(amount, human);
            human.looseBlood(amount, vampire, playerParty);
            System.err.println("\t The minion vampire drank " + amount + " liters of blood.");
        } else {
            vampire.drinkBlood(human.getAmountOfBlood(), human);
            human.looseBlood(human.getAmountOfBlood(), vampire, playerParty);
            System.out.println("\tIt seems, that the human has died.");
        }
    }

    public void commandToAttack(Vampire vampire, Human human) {
        vampire.attackHuman(human);
    }

    /**
     * 
     * @param vampire
     * @param vampireHunter
     * @return boolean if sacrifice was successful
     */
    public boolean sacrifice(Vampire vampire, VampireHunter vampireHunter) {
        Random flee = new Random();
        int chance = flee.nextInt(2);

        playerParty.deleteMember(vampire);

        if (chance == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean flee(Vampire vampire) {
        Random flee = new Random();
        int chance = flee.nextInt(101);

        if (chance <= 40) {
            System.out.println("\tYou managed to escape the vampire hunter!");
            return true;
        } else {
            System.out.println("\tYou failed to escape the vampire hunter!");
            return false;
        }
    }

}