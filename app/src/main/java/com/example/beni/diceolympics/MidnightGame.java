package com.example.beni.diceolympics;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class MidnightGame extends AppCompatActivity {

    String[] getNamesArr;
    int[] getScores;

    boolean was4 = false;
    boolean was1 = false;

    MediaPlayer diceShake1;
    MediaPlayer diceShake2;
    MediaPlayer diceShake3;

    Button RollTheDiceBTN;
    Button EndTurnBTN;

    ImageButton firstDice_Chosen;
    ImageButton secondDice_Chosen;
    ImageButton thirdDice_Chosen;
    ImageButton fourthDice_Chosen;
    ImageButton fifthDice_Chosen;
    ImageButton sixthDice_Chosen;

    ImageButton firstDice_unChosen;
    ImageButton secondDice_unChosen;
    ImageButton thirdDice_unChosen;
    ImageButton fourthDice_unChosen;
    ImageButton fifthDice_unChosen;
    ImageButton sixthDice_unChosen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midnight_game);

        InitializeDice();

        getInfoFromExtras();

    }

    void InitializeDice() {

        firstDice_Chosen = (ImageButton) findViewById(R.id.midnight_diceFirst_chosen);
        secondDice_Chosen = (ImageButton) findViewById(R.id.midnight_diceSecond_chosen);
        thirdDice_Chosen = (ImageButton) findViewById(R.id.midnight_diceThird_chosen);
        fourthDice_Chosen = (ImageButton) findViewById(R.id.midnight_diceFourth_chosen);
        fifthDice_Chosen = (ImageButton) findViewById(R.id.midnight_diceFifth_chosen);
        sixthDice_Chosen = (ImageButton) findViewById(R.id.midnight_diceSixth_chosen);

        firstDice_unChosen = (ImageButton) findViewById(R.id.midnight_diceFirst_unChosen);
        secondDice_unChosen = (ImageButton) findViewById(R.id.midnight_diceSecond_unChosen);
        thirdDice_unChosen = (ImageButton) findViewById(R.id.midnight_diceThird_unChosen);
        fourthDice_unChosen = (ImageButton) findViewById(R.id.midnight_diceFourth_unChosen);
        fifthDice_unChosen = (ImageButton) findViewById(R.id.midnight_diceFifth_unChosen);
        sixthDice_unChosen = (ImageButton) findViewById(R.id.midnight_diceSixth_unChosen);

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
    }


}
