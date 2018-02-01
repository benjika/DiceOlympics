package com.example.beni.diceolympics;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PigdiceGame extends AppCompatActivity {

    int playerTurn = (int) (Math.random() * 2 + 1);
    int diceNum;

    SensorManager shakeManager;
    boolean canShake;
    float accelVal;
    float accelLast;
    float shake;

    MediaPlayer diceShakeSound;

    TextView currentScorePlayer1;
    TextView currentScorePlayer2;

    TextView totalScorePlayer1;
    TextView totalScorePlayer2;

    ImageView arrow;
    Animation changeArrowTo1;
    Animation changeArrowTo2;

    Button SaveScoreBTN;

    ImageView dice;

    String[] NamesArr;
    int[] Scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pigdice_game);

        NamesArr = getIntent().getStringArrayExtra("NameArr");
        Scores = getIntent().getIntArrayExtra("ScoresArr");

        shakeManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        canShake = true;
        startShaker(shakeManager);

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

                    if (sum >= 100) {
                        weHaveWinner(0);
                    }

                    endTurn(currentScorePlayer1);
                } else {

                    sum = Integer.parseInt(currentScorePlayer2.getText().toString());
                    sum += Integer.parseInt(totalScorePlayer2.getText().toString());
                    totalScorePlayer2.setText(String.valueOf(sum));

                    if (sum >= 100) {
                        weHaveWinner(1);
                    }

                    endTurn(currentScorePlayer2);
                }

            }
        });


        RollDiceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UniversalShake();

            }
        });
    }

    private final SensorEventListener sensorListener = new SensorEventListener()
            // listener of the physical shake
    {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            accelLast = accelVal;
            accelVal = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = accelVal - accelLast;
            shake = shake * 0.9f + delta;

            if (shake > 12 && canShake) {
                UniversalShake();
                canShake = false;
                countDown();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    void countDown()
    // countdowns a second from one physical shake to another
    // to avoid many shakes at once
    {
        new CountDownTimer(1000, 500) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                canShake = true;
            }
        }.start();
    }

    void UniversalShake()
    // A universal function that physical shake and button shake use
    {

        diceShakeSound.start();

        diceNum = rollDice();

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

    void turnArrow() {

        if (playerTurn == 1)
            arrow.startAnimation(changeArrowTo1);

        else {
            arrow.startAnimation(changeArrowTo2);
        }
    }

    void endTurn(TextView currentScore) {

        currentScore.setText("0");

        playerTurn = 1 + ((playerTurn) % 2);
        turnArrow();
    }

    int rollDice()
    //return a new value from the dice
    {

        dice.setVisibility(View.VISIBLE);
        diceNum = (int) (Math.random() * 6 + 1);

        switch (diceNum) {
            case 1:
                dice.setImageResource(R.drawable.diceone);
                SaveScoreBTN.setVisibility(View.GONE);
                return 1;

            case 2:
                dice.setImageResource(R.drawable.dicetwo);
                SaveScoreBTN.setVisibility(View.VISIBLE);
                return 2;

            case 3:
                dice.setImageResource(R.drawable.dicethree);
                SaveScoreBTN.setVisibility(View.VISIBLE);
                return 3;

            case 4:
                dice.setImageResource(R.drawable.dicefour);
                SaveScoreBTN.setVisibility(View.VISIBLE);
                return 4;

            case 5:
                dice.setImageResource(R.drawable.dicefive);
                SaveScoreBTN.setVisibility(View.VISIBLE);
                return 5;

            case 6:
                dice.setImageResource(R.drawable.dicesix);
                SaveScoreBTN.setVisibility(View.VISIBLE);
                return 6;

        }

        return 0;
    }

    void startShaker(SensorManager shakeManager)
    // Initializes the physical shake
    {
        shakeManager.registerListener(sensorListener, shakeManager.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        accelVal = SensorManager.GRAVITY_EARTH;
        accelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;

    }

    void weHaveWinner(int nameIndex)
    //ends game
    {

        Scores[nameIndex]++; //adds a point to the winner

        shakeManager.unregisterListener(sensorListener); //stops physical shake

        final Intent intent = new Intent(this, WinnerScreen.class);
        intent.putExtra("NameArr", NamesArr);
        intent.putExtra("ScoresArr", Scores);
        intent.putExtra("WinnerName", NamesArr[nameIndex]);
        intent.putExtra("GameToReplay", "Pigdice");
        startActivity(intent);
        finish();
    }

}
