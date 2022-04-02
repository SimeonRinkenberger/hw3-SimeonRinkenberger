/**
 * The AssassinManager class provides the methods needed to run AssassinMain.java
 *
 * @author Simeon Rinkenberger
 * @date 4-02-22
 */

import java.util.*;

public class AssassinManager {
    private AssassinNode killRingStart; // the node of the start of the kill ring
    private AssassinNode graveyardStart; // the node of the most recently killed assassin

    /** Initializes killRingStart and graveyardStart
     *  pre: names != null and names.isEmpty() == false otherwise throws an IllegalArgumentException */
    public AssassinManager(List<String> names) {
        if (names == null || names.isEmpty()) throw new IllegalArgumentException("List of names cannot be null or empty");

        graveyardStart = null;
        killRingStart = null;

        for (int i = names.size() - 1; i >= 0; i--) {
            killRingStart = new AssassinNode(names.get(i), killRingStart);
        }
    }

    /**
     * prints the kill ring
     * post: kill ring is outputted to the console
     **/
    public void printKillRing() {
        if (isGameOver()) {
            System.out.println("    " + killRingStart.name + " won the game!");
        } else {
            AssassinNode assassin = killRingStart;
            while (assassin != null) {
                if (assassin.next == null) {
                    System.out.print("    " + assassin.name + " is stalking ");
                    System.out.println(killRingStart.name);
                } else {
                    System.out.println("    " + assassin.name + " is stalking " + assassin.next.name);
                }
                assassin = assassin.next;
            }
        }
    }

    /**
     * prints the graveyard in order of most recent killing first
     * post: graveyard is outputted to the console
     **/
    public void printGraveyard() {
        AssassinNode dead = graveyardStart;
        while (dead != null) {
            System.out.println("    " + dead.name + " was killed by " + dead.killer);
            dead = dead.next;
        }
    }

    /**
     * returns whether the passed name is in the kill ring
     **/
    public boolean killRingContains(String name) {
        return contains(name, killRingStart);
    }

    /**
     * returns whether the passed name is in the graveyard
     **/
    public boolean graveyardContains(String name) {
        return contains(name, graveyardStart);
    }

    /**
     * a helper method for killRingContains and graveyardContains which returns whether the passed name is in the
     * specified list to search
     **/
    private boolean contains(String name, AssassinNode start) {
        AssassinNode assassin = start;
        while (assassin != null) {
            if (assassin.name.toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT))) {
                return true;
            }
            assassin = assassin.next;
        }
        return false;
    }

    /**
     * returns whether the game is complete by returning whether there is more than one person in the kill ring
     **/
    public boolean isGameOver() {
        return killRingStart.next == null;
    }

    /**
     * returns the name of the winner of the game it is over
     **/
    public String winner() {
        if (isGameOver()) {
            return killRingStart.name;
        } else {
            return null;
        }
    }

    /**
     * kills the person with the passed name
     * pre: game must not be over and the kill ring must contain the passed name otherwise throws an exception
     * post: the person killed is removed from the kill ring and added to the graveyard
     **/
    public void kill(String name) {
        if (isGameOver()) throw new IllegalStateException("cannot kill with game already over");
        if (!killRingContains(name)) throw new IllegalArgumentException("given name not part of kill ring");

        AssassinNode killed;
        AssassinNode assassin = killRingStart;

        // if the name to kill is at the start of the kill ring
        if (assassin.name.equalsIgnoreCase(name)) {
            killRingStart = killRingStart.next;
            assassin.next = graveyardStart;
            graveyardStart = assassin;
            assassin = killRingStart;
            while(assassin.next != null){
                assassin = assassin.next;
            }
            graveyardStart.killer = assassin.name;
        } else {
            while (assassin.next != null) {
                if (assassin.next.name.equalsIgnoreCase(name)) {
                    killed = assassin.next;
                    killed.killer = assassin.name;
                    assassin.next = assassin.next.next;
                    killed.next = null;

                    AssassinNode dead = graveyardStart;
                    graveyardStart = killed;
                    graveyardStart.next = dead;
                    break;
                }
                assassin = assassin.next;
            }
        }
    }

    //////// DO NOT MODIFY AssassinNode.  You will lose points if you do. ////////
    /**
     * Each AssassinNode object represents a single node in a linked list
     * for a game of Assassin.
     */
    private static class AssassinNode {
        public final String name;  // this person's name
        public String killer;      // name of who killed this person (null if alive)
        public AssassinNode next;  // next node in the list (null if none)

        /**
         * Constructs a new node to store the given name and no next node.
         */
        public AssassinNode(String name) {
            this(name, null);
        }

        /**
         * Constructs a new node to store the given name and a reference
         * to the given next node.
         */
        public AssassinNode(String name, AssassinNode next) {
            this.name = name;
            this.killer = null;
            this.next = next;
        }
    }
}
