package com.example.beni.diceolympics;

import android.content.Intent;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class WhosStarting extends AppCompatActivity {

    String[] NamesArr;
    int[] Scores;
    String GameToPlay;
    static MediaPlayer diceSound;
    static int playerTurn;
    static Button RollDice;
    static Button StartTheGame;

    SensorManager shakeManager; //Activates dice shaker

    static int player1Score;
    static int player2Score;

    static ImageView arrow;
    static MediaPlayer arrowTurn;
    static Animation changeArrowTo1;
    static Animation changeArrowTo2;

    MediaPlayer buttonClickSound;

    static ImageView dice1;
    static ImageView dice2;

    static boolean was1;
    static boolean was2;

    static int playerToBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whos_starting);

        final TextView playername1 = (TextView) findViewById(R.id.whosStarting_PlayerName_Player1);
        final TextView playername2 = (TextView) findViewById(R.id.whosStarting_PlayerName_Player2);
        dice1 = (ImageView) findViewById(R.id.whosStarting_dice_Player1);
        dice2 = (ImageView) findViewById(R.id.whosStarting_dice_Player2);
        RollDice = (Button) findViewById(R.id.whosStarting_btn_diceRoll);
        StartTheGame = (Button) findViewById(R.id.whosStarting_btn_StarTheGame);
        final ImageButton btnMute = (ImageButton) findViewById(R.id.whosStarting_game_btn_mute);
        diceSound = MediaPlayer.create(WhosStarting.this, R.raw.shakerdice1);
        arrowTurn = MediaPlayer.create(WhosStarting.this, R.raw.arrowturn);
        arrow = (ImageView) findViewById(R.id.whosStarting_turnArrow);
        changeArrowTo1 = AnimationUtils.loadAnimation(WhosStarting.this, R.anim.arrorflipto1);
        changeArrowTo2 = AnimationUtils.loadAnimation(WhosStarting.this, R.anim.arrowflipto2);
        buttonClickSound = MediaPlayer.create(WhosStarting.this, R.raw.buttonpress);
        playerTurn = 1;

        arrow.startAnimation(changeArrowTo1);

        //Activate Shaker
        shakeManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Shaker.setNameOfGame("Who's Starting");
        Shaker.startShaker(shakeManager);

        if (Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);

        NamesArr = getIntent().getStringArrayExtra("NameArr");
        Scores = getIntent().getIntArrayExtra("ScoresArr");
        GameToPlay = getIntent().getStringExtra("GameToPlay");

        playername1.setText(NamesArr[0]);
        playername2.setText(NamesArr[1]);

        RollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UniversalShake();
            }
        });

        StartTheGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Sounds.getIsMute()) buttonClickSound.start();

                shakeManager.unregisterListener(Shaker.sensorListener); //stops physical shake

                Intent intent;
                if (GameToPlay.equals("Pigdice"))
                    intent = new Intent(WhosStarting.this, PigdiceGame.class);
                else
                    intent = new Intent(WhosStarting.this, MidnightGame.class);
                intent.putExtra("NameArr", NamesArr);
                intent.putExtra("ScoresArr", Scores);
                intent.putExtra("playerToBegin", playerToBegin);
                startActivity(intent);
                finish();
            }
        });


    }

    static void UniversalShake() {

        if (!Sounds.getIsMute()) diceSound.start();

        if (playerTurn == 1) {
            player1Score = GameFunctions.rollDice("Who's Starting", dice1);
            playerTurn++;
            arrow.startAnimation(changeArrowTo2);
            arrowTurn.start();

        } else {

            RollDice.setVisibility(View.INVISIBLE);
            player2Score = GameFunctions.rollDice("Who's Starting", dice2);
            if (player1Score > player2Score) {
                playerToBegin = 1;
                Handler delayer = new Handler();
                delayer.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        StartTheGame.setVisibility(View.VISIBLE);
                        return;
                    }
                }, 700);
            } else if (player1Score < player2Score) {
                playerToBegin = 2;
                Handler delayer = new Handler();
                delayer.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        StartTheGame.setVisibility(View.VISIBLE);
                        return;
                    }
                }, 700);
            } else {
                playerTurn = 1;
                arrow.startAnimation(changeArrowTo1);
                arrowTurn.start();
                dice1.setImageResource(R.drawable.diceempty);
                dice2.setImageResource(R.drawable.diceempty);
                Handler delayer = new Handler();
                delayer.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        RollDice.setVisibility(View.VISIBLE);
                        return;
                    }
                }, 700);
            }


        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            if (!Sounds.getIsMute()) buttonClickSound.start();

            shakeManager.unregisterListener(Shaker.sensorListener); //stops physical shake
            final Intent intent;
            if (GameToPlay.equals("Pigdice"))
                intent = new Intent(WhosStarting.this, PigdiceEntrance.class);
            else
                intent = new Intent(WhosStarting.this, MidnightEntrance.class);
            intent.putExtra("NameArr", NamesArr);
            intent.putExtra("ScoresArr", Scores);
            startActivity(intent);
            finish();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
