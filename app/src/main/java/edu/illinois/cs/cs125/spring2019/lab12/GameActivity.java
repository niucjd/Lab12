package edu.illinois.cs.cs125.spring2019.lab12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * game class for our UI design lab.
 */
public class GameActivity extends Activity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Lab12:Main";
    /** smallbutton.*/
    private static Button smallButton;
    /**largeButton.*/
    private static Button largeButton;
    /**tripleSeven.*/
    private static Button tripleSevenButton;
    /**tripleSeven.*/
    private static Button endButton;
    /**game.*/
    private static guessDice game;
    /**money textview.*/
    private TextView moneyText;
    /**total dices point.*/
    private TextView pointsText;
    /**guess string.*/
    private TextView guessText;
    /**money string.*/
    private TextView betMoneyText;
    /**betmoney.*/
    private final int robetnumber = 10;
    /**dice 1 image.*/
    private ImageView dice1View;
    /**dice 2 image.*/
    private ImageView dice2View;
    /**dice 3 image.*/
    private ImageView dice3View;
    /**dice1Image.*/
    private int dice1Image = R.drawable.dice1;
    /**dice2Image.*/
    private int dice2Image = R.drawable.dice2;
    /**dice3Image.*/
    private int dice3Image = R.drawable.dice3;
    /**dice4Image.*/
    private int dice4Image = R.drawable.dice4;
    /**dice5Image.*/
    private int dice5Image = R.drawable.dice5;
    /**dice6Image.*/
    private int dice6Image = R.drawable.dice6;
    /**array of image.*/
    private int[]diceArray;
    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game = new guessDice();
        moneyText = findViewById(R.id.money);
        pointsText = findViewById(R.id.total);
        guessText = findViewById(R.id.guess);
        smallButton = findViewById(R.id.smallButton);
        largeButton = findViewById(R.id.largeButton);
        tripleSevenButton = findViewById(R.id.tripleSeven);
        endButton = findViewById(R.id.endButton);
        betMoneyText = findViewById(R.id.editText2);
        dice1View = findViewById(R.id.firstDice);
        dice2View = findViewById(R.id.secondDice);
        dice3View = findViewById(R.id.thirdDice);
        diceArray = new int[6];
        diceArray[0] = dice1Image;
        diceArray[1] = dice2Image;
        diceArray[2] = dice3Image;
        diceArray[3] = dice4Image;
        diceArray[4] = dice5Image;
        diceArray[5] = dice6Image;
        dice1View.setImageResource(diceArray[0]);
        dice2View.setImageResource(diceArray[0]);
        dice3View.setImageResource(diceArray[0]);
        smallButton.setOnClickListener(v -> {
            game.bet("small");
            guessText.setText("small");
            check();
        });
        largeButton.setOnClickListener(v -> {
            game.bet("large");
            guessText.setText("large");
            check();
        });
        tripleSevenButton.setOnClickListener(v -> {
            game.bet("tripleSeven");
            guessText.setText("777");
            check();
        });
        endButton.setOnClickListener(v -> {
            finish();
        });
    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }
    /**
     * stop when game end.
     */
    protected void check() {
        int betMoneyamount = Integer.parseInt(betMoneyText.getText().toString());
        game.setAmount(betMoneyamount);
        game.roll();
        String result = game.result();
        moneyText.setText(Integer.toString(game.getMoney()));
        pointsText.setText(Integer.toString(game.getTotalPointsnts()));
        int[] dices = game.getDices();
        dice1View.setImageResource(diceArray[dices[0]]);
        dice2View.setImageResource(diceArray[dices[1]]);
        dice3View.setImageResource(diceArray[dices[2]]);
        if (result == "win") {
            Intent intent = new Intent(GameActivity.this, ResultActivity.class);
            intent.putExtra("result", "win");
            finish();
            startActivity(intent);

        } else if (result == "loose") {
            Intent intent = new Intent(GameActivity.this, ResultActivity.class);
            intent.putExtra("result", "loose");
            finish();
            startActivity(intent);
        }
    }
}
