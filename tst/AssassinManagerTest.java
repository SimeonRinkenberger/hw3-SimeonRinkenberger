import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * test cases for the methods in AssassinManager.java
 */

//Test constructor
public class AssassinManagerTest {

    /**
     * Test case 1 provided as an example
     * Test graveyardContains should not have the person who wasn't killed
     */
    @Test
    public void graveyardContainsNegativeTest(){
        List<String> list1= new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        Assert.assertFalse(manager.graveyardContains("ocean"));
    }

    /**
     * Test case 2 provided as an example
     * Test constructor with invalid argument
     * Should throw IllegalArgumentException
     */
    @Test
    public void constructorNegativeTest(){
        try{
            List<String> emptyList = new ArrayList<String>();
            AssassinManager manager = new AssassinManager(emptyList);
            Assert.fail("AssassinManager should throw IllegalArgumentException for empty list");
        }catch (IllegalArgumentException e) {
        }
    }

    /**
     * Constructor test
     * tests that an IllegalArgumentException is thrown if it is passed a null list
     */
    @Test
    public void constructorNegativeTest2(){
        try{
            List<String> emptyList = null;
            AssassinManager manager = new AssassinManager(emptyList);
            Assert.fail("AssassinManager should throw IllegalArgumentException for empty list");
        }catch (IllegalArgumentException e) {
        }
    }

    /**
     * test for killRingContains()
     * tests that the method returns true for a name in the list
     */
    @Test
    public void killRingContainsTest1() {
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertTrue(manager.killRingContains("Ocean"));
    }

    /**
     * test for killRingContains()
     * tests that the method returns false if the name is not found in the kill ring
     */
    @Test
    public void killRingContainsTest2() {
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.printGraveyard();
        Assert.assertFalse(manager.killRingContains("Bill"));
    }

    /**
     * test for graveyardContains()
     * tests that the method returns true for a name in the list
     */
    @Test
    public void graveyardContainsTest1() {
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Ocean");
        Assert.assertTrue(manager.graveyardContains("Ocean"));
    }

    /**
     * test for graveyardContains()
     * tests that the method returns false for a name not in the list
     */
    @Test
    public void graveyardContainsTest2() {
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Ocean");
        Assert.assertFalse(manager.graveyardContains("Chris"));
    }

    /**
     * test for isGameOver()
     * tests that the method returns false when the game is not over
     */
    @Test
    public void isGameOverTest1() {
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertFalse(manager.isGameOver());
    }

    /**
     * test for isGameOver()
     * tests that the method returns true when the game is over
     */
    @Test
    public void isGameOverTest2() {
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");

        AssassinManager manager = new AssassinManager(list1);
        manager.printKillRing();
        Assert.assertTrue(manager.isGameOver());
    }

    /**
     * test for winner()
     * tests that the method returns null when the winner has not been decided yet
     */
    @Test
    public void winnerTest1() {
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");
        list1.add("Ocean");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertNull(manager.winner());
    }

    /**
     * test for winner()
     * tests that the method returns null when the winner has not been decided yet
     */
    @Test
    public void winnerTest2() {
        List<String> list1= new ArrayList<>();
        list1.add("Grayson");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertEquals("Grayson", manager.winner());
    }

    /**
     * test for kill()
     * tests that the method returns an IllegalStateException when the game is already over
     */
    @Test
    public void killTest1() {
        try {
            List<String> list1= new ArrayList<>();
            list1.add("Grayson");

            AssassinManager manager = new AssassinManager(list1);
            manager.kill("Grayson");
            Assert.fail("should have thrown an IllegalStateException");
        } catch (IllegalStateException e) {

        }

    }

    /**
     * test for kill()
     * tests that the method returns an IllegalStateException when the game is already over
     */
    @Test
    public void killTest2() {
        try {
            List<String> list1= new ArrayList<>();
            list1.add("Ocean");
            list1.add("Chris");

            AssassinManager manager = new AssassinManager(list1);
            manager.kill("Grayson");
            Assert.fail("should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }
    }
}