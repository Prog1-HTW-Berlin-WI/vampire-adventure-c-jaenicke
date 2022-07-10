package app;

import java.util.Scanner;
import java.util.Random;

import model.City;
import model.CreatorVampire;
import model.Human;
import model.Party;
import model.Vampire;
import model.VampireHunter;

/**
 * Main game function
 *
 */
public class VampireAdventureApp {

    // create party for game
    private static Party playerParty = new Party();

    // create city
    private static City berlin = new City();

    // create public scanner
    private static Scanner scanner = new Scanner(System.in);

    /**
     * @param args
     */
    public static void main(String[] args) {

        while (true) {
            showMenu();
            // int choice = readUserInput();
            System.out.print("\nEnter a number between 1 and 6:\t");
            int choice = readIntInput();
            handle(choice);
            System.out.println("\n========================================");
        }
    }

    /**
     * Read user input for main menu
     * Not used anymore!
     * 
     * @return
     */
    // private static int readUserInput() {
    // System.out.print("\nPlease choose a number between 1 and 6:\t");
    // int choiceInternal = 0;
    // try {
    // choiceInternal = scanner.nextInt();
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }

    // return choiceInternal;
    // }

    /**
     * handle user input, depending on choice
     * 
     * @param choice
     */
    private static void handle(int choice) {
        switch (choice) {
            case 1:
                // createVampire();
                // for testing purposes, use predefined vampires
                debugCreateVampire();
                break;
            case 2:
                // show list of vampires, choose one, show stats
                showVampires();
                break;
            case 3:
                // list all vampires with stats
                listAllVampires();
                break;
            case 4:
                // show list of vampires and choose one to delete
                deleteVampire();
                break;
            case 5:
                // start game
                startAdventure();
                break;
            case 6:
                // exit with code 0 when quitting game
                System.exit(0);
                break;
            default: {
                System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                showMenu();
            }
                break;
        }
    }

    /**
     * list options available in main menu
     */
    private static void showMenu() {

        String menuItems[] = { "",
                "(1)\t Create Vampires",
                "(2)\t Show Statistics of Vampires (Show Selected Vampire)",
                "(3)\t List all Vampires",
                "(4)\t Delete Minion Vampires",
                "(5)\t Start Nightly Adventure",
                "(6)\t Quit",
        };
        System.out.println("========================================");
        System.out.println("\nVampire Adventures 1.0\n");
        for (int i = 1; i < menuItems.length; i++) {
            System.out.println(menuItems[i]);
        }
    }

    /**
     * Main function for game actions and events
     */
    private static void startAdventure() {
        // for colorful output of game won or lost
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";

        // random int generator
        Random rand = new Random();

        // check if a creatorVampire exists
        if (playerParty.getCreator() == null) {
            System.out.println("\nYou don't have a creator vampire! Create on in the main menu!");
            waitSeconds(2);
            return;
        }

        // Start game night
        System.out.println("\nRise vampires, the sun has gone down and there is lots that needs to be done.");
        // run for 15 rounds
        for (int i = 0; i < 15; i++) {
            System.out.println("\n====================");
            System.out.println("\n Time is running: Round " + (i + 1));
            int chance = rand.nextInt(10);

            // 70% chance to meet a human
            // 20% change to meet a vampireHunter
            // 10% chance that nothing happens
            if (chance < 7) {
                meetHuman();
            } else if ((7 <= chance) && (chance < 9)) {
                System.out.println("\nA Vampire Hunter has crossed your way. Your time has come...");
                if (meetVampireHunter() == true) {
                    waitSeconds(2);
                    //return;
                } else {
                    System.out.println(ANSI_RED
                            + "\n========================================\nYou lost the night, your group is still hungry!\n========================================"
                            + ANSI_RESET);
                    waitSeconds(2);
                    return;
                }

            } else {
                System.out.println("\nYou didn't meet anyone.");
            }
            waitSeconds(2);
        }

        // check if player has won the game
        if (gameWon() == true) {
            System.out.println(ANSI_GREEN
                    + "\n========================================\n========================================\nYOU WON THE GAME\n========================================\n========================================"
                    + ANSI_RESET);
            waitSeconds(3);
            return;
        } else {
            System.out.println(ANSI_RED
                    + "\n========================================\nYou lost the night, your group is still hungry!\n========================================"
                    + ANSI_RESET);
            waitSeconds(3);
        }
    }

    /**
     * Meeting a human
     */
    private static void meetHuman() {
        berlin.populateCity();
        Vampire[] members = playerParty.getMembers();
        Human human = berlin.getHuman();
        System.out.println("\nYou meet a human! Its name is " + human.getName() + ".");
        // meet human

        System.out.print("\nDo you want to send a minion (0) or attack yourself (1)? ");

        int input = readIntInput();
        if (input == 1) {
            // attack
            playerParty.getCreator().attackHuman(human);

        } else if (input == 0) {
            // minion should attack
            System.out.print("\n");
            if (listMembersStats(0) == false) {
                System.out.println("You missed your chance and the human ran away!");
                return;

            } else {
                System.out.print("\nWhich minion should attack? ");
                // choose a minion
                while (true) {
                    int choice = readIntInput();
                    if ((playerParty.getMembers().length < choice) || (choice < 0)) {
                        System.out.println("That is not a valid vampire!");
                    } else if (members[choice] == null) {
                        System.out.println("That is not a valid vampire!");
                    } else {
                        Vampire vampire = (members[choice]);
                        playerParty.getCreator().commandToAttack(vampire, human);
                        break;
                    }
                }
            }
            // TODO not having minions still gives the "attack has failed text"

        } else {
            System.out.println("Invalid input!");
        }

        // check if human is calm
        if (human.getCalm() != true) {
            System.out.println("\tYour attack has failed!\n\tYou run away and hide.");

        } else {
            System.out.println("\tYour attack was successful, the human is calm.");
            System.out.print("\nWho should drink the humans blood, a minion (0) or you (1)? ");
            input = readIntInput();
            if (input == 1) {
                // drink blood as creator vampire
                System.out.print("How much do you want to drink? ");
                int amount = readIntInput();
                playerParty.getCreator().drinkBlood(amount, human);
                human.looseBlood(amount, playerParty.getCreator(), playerParty);

            } else if (input == 0) {
                while (true) {
                    // minion should drink
                    if (listMembersStats(0) == false) {
                        break;
                    }
                    System.out.print("\nWhich minion should drink? ");
                    // choose a minion
                    while (true) {
                        int choice = readIntInput();
                        if ((playerParty.getMembers().length < choice) || (choice < 0)) {
                            System.out.println("That is not a valid vampire!");
                        } else if (members[choice] == null) {
                            System.out.println("That is not a valid vampire!");
                        } else {
                            Vampire vampire = (members[input]);
                            System.out.print("\nHow much should the minion " + vampire.getName() + " drink? ");
                            int amount = readIntInput();
                            playerParty.getCreator().commandToDrinkBlood(vampire, amount, human);
                            // break;
                            return;
                        }
                    }
                }

            } else {
                System.out.println("Invalid input!");
            }
        }

    }

    /**
     * meet a vampire hunter
     * 
     * @return if the game is lost through the hunter
     */
    private static boolean meetVampireHunter() {
        // initiate
        // create vampireHunter if there is none or he has died
        if ((berlin.getHunter() == null) || (berlin.getHunter().getEnergy() <= 0)) {
            berlin.createHunter();
        }

        VampireHunter hunter = berlin.getHunter();
        Vampire[] members = playerParty.getMembers();

        System.out.println("Its the " + hunter.getName() + ".");

        while (true) {
            System.out.println(
                    "\n(1) Sacrifice a Descendant\n(2) Flee\n(3) Fight yourself\n(4) Call it a night and loose");
            System.out.print("\nWhat do you want to do? ");
            int input = readIntInput();
            if (input == 1) {
                // sacrifice a minion

                while (true) {
                    // list all minions
                    if (listMembersStats(0) == false) {
                        break;
                    } else {
                        System.out.print("\nWhich minion do you want to sacrifice? ");
                        input = readIntInput();
                        if ((playerParty.getMembers().length < input) || (input < 0)) {
                            System.out.println("That is not a valid vampire!");
                        } else if (members[input] == null) {
                            System.out.println("That is not a valid vampire!");
                        } else {
                            Vampire vampire = (members[input]);
                            hunter.addExperiencePoints();
                            if (playerParty.getCreator().sacrifice(vampire) == true) {
                                return true;
                            } else {
                                break;
                            }
                        }
                    }
                }

            } else if (input == 2) {
                // flee from hunter
                if (playerParty.getCreator().flee() == true) {
                    return true;
                } else {
                    if (hunter.attack(playerParty.getCreator()) == true) {
                        playerParty.getCreator().takeDamage(10);
                    }
                }
            } else if (input == 3) {
                // fight the hunter
                System.out.println("You attack the vampire hunter!");
                int power = playerParty.getCreator().getPower();
                hunter.takeDamage(power);
                if (hunter.getAlive() == true) {
                    hunter.attack(playerParty.getCreator());

                } else {
                    return true;
                    //break;
                }
            } else if (input == 4) {
                // give up
                System.out.println("You surrender to the vampire hunter! And loose the game!");
                return false;
            } else {
                System.out.println("Invalid choice!");
            }
        }
        //return true;
    }

    /**
     * check if the game is won
     * when creator vampire is full and all minions are also full
     * 
     * @return if all existing vampires are full
     */
    private static boolean gameWon() {
        int amount = playerParty.getAmountMembers();
        Vampire[] members = playerParty.getMembers();
        int counter = 0;

        if (playerParty.getCreator().getHunger() <= 0) {
            for (int i = 0; i < members.length; i++) {
                if (members[i] != null) {
                    if (members[i].getHunger() <= 0) {
                        counter = counter + 1;
                    }
                }

            }
            if (counter == amount) {
                return true;

            }
        }

        return true;
    }

    /**
     * show a list of vampires
     * with ability to select vampire
     * hitting 0 goes back to main menu
     */
    private static void showVampires() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("\nAll vampires of your group\n");

            Vampire[] members = playerParty.getMembers();

            if (playerParty.getCreator() != null) {
                System.out.println("(1)\t" + playerParty.getCreator().getName());
            } else {
                System.out.println("No creator vampire available! Create one in the main menu!\n");
            }

            listMembers(2);

            System.out.print("\nChoose a vampire to show the stats of or (0) to go to the main menu: ");

            int input = readIntInput();
            if (input == 0) {
                // go back to main menu
                return;
            } else if (input == 1) {
                showVampireStats(playerParty.getCreator());
                return;
            } else if ((members.length + 2 < input) || (input < 0)) {
                System.out.println("That is not a valid vampire!");
            } else if (members[input - 2] == null) {
                System.out.println("That is not a valid vampire!");
            } else {
                showVampireStats(members[input - 2]);
            }
        }
    }

    /**
     * show stats of a vampire
     * 
     * @param vamp
     */
    public static void showVampireStats(Vampire vamp) {
        System.out.println("\n========================================");
        System.out.println("\nVampire Stats\n");

        System.out.println("You have selected " + vamp.getName() + "\n");

        System.out.println("Name: " + vamp.getName());
        System.out.println("Age: " + vamp.getAge());
        System.out.println("Hunger: " + vamp.getHunger());
        System.out.println("Energy: " + vamp.getEnergy());
        System.out.println("Power: " + vamp.getPower());
        System.out.println("Grandness: " + vamp.getGrandness());

        System.out.print("\nEnter 0 to go back to the menu: ");
        while (true) {
            int input = readIntInput();
            if (input == 0) {
                // go back to main menu
                return;
            }
        }
    }

    /**
     * show list of vampires
     * choose a vampire
     * delete a vampire
     */
    private static void deleteVampire() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("\nDelete a vampire\n");

            Vampire[] members = playerParty.getMembers();

            listMembers(1);

            System.out.print("\nChoose a vampire to delete or (0) to go to the main menu: ");

            int input = readIntInput();
            if (input == 0) {
                // go back to main menu
                return;
            } else if ((members.length + 2 < input) || (input < 0)) {
                System.out.println("That is not a valid vampire!");
            } else if (members[input - 1] == null) {
                System.out.println("That vampire doesn't exist!");
            } else {
                Vampire vampire = members[input - 1];
                playerParty.deleteMember(vampire);
            }
        }

    }

    /**
     * show list of all party members
     */
    private static void listAllVampires() {
        System.out.println("\n========================================");
        System.out.println("\nAll vampires of your group\n");

        if (playerParty.getCreator() != null) {
            System.out.println("The creator vampire is: " + playerParty.getCreator().getName());
            System.out.println("\tAge: " + playerParty.getCreator().getAge() +
                    "\n\tHunger: " + playerParty.getCreator().getHunger() +
                    "\n\tEnergy: " + playerParty.getCreator().getEnergy() +
                    "\n\tPower: " + playerParty.getCreator().getPower() +
                    "\n\tGrandness: " + playerParty.getCreator().getGrandness());
        } else {
            System.out.println("No creator vampire exists! Create one in the main menu!\n");
        }

        System.out.println("\nYour minions are:");
        listMembersStats(1);

        // wait for user to press 0 to go back to main menu
        System.out.print("\nEnter 0 to go back to the menu: ");
        while (true) {
            int input = readIntInput();
            if (input == 0) {
                return;
            }
        }
    }

    /**
     * create start vampires
     */
    private static void createVampire() {
        System.out.println("\n========================================");
        System.out.println("\nVampire Creation Menu\n");

        // create creator vampire
        System.out.println("Create your creator vampire");
        System.out.print("\tWhat is the name of the vampire: ");
        String name = readStringInput();

        System.out.print("\tHow old is the vampire: ");
        int age = readIntInput();

        CreatorVampire cVampire = new CreatorVampire(name, age, playerParty);
        playerParty.setCreator(cVampire);

        // create first minion
        System.out.println("\nCreate your first minion vampire");

        System.out.print("\tWhat is the name of the vampire: ");
        name = readStringInput();

        System.out.print("\tHow old is the vampire: ");
        age = readIntInput();

        Vampire mVamp1 = new Vampire(name, age, playerParty);
        mVamp1.setCreator(cVampire);
        playerParty.addMember(mVamp1);

        // create second minion
        System.out.println("\nCreate your second minion vampire");

        System.out.print("\tWhat is the name of this vampire: ");
        name = readStringInput();

        System.out.print("\tHow old is this vampire: ");
        age = readIntInput();

        Vampire mVamp2 = new Vampire(name, age, playerParty);
        mVamp2.setCreator(cVampire);
        playerParty.addMember(mVamp2);

        // wait for user to press 0 to go back to main menu
        System.out.print("\nEnter 0 to go back to the menu: ");
        while (true) {
            int input = readIntInput();
            if (input == 0) {
                return;
            }
        }
    }

    /**
     * Create predefined Vampires for testing purposes
     * "Create Vampire" needs to be called first
     */
    private static void debugCreateVampire() {
        System.out.println("\n========================================");
        System.out.println("\n!!--- DEBUG Vampire Creation Menu\n");

        // TODO when the vampire class is expanded, fill out more of the attributes

        System.out.println("Creating creator vampire\nName: The Creator Vampire\n Age: 300");
        CreatorVampire cVampire = new CreatorVampire("The Creator Vampire", 300, playerParty);
        playerParty.setCreator(cVampire);

        System.out.println("\nCreating first minion vampire\nName: First Minion Vampire\n Age: 100");
        Vampire mVamp1 = new Vampire("First Minion Vampire", 100, playerParty);
        mVamp1.setCreator(cVampire);
        playerParty.addMember(mVamp1);

        System.out.println("\nCreating second minion vampire\nName: Second Minion Vampire\n Age: 200");
        Vampire mVamp2 = new Vampire("Second Minion Vampire", 200, playerParty);
        mVamp2.setCreator(cVampire);
        playerParty.addMember(mVamp2);

        // wait for user to press 0 to go back to main menu
        System.out.print("\nEnter 0 to go back to the menu: ");
        while (true) {
            int input = readIntInput();
            if (input == 0) {
                return;
            }
        }
    }

    /**
     * reads user input, if no input is detected, try again
     * 
     * @return
     */
    private static String readStringInput() {
        while (!scanner.hasNext()) {
            System.out.println("No input was detected! Please try again...");
            scanner.next();
        }
        String input = scanner.next();

        return input;
    }

    /**
     * read input and check if it is an int, if not try again
     * 
     * @return
     */
    private static int readIntInput() {
        while (!scanner.hasNextInt()) {
            // System.out.println("No number or input detected! Please try again...");
            System.out.println("That is not a valid input! Please try again...");
            scanner.next();
        }
        int input = scanner.nextInt();

        return input;
    }

    /**
     * Sleep for a specified amount of seconds
     * 
     * @param seconds
     */
    private static void waitSeconds(int seconds) {
        int time = seconds * 1000;

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * list all members of the party
     * offset is used when the input of the menu is changing
     * e.g. 0 is reserved for going back, offset would be 1
     * 
     * @param offset
     */
    private static void listMembers(int offset) {
        Vampire[] members = playerParty.getMembers();
        int emptyCounter = 0;

        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                emptyCounter = emptyCounter + 1;
                if (emptyCounter == members.length) {
                    System.out.println("Looks like you don't have any minions, maybe they all died!");
                }
            } else {
                System.out.println("(" + (i + offset) + ")\t" + members[i].getName());
            }
        }
    }

    /**
     * list all members of the party with all important statistics
     * offset is used when the input of the menu is changing
     * e.g. 0 is reserved for going back, offset would be 1
     * 
     * @param offset
     */
    private static boolean listMembersStats(int offset) {
        Vampire[] members = playerParty.getMembers();
        int emptyCounter = 0;

        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                emptyCounter = emptyCounter + 1;
                if (emptyCounter == members.length) {
                    System.out.println("Looks like you don't have any minions, maybe they all died!");
                    return false;
                }
            } else {
                System.out.println("\n(" + (i + offset) + ")\t" + members[i].getName());
                System.out.println("\tAge: " + members[i].getAge() +
                        "\n\tHunger: " + members[i].getHunger() +
                        "\n\tEnergy: " + members[i].getEnergy() +
                        "\n\tPower: " + members[i].getPower() +
                        "\n\tGrandness: " + members[i].getGrandness());
            }
        }
        return true;
    }

}
