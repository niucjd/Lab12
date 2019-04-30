package edu.illinois.cs.cs125.spring2019.lab12;

import java.util.Random;
/**
 * game class.
 */
public class guessDice {
    /**odds of small.*/
    private final int smallOdds = 2;
    /**odds of large.*/
    private final int largeOdds = 2;
    /**odds of triple.*/
    private final int tripleOdds = 7;
    /**default money.*/
    private final int defaultMoney = 50;
    /**money win.*/
    private final int winMoney = 1000;
    /**money loose.*/
    private final int looseMoney = 0;
    /**dice number.*/
    private final int diceNumber = 3;
    /**small dice number.*/
    private final int smallDiceNumber = 10;
    /**total points.*/
    private int totalPoints = 0;
    /**money.*/
    private int money;
    /**betAmount.*/
    private int betAmount;
    /**betchoice.*/
    private String betChoice;
    /**random int generator.*/
    private Random random = new Random();
    /**dice value.*/
    private int[] dices;
    /**default constructor.*/
    guessDice() {
        money = defaultMoney;
        dices = new int[diceNumber];
    }
    /**
     * roll dice.
     * @return dices
     */
    public int[] roll() {
        for (int i = 0; i < dices.length; i++) {
            dices[i] = random.nextInt(5) + 1;
        }
        return dices;
    }
    /**
     * bet.
     * @param s betstring
     */
    public void bet(final String s) {
        betChoice = s;
    }
    /**
     * bet.
     * @param amount betAmount
     */
    public void setAmount(final int amount) {
        betAmount = amount;
    }
    /**
     * getMoney.
     * @return money
     */
    public int getMoney() {
        return money;
    }

    /**
     * getMoney.
     * @return money
     */
    public int getTotalPointsnts() {
        return totalPoints;
    }

    /**
     * getDices.
     * @return dices
     */
    public int[] getDices() {
        return dices;
    }

    /**
     * win or not.
     * @return result
     */
    public String result() {
        int result = 0;
        for (int i = 0; i < dices.length; i++) {
            result += dices[i];
            System.out.println(dices[i]);
        }
        totalPoints = result + 3;
        if (betChoice == "tripleSeven") {
            if (dices[0] == dices[1] && dices[1] == dices[2]) {
                money += betAmount * tripleOdds;
            } else {
                money -= betAmount * smallOdds;
            }
        } else if (result <= smallDiceNumber) {
            if (betChoice == "small") {
                money += betAmount * smallOdds;
            } else {
                money -= betAmount * smallOdds;
            }
        } else {
            if (betChoice == "large") {
                money += betAmount * largeOdds;
            } else {
                money -= betAmount * largeOdds;
            }
        }
        if (money <= looseMoney) {
            return "loose";
        } else if (money >= winMoney) {
            return "win";
        } else {
            return "continue";
        }
    }
}
