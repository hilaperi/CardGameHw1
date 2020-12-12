package hila.peri.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.content.Intent;

import android.app.Activity;

import java.util.ArrayList;

public class winPage extends AppCompatActivity {
    public static final String playerScoreA = "PLAYER_A_SCORE";
    public static final String playerScoreB = "PLAYER_B_SCORE";
    //private static ArrayList<Activity> activities = new ArrayList<>();
    TextView winner_LBL_game_over, winner_TXT_name;
    Button main_BTN_restart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winpage);
        findViews();
        initViews();
        displayWinner();
    }

    private void findViews() {
        winner_LBL_game_over = findViewById(R.id.winner_LBL_game_over);
        winner_TXT_name = findViewById(R.id.winner_TXT_name);
        main_BTN_restart = findViewById(R.id.main_BTN_restart);
    }
    private void initViews() {
        main_BTN_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });

    }

    private void displayWinner() {
        Intent intent = getIntent();
        String scoreA = intent.getStringExtra(playerScoreA);
        String scoreB = intent.getStringExtra(playerScoreB);
        String playerName;

        if (Integer.parseInt(scoreA) > Integer.parseInt(scoreB)) {
            playerName = "The woman";
        } else {
            playerName = "the man";
        }

        winner_TXT_name.setText("Congratulations " + playerName + " win!");

    }

    private void restartGame() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}