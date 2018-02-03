package com.example.beni.diceolympics;

import android.content.Intent;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PigdiceGame extends AppCompatActivity {

    static int playerTurn = (int) (Math.random() * 2 + 1);
    static int diceNum;

    SensorManager shakeManager;

    static MediaPlayer diceShakeSound;

    static TextView currentScorePlayer1;
    static TextView currentScorePlayer2;

    static TextView totalScorePlayer1;
    static TextView totalScorePlayer2;

    static ImageView arrow;
    static Animation changeArrowTo1;
    static Animation changeArrowTo2;

    static Button SaveScoreBTN;

    static ImageView dice;

    String[] NamesArr;
    int[] Scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pigdice_game);

        NamesArr = getIntent().getStringArrayExtra("NameArr");
        Scores = getIntent().getIntArrayExtra("ScoresArr");

        shakeManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Shaker.setNameOfGame("Pigdice");
        Shaker.startShaker(shakeManager);

        final TextView player1name = (TextView) findViewById(R.id.pigDice_PlayerName_Player1);
        final TextView player2name = (TextView) findViewById(R.id.pigDice_PlayerName_Player2);
        player1name.setText(NamesArr[0]);
        player2name.setText(NamesArr[1]);

        diceShakeSound = MediaPlayer.create(this, R.raw.shakerdice1);

        currentScorePlayer1 = (TextView) findViewById(R.id.pigDice_currentScore_Player1);
        currentScorePlayer2 = (TextView) findViewById(R.id.pigDice_currentScorePlayer2);

        totalScorePlayer1 = (TextView) findViewById(R.id.pigDice_totalScore_Player1);
        totalScorePlayer2 = (TextView) findViewById(R.id.pigDice_totalScore_Player2);

        dice = (ImageView) findViewById(R.id.pigDice_dice);
        arrow = (ImageView) findViewById(R.id.pigDice_turnArrow);
        SaveScoreBTN = (Button) findViewById(R.id.pigDice_btn_saveScore);

        Button RollDiceBTN = (Button) findViewById(R.id.pigDice_btn_diceRoll);
        SaveScoreBTN.setVisibility(View.GONE);

        changeArrowTo1 = AnimationUtils.loadAnimation(PigdiceGame.this, R.anim.arrorflipto1);
        changeArrowTo2 = AnimationUtils.loadAnimation(PigdiceGame.this, R.anim.arrowflipto2);

        turnArrow();


        SaveScoreBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaveScoreBTN.setVisibility(View.GONE);

                int sum;


                if (playerTurn == 1) {
                    sum = Integer.parseInt(currentScorePlayer1.getText().toString());
                    sum += Integer.parseInt(totalScorePlayer1.getText().toString());
                    totalScorePlayer1.setText(String.valueOf(sum));

                    if (sum >= 100) weHaveWinner(0);
                    else endTurn(currentScorePlayer1);

                } else {

                    sum = Integer.parseInt(currentScorePlayer2.getText().toString());
                    sum += Integer.parseInt(totalScorePlayer2.getText().toString());
                    totalScorePlayer2.setText(String.valueOf(sum));

                    if (sum >= 100) weHaveWinner(1);

                    else endTurn(currentScorePlayer2);
                }

            }
        });


        RollDiceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UniversalShake();
            }
        });
    }


    static void UniversalShake()
    // A universal function that physical shake and button shake use
    {
        if (!Sounds.getIsMute()) diceShakeSound.start();

        diceNum = GameFunctions.rollDice("Pigdice");

        if (diceNum == 1) {
            if (playerTurn == 1) {
                endTurn(currentScorePlayer1);
            } else {
                endTurn(currentScorePlayer2);
            }
        } else {
            if (playerTurn == 1)
                currentScorePlayer1.setText(String.valueOf(Integer.parseInt
                        (currentScorePlayer1.getText().toString()) + diceNum));

            else
                currentScorePlayer2.setText(String.valueOf(Integer.parseInt
                        (currentScorePlayer2.getText().toString()) + diceNum));
        }
    }

    static void turnArrow() {

        if (playerTurn == 1)
            arrow.startAnimation(changeArrowTo1);

        else {
            arrow.startAnimation(changeArrowTo2);
        }
    }

    static void endTurn(TextView currentScore) {

        currentScore.setText("0");

        playerTurn = 1 + ((playerTurn) % 2);
        turnArrow();
    }

    void weHaveWinner(int nameIndex)
    //ends game
    {

        Scores[nameIndex]++; //adds a point to the winner

        shakeManager.unregisterListener(Shaker.sensorListener); //stops physical shake

        final Intent intent = new Intent(this, WinnerScreen.class);
        intent.putExtra("NameArr", NamesArr);
        intent.putExtra("ScoresArr", Scores);
        intent.putExtra("WinnerName", NamesArr[nameIndex]);
        intent.putExtra("GameToReplay", "Pigdice");
        startActivity(intent);
        finish();
    }

}
