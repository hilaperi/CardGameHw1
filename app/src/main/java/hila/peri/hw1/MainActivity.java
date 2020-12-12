package hila.peri.hw1;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<Activity> activities = new ArrayList<>();

    private final int NUMBER_OF_CARDS = 14;
    ImageView cardLeftImage, cardRightImage, buttonBattle, manLeftImage, ladyLeftImage;
    TextView scoreLeft, scoreRight, winner_TXT_man, winner_TXT_woman;
    Button buttonRestart;
    String gameCompetitionStatus = "competing";
    Typeface font;
    Deck warDeck;
    private int playerScoreA = 0, playerScoreB = 0;

   // Random r = new Random();

  //  int maxScore = 7;
    //int leftScore, rightScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();

    }
    private void findViews() {
        //Define of button and image
        cardLeftImage = findViewById(R.id.main_IMG_leftCard);
        cardRightImage = findViewById(R.id.main_IMG_rightCard);
        scoreLeft = findViewById(R.id.main_LBL_scoreLeft);
        scoreRight = findViewById(R.id.main_LBL_scoreRight);
        buttonBattle = findViewById(R.id.main_BTN_play);
        manLeftImage = findViewById(R.id.main_IMG_man);
        ladyLeftImage = findViewById(R.id.main_IMG_lady);

    }

    private void initViews() {
        //Define start game with "buttonBattle"
        initDeck();
        buttonBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTurn();
            }
        });

    }

    private void initDeck() {
        warDeck = new Deck();
        for (int i = 1; i <= NUMBER_OF_CARDS; i++) {
            warDeck.addCard("card_" + i, i);
        }
        warDeck.shuffleCards();
    }


    private void playTurn() {
        Cards playerCardA = warDeck.getCard();
        Cards playerCardB = warDeck.getCard();

        setNewCardsImage(playerCardA.getImageName(), playerCardB.getImageName());

        setScore(playerCardA, playerCardB);

        if (warDeck.isEmpty()) {
            displayWinner();
        }
    }

    private void setNewCardsImage(String imageNameA, String imageNameB) {
        int playerDrawableA = this.getResources().getIdentifier(imageNameA, "drawable", this.getPackageName());
        int playerDrawableB = this.getResources().getIdentifier(imageNameB, "drawable", this.getPackageName());

        cardLeftImage.setImageDrawable(getDrawable(playerDrawableA));
        cardRightImage.setImageDrawable(getDrawable(playerDrawableB));
    }

    private void setScore(Cards playerCardA, Cards playerCardB) {
        if (playerCardA.isStronger(playerCardB)) {
            playerScoreA++;
            scoreLeft.setText(playerScoreA + "");
        } else {
            playerScoreB++;
            scoreRight.setText(playerScoreB + "");
        }
    }

    private void displayWinner() {
        Intent intent = new Intent(this, winPage.class);
        intent.putExtra(winPage.playerScoreA, "" + playerScoreA);
        intent.putExtra(winPage.playerScoreB, "" + playerScoreB);
        startActivity(intent);
        finish();
    }



}

