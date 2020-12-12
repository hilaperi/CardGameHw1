package hila.peri.hw1;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<Activity> activities = new ArrayList<>();

    private final int NUMBER_OF_CARDS = 14;
    ImageView cardLeftImage, cardRightImage, buttonBattle, manLeftImage, ladyLeftImage;
    TextView scoreLeft, scoreRight;
    Deck Decks;
    private int playerLeftScoreA = 0, playerRightScoreB = 0;

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
        randCards();
        buttonBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTurn();
            }
        });

    }

    private void randCards() {
        Decks = new Deck();
        for (int i = 1; i <= NUMBER_OF_CARDS; i++) {
            Decks.addCard("card_" + i, i);
        }
        Decks.shuffleCards();
    }


    private void playTurn() {
        Cards playerLeftCard = Decks.getCard();
        Cards playerRightCard = Decks.getCard();

        setNewCardsImage(playerLeftCard.getImageName(), playerRightCard.getImageName());

        setScore(playerLeftCard, playerRightCard);

        if (Decks.isEmpty()) {
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
            playerLeftScoreA++;
            scoreLeft.setText(playerLeftScoreA + "");
        } else {
            playerRightScoreB++;
            scoreRight.setText(playerRightScoreB + "");
        }
    }

    private void displayWinner() {
        Intent intent = new Intent(this, winPage.class);
        intent.putExtra(winPage.playerLeftScore, "" + playerLeftScoreA);
        intent.putExtra(winPage.playerRightScore, "" + playerRightScoreB);
        startActivity(intent);
        finish();
    }



}

