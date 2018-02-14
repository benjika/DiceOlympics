package com.example.beni.diceolympics;

import android.content.Intent;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MidnightGame extends AppCompatActivity {

    String[] getNamesArr;
    int[] getScores;

    SensorManager shakeManager; //Activates dice shaker

    int playerTurn = (int) Math.random() * 2 + 1;

    boolean player1played = false;
    boolean player2played = false;

    boolean was4 = false;
    boolean was1 = false;

    MediaPlayer diceShake1;
    MediaPlayer diceShake2;
    MediaPlayer diceShake3;

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

boolean isFirstShake=true;

    int[] diceValues = {0, 0, 0, 0, 0, 0};

    ImageButton firstDice_Chosen;
    ImageButton secondDice_Chosen;
    ImageButton thirdDice_Chosen;
    ImageButton fourthDice_Chosen;
    ImageButton fifthDice_Chosen;
    ImageButton sixthDice_Chosen;


    ImageButton[] arrayOfButtonsChosen = {firstDice_Chosen, secondDice_Chosen, thirdDice_Chosen,
            fourthDice_Chosen, fifthDice_Chosen, sixthDice_Chosen};

    ImageButton firstDice_unChosen;
    ImageButton secondDice_unChosen;
    ImageButton thirdDice_unChosen;
    ImageButton fourthDice_unChosen;
    ImageButton fifthDice_unChosen;
    ImageButton sixthDice_unChosen;

    boolean[] arrayOfValuesUnChosen = {true, true, true, true, true, true};
    ImageButton[] arrayOfButtonsUnChosen = {firstDice_unChosen, secondDice_unChosen,
            thirdDice_unChosen, fourthDice_unChosen, fifthDice_unChosen, sixthDice_unChosen};

    int diceAmount = arrayOfValuesUnChosen.length;

    int diceCountCurr = diceAmount;
    int diceCountTot = diceAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midnight_game);

        getInfoFromExtras();
        InitializeDiceAndButtons();

        firstDice_unChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDice_InAndOut_OfBank(firstDice_unChosen, firstDice_Chosen, diceValues[0]);
                diceCountCurr--;
                if (diceCountCurr == 0) noMoreDice();
            }
        });

        secondDice_unChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDice_InAndOut_OfBank(secondDice_unChosen, secondDice_Chosen, diceValues[1]);
                diceCountCurr--;
                if (diceCountCurr == 0) noMoreDice();
            }
        });

        thirdDice_unChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDice_InAndOut_OfBank(thirdDice_unChosen, thirdDice_Chosen, diceValues[2]);
                diceCountCurr--;
                if (diceCountCurr == 0) noMoreDice();
            }
        });

        fourthDice_unChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDice_InAndOut_OfBank(fourthDice_unChosen, fourthDice_Chosen, diceValues[3]);
                diceCountCurr--;
                if (diceCountCurr == 0) noMoreDice();
            }
        });

        fifthDice_unChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDice_InAndOut_OfBank(fifthDice_unChosen, fifthDice_Chosen, diceValues[4]);
                diceCountCurr--;
                if (diceCountCurr == 0) noMoreDice();
            }
        });

        sixthDice_unChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDice_InAndOut_OfBank(sixthDice_unChosen, sixthDice_Chosen, diceValues[5]);
                diceCountCurr--;
                if (diceCountCurr == 0) noMoreDice();
            }
        });

        firstDice_Chosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDice_InAndOut_OfBank(firstDice_Chosen, firstDice_unChosen, diceValues[0]);
                diceCountCurr++;
                checkButtons();
            }
        });

        secondDice_Chosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDice_InAndOut_OfBank(secondDice_Chosen, secondDice_unChosen, diceValues[1]);
                diceCountCurr++;
                checkButtons();
            }
        });

        thirdDice_unChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDice_InAndOut_OfBank(thirdDice_Chosen, thirdDice_unChosen, diceValues[2]);
                diceCountCurr++;
                checkButtons();
            }
        });

        fourthDice_unChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDice_InAndOut_OfBank(fourthDice_Chosen, fourthDice_unChosen, diceValues[3]);
                diceCountCurr++;
                checkButtons();
            }
        });

        fifthDice_unChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDice_InAndOut_OfBank(fifthDice_Chosen, fifthDice_unChosen, diceValues[4]);
                diceCountCurr++;
                checkButtons();
            }
        });

        sixthDice_unChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDice_InAndOut_OfBank(sixthDice_Chosen, sixthDice_unChosen, diceValues[5]);
                diceCountCurr++;
                checkButtons();
            }
        });

        RollTheDiceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UltimateShake();
            }
        });

        EndTurnBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerTurn == 1) {
                    PlayerScore1.setText(String.valueOf(countDice()));
                    playerTurn = 2;
                    arrow.startAnimation(changeArrowTo2);
                } else {
                    PlayerScore2.setText(String.valueOf(countDice()));
                    playerTurn = 1;
                    arrow.startAnimation(changeArrowTo1);
                }

                refreshDice();


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
                            intent.putExtra("WinnerName", 3);
                        } else if (player1ScoreINT > player2ScoreINT) {
                            getScores[0]++;
                            intent.putExtra("WinnerName", 0);

                        } else {
                            getScores[1]++;
                            intent.putExtra("WinnerName", 1);
                        }

                        intent.putExtra("ScoresArr", getScores);
                        startActivity(intent);
                        finish();
                    }
                };

                Handler handler = new Handler();

                handler.postDelayed(r, 2500);
            }
        });


    }

    void InitializeDiceAndButtons() {

        RollTheDiceBTN = (Button) findViewById(R.id.midnight_game_diceTheDice);
        EndTurnBTN = (Button) findViewById(R.id.midnight_game_endTurn);
        EndgameBTN = (Button) findViewById(R.id.midnight_game_endgame);

        arrow = (ImageView) findViewById(R.id.midnight_turnArrow);
        changeArrowTo1 = AnimationUtils.loadAnimation(MidnightGame.this, R.anim.arrorflipto1);
        changeArrowTo2 = AnimationUtils.loadAnimation(MidnightGame.this, R.anim.arrowflipto2);
        if (playerTurn == 1) arrow.startAnimation(changeArrowTo1);
        else arrow.startAnimation(changeArrowTo2);


        //Activate Shaker
        shakeManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Shaker.setNameOfGame("Midnight");
        Shaker.startShaker(shakeManager);

        firstDice_Chosen = (ImageButton) findViewById(R.id.midnight_game_diceFirst_chosen);
        secondDice_Chosen = (ImageButton) findViewById(R.id.midnight_game_diceSecond_chosen);
        thirdDice_Chosen = (ImageButton) findViewById(R.id.midnight_game_diceThird_chosen);
        fourthDice_Chosen = (ImageButton) findViewById(R.id.midnight_game_diceFourth_chosen);
        fifthDice_Chosen = (ImageButton) findViewById(R.id.midnight_game_diceFifth_chosen);
        sixthDice_Chosen = (ImageButton) findViewById(R.id.midnight_game_diceSixth_chosen);

        firstDice_unChosen = (ImageButton) findViewById(R.id.midnight_game_diceFirst_unChosen);
        secondDice_unChosen = (ImageButton) findViewById(R.id.midnight_game_diceSecond_unChosen);
        thirdDice_unChosen = (ImageButton) findViewById(R.id.midnight_game_diceThird_unChosen);
        fourthDice_unChosen = (ImageButton) findViewById(R.id.midnight_game_diceFourth_unChosen);
        fifthDice_unChosen = (ImageButton) findViewById(R.id.midnight_game_diceFifth_unChosen);
        sixthDice_unChosen = (ImageButton) findViewById(R.id.midnight_game_diceSixth_unChosen);

        refreshDice();

    }

    void refreshDice() {

        firstDice_Chosen.setImageResource(R.drawable.diceempty);
        secondDice_Chosen.setImageResource(R.drawable.diceempty);
        thirdDice_Chosen.setImageResource(R.drawable.diceempty);
        fourthDice_Chosen.setImageResource(R.drawable.diceempty);
        fifthDice_Chosen.setImageResource(R.drawable.diceempty);
        sixthDice_Chosen.setImageResource(R.drawable.diceempty);

        firstDice_unChosen.setImageResource(R.drawable.diceempty);
        secondDice_unChosen.setImageResource(R.drawable.diceempty);
        thirdDice_unChosen.setImageResource(R.drawable.diceempty);
        fourthDice_unChosen.setImageResource(R.drawable.diceempty);
        fifthDice_unChosen.setImageResource(R.drawable.diceempty);
        sixthDice_unChosen.setImageResource(R.drawable.diceempty);

        firstDice_Chosen.setClickable(false);
        secondDice_Chosen.setClickable(false);
        thirdDice_Chosen.setClickable(false);
        fourthDice_Chosen.setClickable(false);
        fifthDice_Chosen.setClickable(false);
        sixthDice_Chosen.setClickable(false);

        firstDice_unChosen.setClickable(false);
        secondDice_unChosen.setClickable(false);
        thirdDice_unChosen.setClickable(false);
        fourthDice_unChosen.setClickable(false);
        fifthDice_unChosen.setClickable(false);
        sixthDice_unChosen.setClickable(false);

    }

    void getInfoFromExtras() {

        getNamesArr = getIntent().getStringArrayExtra("NameArr");
        getScores = getIntent().getIntArrayExtra("ScoresArr");

        PlayerName1 = (TextView) findViewById(R.id.midnight_game_playerName1);
        PlayerName2 = (TextView) findViewById(R.id.midnight_game_playerName1);

        PlayerName1.setText(getNamesArr[0]);
        PlayerName2.setText(getNamesArr[1]);

        PlayerScore1 = (TextView) findViewById(R.id.midnight_game_scorePlayer1);
        PlayerScore2 = (TextView) findViewById(R.id.midnight_game_scorePlayer2);

        PlayerScore1.setText("0");
        PlayerScore2.setText("0");

    }

    void setDice_InAndOut_OfBank(ImageButton diceOld, ImageButton diceNew, int i) {

        diceNew.setClickable(true);

        if (i == 1) diceNew.setImageResource(R.drawable.diceone);
        else if (i == 2) diceNew.setImageResource(R.drawable.dicetwo);
        else if (i == 3) diceNew.setImageResource(R.drawable.dicethree);
        else if (i == 4) diceNew.setImageResource(R.drawable.dicefour);
        else if (i == 5) diceNew.setImageResource(R.drawable.dicefive);
        else diceNew.setImageResource(R.drawable.dicesix);

        diceOld.setClickable(false);
        diceOld.setImageResource(R.drawable.diceempty);

    }

    void setDiceChosenToBank(ImageButton dice, int i) {

        dice.setClickable(false);

        if (i == 1) dice.setImageResource(R.drawable.diceonered);
        else if (i == 2) dice.setImageResource(R.drawable.dicetwored);
        else if (i == 3) dice.setImageResource(R.drawable.dicethreered);
        else if (i == 4) dice.setImageResource(R.drawable.dicefourred);
        else if (i == 5) dice.setImageResource(R.drawable.dicefivered);
        else dice.setImageResource(R.drawable.dicesixred);

        arrayOfValuesUnChosen[i] = false;

    }

    void UltimateShake() {

        if (diceCountTot == diceCountCurr && !isFirstShake) {
            Toast.makeText(this, R.string.noDiceWereChosen, Toast.LENGTH_SHORT);
            return;
        }

        isFirstShake = false;

        diceCountTot = diceCountCurr;

        for (int i = 0; i < diceAmount; i++)
            if (arrayOfButtonsChosen[i].isClickable())
                setDiceChosenToBank(arrayOfButtonsChosen[i], i);


        for (int i = 0; i < diceAmount; i++)
            if (arrayOfValuesUnChosen[i]) rollDice(arrayOfButtonsUnChosen[i], i);


    }

    void rollDice(ImageButton diceToRoll, int i) {
        diceValues[i] = GameFunctions.rollDice(diceToRoll);
        diceToRoll.setClickable(true);
    }

    void noMoreDice() {
        RollTheDiceBTN.setVisibility(View.INVISIBLE);


        if (playerTurn == 1) {

            if (player2played) EndgameBTN.setVisibility(View.VISIBLE);
            else EndTurnBTN.setVisibility(View.VISIBLE);

        } else {

            if (player1played) EndgameBTN.setVisibility(View.VISIBLE);
            else EndgameBTN.setVisibility(View.VISIBLE);
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

        if (was1 && was4) return sum;

        return 0;

    }
}
