package model;

/**
 * Represents the party of the player, contains all vampires and the creator
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
     * @return
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
     * @return
     */
    public boolean getFull() {
        return this.isFull;
    }

    /**
     * add new member at first free spot
     * @param vamp
     */
    public void addMember(Vampire vamp) {
        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                members[i] = vamp;
                break;
            }
        }
    }

    /**
     * 
     * @return
     */
    public Vampire[] getMembers(){
        return this.members;
    }

    /**
     * 
     * @param position
     */
    public void deleteMember(int position){
        members[position] = null;
    }
}
