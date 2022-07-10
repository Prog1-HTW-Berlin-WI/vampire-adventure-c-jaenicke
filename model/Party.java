package model;

/**
 * Represents the party of the player, contains all vampires and the creator
 * 
 * @author @c-jaenicke Christoph Nicklas Jänicke
 * @author @ilkbi Ilkaan Bingöl
 */
public class Party {
    private CreatorVampire creator = null;
    private Vampire members[] = new Vampire[10];
    private boolean isFull = false;

    /**
     * 
     * @param creator
     */
    public void setCreator(CreatorVampire creator) {
        this.creator = creator;
    }

    /**
     * 
     * @return the creatorVampire as a creatorVampire
     */
    public CreatorVampire getCreator() {
        return this.creator;
    }

    /**
     * 
     * @param value
     */
    public void setFull(boolean value) {
        this.isFull = value;
    }

    /**
     * 
     * @return if the party is full as boolean
     */
    public boolean getFull() {
        return this.isFull;
    }

    /**
     * add new member at first free spot
     * 
     * @param vamp
     */
    public void addMember(Vampire vamp) {
        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                members[i] = vamp;
                checkFull();
                break;
            }
        }
    }

    /**
     * 
     * @return array of members as an array of Vampires
     */
    public Vampire[] getMembers() {
        return this.members;
    }

    /**
     * 
     * @param position
     */
    public void deleteMember(Vampire vampire) {
        for (int i = 0; i < members.length; i++) {
            if (vampire == members[i]) {
                members[i] = null;
                checkFull();
            }
        }
    }

    /**
     * check if the members array is full and set isFull to true or false
     */
    public void checkFull() {
        int full = 0;
        for (int i = 0; i < this.members.length; i++) {
            if (this.members[i] != null) {
                full = full + 1;
            }
        }
        if (full == this.members.length) {
            this.isFull = true;
        } else {
            this.isFull = false;
        }
    }

    /**
     * get number of members in the array
     * 
     * @return amount of valid members
     */
    public int getAmountMembers() {
        int counter = 0;
        for (int i = 0; i < this.members.length; i++) {
            if (this.members[i] != null) {
                counter = counter + 1;
            }
        }
        return counter;
    }
}
