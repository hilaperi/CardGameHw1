package hila.peri.hw1;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends  Activity {
    private static ArrayList<Activity> activities = new ArrayList<>();

    ImageView cardLeftImage, cardRightImage,buttonBattle,manLeftImage,ladyLeftImage;
    TextView scoreLeft, scoreRight;
    Button buttonRestart;
    String gameCompetitionStatus = "competing";
    Typeface font;

    Random r = new Random();

    int maxScore = 7;
    int leftScore, rightScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
        setContentView(R.layout.activity_main);

        //Define of button and image
        cardLeftImage = findViewById(R.id.main_IMG_leftCard);
        cardRightImage = findViewById(R.id.main_IMG_rightCard);
        scoreLeft = findViewById(R.id.main_LBL_scoreLeft);
        scoreRight = findViewById(R.id.main_LBL_scoreRight);
        buttonBattle = findViewById(R.id.main_BTN_play);
        buttonRestart = findViewById(R.id.main_BTN_restart);
        manLeftImage = findViewById(R.id.main_IMG_man);
        ladyLeftImage = findViewById(R.id.main_IMG_lady);


       //Define start game with "buttonBattle"
        buttonBattle.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // generate the two card numbers
                int leftCard = r.nextInt(13) + 2; // this is for cards 2 - 14
                int rightCard = r.nextInt(13) + 2; // this is for cards 2 - 14

                int leftImage = getDrawableCard(leftCard);
                cardLeftImage.setImageResource(leftImage);

                int rightImage = getDrawableCard(rightCard);
                cardRightImage.setImageResource(rightImage);


                // compare cards, add points and update score
                if (leftCard > rightCard) {
                    leftScore++;
                    setScoreLeft(String.valueOf(leftScore));

                } else if (leftCard < rightCard) {
                    rightScore++;
                    setScoreRight(String.valueOf(rightScore));
                }

                // Check competition

                if (leftScore == maxScore && rightScore == maxScore) {
                    gameCompetitionStatus = "draw"; }
                else if (leftScore >= maxScore) {
                    gameCompetitionStatus = "left_wins";}
                else if (rightScore >= maxScore) {
                    gameCompetitionStatus = "right_wins"; }


                if (gameCompetitionStatus.equals("draw")) {
                    hideThingsMakeRestartButtonVisible();
                    setScoreLeft("Draw!!");

                } else if (gameCompetitionStatus.equals("left_wins")) {
                    hideThingsMakeRestartButtonVisible();
                    scoreLeft.setText(R.string.congratsWoman);

                } else if (gameCompetitionStatus.equals("right_wins")) {
                    hideThingsMakeRestartButtonVisible();
                    scoreLeft.setText(R.string.congratsMan);

                }

            }
            //The screen after the win
            private void hideThingsMakeRestartButtonVisible() {
                scoreRight.setVisibility(View.GONE);
                cardLeftImage.setVisibility(View.GONE);
                cardRightImage.setVisibility(View.GONE);
                buttonBattle.setVisibility(View.GONE);
                ladyLeftImage.setVisibility(View.GONE);
                manLeftImage.setVisibility(View.GONE);
                buttonRestart.setVisibility(View.VISIBLE);
            }


        });

        // Listen Restart button which will restart the game

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });


    }

    private int getDrawableCard(int card) {
        return getResources().getIdentifier("card" + card, "drawable", getPackageName());
    }

    private void setScoreRight(String s) {
        scoreRight.setText(String.valueOf(s));
    }

    private void setScoreLeft(String s) {
        scoreLeft.setText(s);
    }
    
    private void restartGame() {

        for (Activity activity : activities)
            activity.recreate();


    }
}
