package edu.illinois.cs.cs125.spring2019.lab12;
//import org.junit.Test;
/**
 * testcase.
 */
public class guessDiceTest {
    /** Timeout for all tests. These should be quite quick. */
    private static final int TEST_TIMEOUT = 100;
    /**
     * Test simple width getters and setters.
     */
    //@Test(timeout = TEST_TIMEOUT)
    public void testGame() {
        guessDice game = new guessDice();
        game.bet("small");
        game.roll();
        game.result();
        System.out.println(game.getMoney());
    }
}
