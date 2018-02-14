package com.example.beni.diceolympics;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WinnerScreen extends AppCompatActivity {

    TextView winnerNameTextView;
    TextView playerName1;
    TextView playerName2;
    TextView playerScore1;
    TextView playerScore2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_screen);


        final String[] getNamesArr = getIntent().getStringArrayExtra("NameArr");
        final int[] getScores = getIntent().getIntArrayExtra("ScoresArr");
        final int winnerNameFromGame = Integer.parseInt(getIntent().getStringExtra("WinnerName"));
        final String GameToReplay = getIntent().getStringExtra("GameToReplay");
        setTexts(getNamesArr, getScores, winnerNameFromGame);

        final ImageButton btnMute = (ImageButton) findViewById(R.id.winner_btn_mute);
        if (Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);

        final MediaPlayer buttonClickSound = MediaPlayer.create(WinnerScreen.this, R.raw.buttonpress);
        final MediaPlayer victorySound = MediaPlayer.create(WinnerScreen.this, R.raw.victory);


        if (winnerNameFromGame < 2){
            if (!Sounds.getIsMute()) victorySound.start();}

        final RelativeLayout set1 = (RelativeLayout) findViewById(R.id.winner_set1);
        final RelativeLayout set2 = (RelativeLayout) findViewById(R.id.winner_set2);

        Handler delayer = new Handler();
        delayer.postDelayed(new Runnable() {
            @Override
            public void run() {
                set1.setVisibility(View.GONE);
                set2.setVisibility(View.VISIBLE);
            }
        }, 4000);

        //buttons set 1
        final Button btnPlayAgain = (Button) findViewById(R.id.winner_btn_PlayAgain);
        final Button btnNewGame = (Button) findViewById(R.id.winner_btn_NewGame);
        final Button btnExitApp = (Button) findViewById(R.id.winner_btn_ExitApp);
        //buttons set 2
        final Button btnSinglePlayer = (Button) findViewById(R.id.winner_btn_SinglePlayer);
        final Button btnTwoPlayers = (Button) findViewById(R.id.winner_btn_TwoPlayers);
        //buttons set 3
        final Button btnNewPlayers = (Button) findViewById(R.id.winner_btn_NewPlayers);
        final Button btnSamePlayers = (Button) findViewById(R.id.winner_btn_SamePlayers);
        //buttons set 4
        final Button btnNewScore = (Button) findViewById(R.id.winner_btn_NewScore);
        final Button btnSameScore = (Button) findViewById(R.id.winner_btn_SameScore);

        final LinearLayout btnsSet1 = (LinearLayout) findViewById(R.id.winner_btns_set1);
        final LinearLayout btnsSet2 = (LinearLayout) findViewById(R.id.winner_btns_set2);
        final LinearLayout btnsSet3 = (LinearLayout) findViewById(R.id.winner_btns_set3);
        final LinearLayout btnsSet4 = (LinearLayout) findViewById(R.id.winner_btns_set4);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();

                final Intent intent;
                if (GameToReplay.equals("Pigdice")) {
                    intent = new Intent(WinnerScreen.this, PigdiceGame.class);
                } else {
                    intent = new Intent(WinnerScreen.this, MidnightGame.class);
                }

                intent.putExtra("NameArr", getNamesArr);
                intent.putExtra("ScoresArr", getScores);
                startActivity(intent);
                finish();
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                btnsSet1.setVisibility(View.GONE);
                btnsSet2.setVisibility(View.VISIBLE);
            }
        });

        btnExitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                final AlertDialog.Builder builder = new AlertDialog.Builder(WinnerScreen.this);
                builder.setTitle("");
                builder.setMessage(R.string.areYouSureToExit);

                builder.setPositiveButton(R.string.yesWantToExit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!Sounds.getIsMute()) buttonClickSound.start();
                        finish();
                        System.exit(0);
                    }
                });

                builder.setNegativeButton(R.string.noWantToStay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!Sounds.getIsMute()) buttonClickSound.start();
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        btnSinglePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                /*final Intent intent = new Intent(WinnerScreen.this, NewGameClass.class);
                startActivity(intent);
                finish();*/
                Toast.makeText(WinnerScreen.this, "Will Work", Toast.LENGTH_SHORT).show();
            }
        });

        btnTwoPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                btnsSet2.setVisibility(View.GONE);
                btnsSet3.setVisibility(View.VISIBLE);
            }
        });

        btnNewPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                final Intent intent = new Intent(WinnerScreen.this, InputNames.class);
                startActivity(intent);
                finish();
            }
        });

        btnSamePlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                btnsSet3.setVisibility(View.GONE);
                btnsSet4.setVisibility(View.VISIBLE);
            }
        });

        btnNewScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Sounds.getIsMute()) buttonClickSound.start();

                getScores[0] = 0;
                getScores[1] = 0;

                final Intent intent = new Intent(WinnerScreen.this, ChooseGame.class);
                intent.putExtra("NameArr", getNamesArr);
                intent.putExtra("ScoresArr", getScores);
                startActivity(intent);
                finish();
            }
        });

        btnSameScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();

                final Intent intent = new Intent(WinnerScreen.this, ChooseGame.class);
                intent.putExtra("NameArr", getNamesArr);
                intent.putExtra("ScoresArr", getScores);
                startActivity(intent);
                finish();
            }
        });

        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.buttonMutePress(btnMute);
            }
        });

    }

    void setTexts(String[] getNamesArr, int[] getScores, int winnerNameFromGame) {
        winnerNameTextView = (TextView) findViewById(R.id.winner_winnerName);
        playerName1 = (TextView) findViewById(R.id.winner_namePlayer1);
        playerName2 = (TextView) findViewById(R.id.winner_namePlayer2);
        playerScore1 = (TextView) findViewById(R.id.winner_ScorePlayer1);
        playerScore2 = (TextView) findViewById(R.id.winner_ScorePlayer2);

        if (winnerNameFromGame < 2) winnerNameTextView.setText(getNamesArr[winnerNameFromGame]);
        playerName1.setText(getNamesArr[0]);
        playerName2.setText(getNamesArr[1]);
        playerScore1.setText(String.valueOf(getScores[0]));
        playerScore2.setText(String.valueOf(getScores[1]));
    }


}
