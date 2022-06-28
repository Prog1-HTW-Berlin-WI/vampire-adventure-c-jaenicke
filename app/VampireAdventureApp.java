package app;

import java.util.Scanner;

import model.CreatorVampire;
import model.Party;
import model.Vampire;

/**
 * Main game function
 *
 */
public class VampireAdventureApp {

    // create party for game
    private static Party playerParty = new Party();

    // create public scanner
    private static Scanner scanner = new Scanner(System.in);

    /**
     * @param args
     */
    public static void main(String[] args) {

        while (true) {
            showMenu();
            //int choice = readUserInput();
            System.out.print("Enter a number between 1 and 6:\t");
            int choice = readIntInput();
            handle(choice);
            System.out.println("====================");
        }
    }

    /**
     * Read user input for main menu
     * Not used anymore!
     * 
     * @return
     */
    private static int readUserInput() {
        System.out.print("\nPlease choose a number between 1 and 6:\t");
        int choiceInternal = 0;
        try {
            choiceInternal = scanner.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return choiceInternal;
    }

    /**
     * handle user input, depending on choice
     * 
     * @param choice
     */
    private static void handle(int choice) {
        switch (choice) {
            case 1:
                createVampire();
                break;
            case 2:
                showVampires();
                break;
            case 3:
                listAllVampires();
                break;
            case 4:
                deleteVampire();
                break;
            case 5:
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
                "(1)\t Create Vampire",
                "(2)\t Show Selected Vampire",
                "(3)\t List all Vampires",
                "(4)\t Delete Vampire",
                "(5)\t Start nightly adventure",
                "(6)\t Quit",
        };

        System.out.println("\nVampire Adventures 1.1\n");
        for (int i = 1; i < menuItems.length; i++) {
            System.out.println(menuItems[i]);
        }

    }

    /**
     * show a list of vampires
     * with ability to select vampire
     * hitting 0 goes back to main menu
     */
    private static void showVampires() {
        System.out.println("====================");
        System.out.println("\nAll vampires of your group\n");

        Vampire[] members = playerParty.getMembers();

        if (playerParty.getCreator() != null) {
            System.out.println("(1)\tCreator Vampire");
        } else {
            System.out.println("No creator vampire available! Create one in the main menu using (1)");
        }

        listMembers(2);

        while (true) {
            System.out.print("\nChoose a vampire to show the stats of or (0) to go to the main menu: ");

            int input = readIntInput();

            if (input == 0) {
                // go back to main menu
                return;
            } else if (input == 1) {
                showVampireStats(playerParty.getCreator());
                break;
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
        System.out.println("====================");
        System.out.println("\nVampire Stats\n");

        System.out.println("You have selected " + vamp.getName());

        System.out.println("Name: " + vamp.getName());
        System.out.println("Age: " + vamp.getAge());
        // TODO show more attributes here

        System.out.println("Press 0 to go back to the main menu!");

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
        System.out.println("====================");
        System.out.println("\nDelete a vampire\n");

        Vampire[] members = playerParty.getMembers();

        listMembers(1);

        while (true) {
            System.out.print("\nChoose a vampire to delete or (0) to go to the main menu: ");

            int input = readIntInput();

            if (input == 0) {
                // go back to main menu
                return;
            } else if (members[input - 1] == null) {
                System.out.println("That vampire doesn't exist!");
            } else {
                playerParty.deleteMember(input - 1);
                return;
            }
        }

    }

    /**
     * show list of all party members
     */
    private static void listAllVampires() {
        System.out.println("====================");
        System.out.println("\nAll vampires of your group\n");

        Vampire[] members = playerParty.getMembers();

        System.out.println("The creator vampire is:");
        System.out.println(playerParty.getCreator());
        System.out.println("\n");

        int emptyCounter = 0;
        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                emptyCounter = emptyCounter + 1;
                if (emptyCounter == members.length) {
                    System.out.println("Looks like you don't have any members or they all have died!");
                }
            } else {
                System.out.println("Member " + (i + 1) + "\t" + members[i]);
            }

        }

        // TODO wait until key press to go back
        waitSeconds(3);

    }

    /**
     * create start vampires
     */
    private static void createVampire() {
        System.out.println("====================");
        System.out.println("\nVampire Creation Menu\n");

        // create creator vampire
        System.out.println("Create your creator vampire");
        System.out.print("What is the name of the vampire: ");
        String name = readStringInput();

        System.out.print("How old is the vampire: ");
        int age = readIntInput();

        CreatorVampire cVampire = new CreatorVampire(name, age);
        playerParty.setCreator(cVampire);

        // create first minion
        System.out.println("\nCreate your first minion vampire");

        System.out.print("What is the name of the vampire: ");
        name = readStringInput();

        System.out.print("How old is the vampire: ");
        age = readIntInput();

        Vampire mVamp1 = new Vampire(name, age);
        mVamp1.setCreator(cVampire);
        playerParty.addMember(mVamp1);

        // create second minion
        System.out.println("\nCreate your second minion vampire");

        System.out.print("What is the name of this vampire: ");
        name = readStringInput();

        System.out.print("How old is this vampire: ");
        age = readIntInput();

        Vampire mVamp2 = new Vampire(name, age);
        mVamp2.setCreator(cVampire);
        playerParty.addMember(mVamp2);

        // TODO wait until key press to go back
        waitSeconds(3);
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
            System.out.println("No number or input detected! Please try again...");
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

    private static void listMembers(int offset) {
        Vampire[] members = playerParty.getMembers();
        int emptyCounter = 0;

        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                emptyCounter = emptyCounter + 1;
                if (emptyCounter == members.length) {
                    System.out.println("Looks like you don't have any members, maybe they all died!\n");
                }
            } else {
                System.out.println("(" + (i + offset) + ")\t" + members[i]);
            }
        }
    }

}
