package com.example.beni.diceolympics;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MidnightGame extends AppCompatActivity {

    String[] getNamesArr;
    int[] getScores;

    SensorManager shakeManager; //Activates dice shaker

    int playerTurn;


    static LinearLayout setOfDiceBoard0;
    static LinearLayout setOfDiceBoard1;
    static LinearLayout setOfDiceBank0;
    static LinearLayout setOfDiceBank1;


    static MediaPlayer diceShake1;
    static MediaPlayer diceShake2;
    static MediaPlayer diceShake3;
    static MediaPlayer arrowSound;

    Button RollTheDiceBTN;
    Button EndTurnBTN;
    Button EndgameBTN;

    TextView PlayerName1;
    TextView PlayerName2;
    TextView PlayerScore1;
    TextView PlayerScore2;

    ImageView arrow;
    Animation changeArrowTo1;
    Animation changeArrowTo2;

    static boolean isFirstShake;

    boolean player1played = false;
    boolean player2played = false;

    boolean was4 = false;
    boolean was1 = false;

    static int[] diceValues = {0, 0, 0, 0, 0, 0};

    ImageButton firstDice_Bank;
    ImageButton secondDice_Bank;
    ImageButton thirdDice_Bank;
    ImageButton fourthDice_Bank;
    ImageButton fifthDice_Bank;
    ImageButton sixthDice_Bank;

    static boolean[] dicePlayableBank = new boolean[6];

    static ImageButton[] diceBank = new ImageButton[6];

    ImageButton firstDice_Board;
    ImageButton secondDice_Board;
    ImageButton thirdDice_Board;
    ImageButton fourthDice_Board;
    ImageButton fifthDice_Board;
    ImageButton sixthDice_Board;

    static boolean[] dicePlayableBoard = new boolean[6];

    static ImageButton[] diceBoard = new ImageButton[6];

    static int diceAmount = dicePlayableBoard.length;

    static int diceCountCurr = 6;
    static int diceCountTot = 6;

    ImageButton btnMute;

    static Activity thisActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midnight_game);

        thisActivity = this;

        final MediaPlayer slideDice = MediaPlayer.create(this, R.raw.slide);

        getInfoFromExtras();
        InitializeDiceAndButtons();

        firstDice_Board.setClickable(false);
        secondDice_Board.setClickable(false);
        thirdDice_Board.setClickable(false);
        fourthDice_Board.setClickable(false);
        fifthDice_Board.setClickable(false);
        sixthDice_Board.setClickable(false);


        firstDice_Bank.setClickable(false);
        secondDice_Bank.setClickable(false);
        thirdDice_Bank.setClickable(false);
        fourthDice_Bank.setClickable(false);
        fifthDice_Bank.setClickable(false);
        sixthDice_Bank.setClickable(false);

        firstDice_Board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Sounds.getIsMute()) slideDice.start();
                setDice_InAndOut_OfBank(diceBoard[0], diceBank[0], diceValues[0], 0);
                diceCountCurr--;
                if (diceCountCurr == 0) noMoreDice();
            }
        });

        secondDice_Board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Sounds.getIsMute()) slideDice.start();
                setDice_InAndOut_OfBank(diceBoard[1], diceBank[1], diceValues[1], 1);
                diceCountCurr--;
                if (diceCountCurr == 0) noMoreDice();
            }
        });

        thirdDice_Board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Sounds.getIsMute()) slideDice.start();
                setDice_InAndOut_OfBank(diceBoard[2], diceBank[2], diceValues[2], 2);
                diceCountCurr--;
                if (diceCountCurr == 0) noMoreDice();
            }
        });

        fourthDice_Board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Sounds.getIsMute()) slideDice.start();
                setDice_InAndOut_OfBank(diceBoard[3], diceBank[3], diceValues[3], 3);
                diceCountCurr--;
                if (diceCountCurr == 0) noMoreDice();
            }
        });

        fifthDice_Board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Sounds.getIsMute()) slideDice.start();
                setDice_InAndOut_OfBank(diceBoard[4], diceBank[4], diceValues[4], 4);
                diceCountCurr--;
                if (diceCountCurr == 0) noMoreDice();
            }
        });

        sixthDice_Board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Sounds.getIsMute()) slideDice.start();
                setDice_InAndOut_OfBank(diceBoard[5], diceBank[5], diceValues[5], 5);
                diceCountCurr--;
                if (diceCountCurr == 0) noMoreDice();
            }
        });

        firstDice_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dicePlayableBank[0])
                    return;
                if (!Sounds.getIsMute()) slideDice.start();
                setDice_InAndOut_OfBank(diceBank[0], diceBoard[0], diceValues[0], 0);
                diceCountCurr++;
                checkButtons();
            }
        });

        secondDice_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dicePlayableBank[1])
                    return;
                if (!Sounds.getIsMute()) slideDice.start();
                setDice_InAndOut_OfBank(diceBank[1], diceBoard[1], diceValues[1], 1);
                diceCountCurr++;
                checkButtons();
            }
        });

        thirdDice_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dicePlayableBank[2])
                    return;
                if (!Sounds.getIsMute()) slideDice.start();
                setDice_InAndOut_OfBank(diceBank[2], diceBoard[2], diceValues[2], 2);
                diceCountCurr++;
                checkButtons();
            }
        });

        fourthDice_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dicePlayableBank[3])
                    return;
                if (!Sounds.getIsMute()) slideDice.start();
                setDice_InAndOut_OfBank(diceBank[3], diceBoard[3], diceValues[3], 3);
                diceCountCurr++;
                checkButtons();
            }
        });

        fifthDice_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dicePlayableBank[4])
                    return;
                if (!Sounds.getIsMute()) slideDice.start();
                setDice_InAndOut_OfBank(diceBank[4], diceBoard[4], diceValues[4], 4);
                diceCountCurr++;
                checkButtons();
            }
        });

        sixthDice_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dicePlayableBank[5])
                    return;
                if (!Sounds.getIsMute()) slideDice.start();
                setDice_InAndOut_OfBank(diceBank[5], diceBoard[5], diceValues[5], 5);
                diceCountCurr++;
                checkButtons();
            }
        });

        RollTheDiceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UniversalShake();
            }
        });

        EndTurnBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (playerTurn == 1) {
                    PlayerScore1.setText(String.valueOf(countDice()));
                    playerTurn = 2;
                    player1played = true;
                    arrow.startAnimation(changeArrowTo2);
                } else {
                    PlayerScore2.setText(String.valueOf(countDice()));
                    playerTurn = 1;
                    player2played = true;
                    arrow.startAnimation(changeArrowTo1);
                }

                if (!Sounds.getIsMute()) arrowSound.start();

                refreshDice();
                EndTurnBTN.setVisibility(View.INVISIBLE);
                RollTheDiceBTN.setVisibility(View.VISIBLE);
                isFirstShake = true;
            }
        });

        EndgameBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (playerTurn == 1) {
                    PlayerScore1.setText(String.valueOf(countDice()));
                } else {
                    PlayerScore2.setText(String.valueOf(countDice()));
                }

                final int player1ScoreINT = Integer.parseInt(PlayerScore1.getText().toString());
                final int player2ScoreINT = Integer.parseInt(PlayerScore2.getText().toString());


                Runnable r = new Runnable() {
                    @Override
                    public void run() {

                        final Intent intent =
                                new Intent(MidnightGame.this, WinnerScreen.class);
                        intent.putExtra("NameArr", getNamesArr);
                        intent.putExtra("GameToReplay", "Midnight");

                        if (player1ScoreINT == player2ScoreINT) {
                            intent.putExtra("WinnerName", " ");
                        } else if (player1ScoreINT > player2ScoreINT) {
                            getScores[0]++;
                            intent.putExtra("WinnerName", getNamesArr[0]);

                        } else {
                            getScores[1]++;
                            intent.putExtra("WinnerName", getNamesArr[1]);
                        }

                        intent.putExtra("ScoresArr", getScores);
                        startActivity(intent);
                        shakeManager.unregisterListener(Shaker.sensorListener); //stops physical shake
                        isFirstShake = true;
                        player1played = false;
                        player2played = false;
                        PlayerScore1.setText("0");
                        PlayerScore2.setText("0");
                        finish();
                    }
                };

                Handler handler = new Handler();

                handler.postDelayed(r, 1000);
            }
        });

        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.buttonMutePress(btnMute);
            }
        });


    }

    void InitializeDiceAndButtons() {

        RollTheDiceBTN = (Button) findViewById(R.id.midnight_game_diceTheDice);
        EndTurnBTN = (Button) findViewById(R.id.midnight_game_endTurn);
        EndgameBTN = (Button) findViewById(R.id.midnight_game_endgame);

        setOfDiceBank0 = (LinearLayout) findViewById(R.id.midnight_setOfDice_Bank0);
        setOfDiceBank1 = (LinearLayout) findViewById(R.id.midnight_setOfDice_Bank1);
        setOfDiceBoard0 = (LinearLayout) findViewById(R.id.midnight_setOfDice_Board0);
        setOfDiceBoard1 = (LinearLayout) findViewById(R.id.midnight_setOfDice_Board1);

        diceShake1 = MediaPlayer.create(this, R.raw.shakerdice1);
        diceShake2 = MediaPlayer.create(this, R.raw.shakerdice2);
        diceShake3 = MediaPlayer.create(this, R.raw.shakerdice3);
        arrowSound = MediaPlayer.create(this, R.raw.arrowturn);

        arrow = (ImageView) findViewById(R.id.midnight_turnArrow);
        changeArrowTo1 = AnimationUtils.loadAnimation(MidnightGame.this, R.anim.arrorflipto1);
        changeArrowTo2 = AnimationUtils.loadAnimation(MidnightGame.this, R.anim.arrowflipto2);
        if (playerTurn == 1) arrow.startAnimation(changeArrowTo1);
        else arrow.startAnimation(changeArrowTo2);

        btnMute = (ImageButton) findViewById(R.id.midnight_game_btn_mute);
        if (Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);

        //Activate Shaker
        shakeManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Shaker.setNameOfGame("Midnight");
        Shaker.startShaker(shakeManager);

        firstDice_Bank = (ImageButton) findViewById(R.id.midnight_game_diceFirst_Bank);
        secondDice_Bank = (ImageButton) findViewById(R.id.midnight_game_diceSecond_bank);
        thirdDice_Bank = (ImageButton) findViewById(R.id.midnight_game_diceThird_Bank);
        fourthDice_Bank = (ImageButton) findViewById(R.id.midnight_game_diceFourth_Bank);
        fifthDice_Bank = (ImageButton) findViewById(R.id.midnight_game_diceFifth_bank);
        sixthDice_Bank = (ImageButton) findViewById(R.id.midnight_game_diceSixth_bank);

        firstDice_Bank.setClickable(false);
        secondDice_Bank.setClickable(false);
        thirdDice_Bank.setClickable(false);
        fourthDice_Bank.setClickable(false);
        fifthDice_Bank.setClickable(false);
        sixthDice_Bank.setClickable(false);

        diceBank[0] = firstDice_Bank;
        diceBank[1] = secondDice_Bank;
        diceBank[2] = thirdDice_Bank;
        diceBank[3] = fourthDice_Bank;
        diceBank[4] = fifthDice_Bank;
        diceBank[5] = sixthDice_Bank;

        firstDice_Board = (ImageButton) findViewById(R.id.midnight_game_diceFirst_Board);
        secondDice_Board = (ImageButton) findViewById(R.id.midnight_game_diceSecond_Board);
        thirdDice_Board = (ImageButton) findViewById(R.id.midnight_game_diceThird_Board);
        fourthDice_Board = (ImageButton) findViewById(R.id.midnight_game_diceFourth_Board);
        fifthDice_Board = (ImageButton) findViewById(R.id.midnight_game_diceFifth_Board);
        sixthDice_Board = (ImageButton) findViewById(R.id.midnight_game_diceSixth_Board);

        firstDice_Board.setClickable(false);
        secondDice_Board.setClickable(false);
        thirdDice_Board.setClickable(false);
        fourthDice_Board.setClickable(false);
        fifthDice_Board.setClickable(false);
        sixthDice_Board.setClickable(false);

        diceBoard[0] = firstDice_Board;
        diceBoard[1] = secondDice_Board;
        diceBoard[2] = thirdDice_Board;
        diceBoard[3] = fourthDice_Board;
        diceBoard[4] = fifthDice_Board;
        diceBoard[5] = sixthDice_Board;


        refreshDice();


    }

    void refreshDice() {

        for (int i = 0; i < diceAmount; i++) {
            dicePlayableBoard[i] = true;
            dicePlayableBank[i] = false;
            diceBank[i].setImageResource(R.drawable.diceempty);
            diceBank[i].setClickable(false);
            diceBoard[i].setImageResource(R.drawable.diceempty);
            diceBoard[i].setClickable(false);
        }

        diceCountCurr = diceAmount;
        diceCountTot = diceAmount;

        isFirstShake = true;

    }

    void getInfoFromExtras() {

        getNamesArr = getIntent().getStringArrayExtra("NameArr");
        getScores = getIntent().getIntArrayExtra("ScoresArr");

        PlayerName1 = (TextView) findViewById(R.id.midnight_game_playerName1);
        PlayerName2 = (TextView) findViewById(R.id.midnight_game_playerName2);

        PlayerName1.setText(getNamesArr[0]);
        PlayerName2.setText(getNamesArr[1]);

        PlayerScore1 = (TextView) findViewById(R.id.midnight_game_scorePlayer1);
        PlayerScore2 = (TextView) findViewById(R.id.midnight_game_scorePlayer2);

        PlayerScore1.setText("0");
        PlayerScore2.setText("0");

        playerTurn = getIntent().getIntExtra("playerToBegin", -1);

    }

    void setDice_InAndOut_OfBank(ImageButton diceOld, ImageButton diceNew, int valueOfDice, int indexOfDice) {

        diceNew.setClickable(true);

        if (valueOfDice == 1) diceNew.setImageResource(R.drawable.diceone);
        else if (valueOfDice == 2) diceNew.setImageResource(R.drawable.dicetwo);
        else if (valueOfDice == 3) diceNew.setImageResource(R.drawable.dicethree);
        else if (valueOfDice == 4) diceNew.setImageResource(R.drawable.dicefour);
        else if (valueOfDice == 5) diceNew.setImageResource(R.drawable.dicefive);
        else diceNew.setImageResource(R.drawable.dicesix);

        diceOld.setClickable(false);
        diceOld.setImageResource(R.drawable.diceempty);

        dicePlayableBank[indexOfDice] = !dicePlayableBank[indexOfDice];
        dicePlayableBoard[indexOfDice] = !dicePlayableBoard[indexOfDice];

    }

    static void setDiceChosenToBank(int i) {

        diceBank[i].setClickable(false);
        diceBoard[i].setClickable(false);


        if (diceValues[i] == 1)
            diceBank[i].setImageResource(R.drawable.diceonered);
        else if (diceValues[i] == 2)
            diceBank[i].setImageResource(R.drawable.dicetwored);
        else if (diceValues[i] == 3)
            diceBank[i].setImageResource(R.drawable.dicethreered);
        else if (diceValues[i] == 4)
            diceBank[i].setImageResource(R.drawable.dicefourred);
        else if (diceValues[i] == 5)
            diceBank[i].setImageResource(R.drawable.dicefivered);
        else
            diceBank[i].setImageResource(R.drawable.dicesixred);

        diceBoard[i].setImageResource(R.drawable.diceempty);

        dicePlayableBank[i] = false;
        dicePlayableBoard[i] = false;

    }

    static void UniversalShake() {

        if (diceCountTot == diceCountCurr && !isFirstShake) {
            Toast.makeText(thisActivity, R.string.noDiceWereChosen, Toast.LENGTH_SHORT).show();
            return;
        }

        if (isFirstShake) {
            isFirstShake = false;
            setOfDiceBank0.setVisibility(View.GONE);
            setOfDiceBank1.setVisibility(View.VISIBLE);
            setOfDiceBoard0.setVisibility(View.GONE);
            setOfDiceBoard1.setVisibility(View.VISIBLE);
        }

        diceCountTot = diceCountCurr;

        if (!Sounds.getIsMute()) {
            if (diceCountTot <= 6 && diceCountTot >= 4) diceShake3.start();
            else if (diceCountTot == 2 || diceCountTot == 3) diceShake2.start();
            else diceShake1.start();
        }

        for (int i = 0; i < diceAmount; i++)
            if (dicePlayableBank[i])
                setDiceChosenToBank(i);


        for (int i = 0; i < diceAmount; i++)
            if (dicePlayableBoard[i]) {
                diceValues[i] = GameFunctions.rollDice(diceBoard[i]);
                diceBoard[i].setClickable(true);
            }

    }


    void noMoreDice() {
        RollTheDiceBTN.setVisibility(View.INVISIBLE);


        if (playerTurn == 1) {
            if (player2played) EndgameBTN.setVisibility(View.VISIBLE);
            else EndTurnBTN.setVisibility(View.VISIBLE);

        } else {

            if (player1played) EndgameBTN.setVisibility(View.VISIBLE);
            else EndTurnBTN.setVisibility(View.VISIBLE);
        }
    }

    void checkButtons() {

        if (RollTheDiceBTN.getVisibility() == View.VISIBLE) return;

        if (EndgameBTN.getVisibility() == View.VISIBLE) EndgameBTN.setVisibility(View.GONE);
        else if (EndTurnBTN.getVisibility() == View.VISIBLE) EndTurnBTN.setVisibility(View.GONE);

        RollTheDiceBTN.setVisibility(View.VISIBLE);
    }

    int countDice() {

        int sum = 0;

        for (int i = 0; i < diceAmount; i++) {
            sum += diceValues[i];

            if (diceValues[i] == 1) was1 = true;
            else if (diceValues[i] == 4) was4 = true;

            diceValues[i] = 0;
        }


        if (was1 && was4) {
            was1 = false;
            was4 = false;
            return sum;
        }

        was1 = false;
        was4 = false;
        return 0;

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            shakeManager.unregisterListener(Shaker.sensorListener); //stops physical shake

            final Intent intent = new Intent(this, MidnightEntrance.class);
            intent.putExtra("NameArr", getNamesArr);
            intent.putExtra("ScoresArr", getScores);
            startActivity(intent);
            isFirstShake = true;
            PlayerScore1.setText("0");
            PlayerScore2.setText("0");
            finish();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
