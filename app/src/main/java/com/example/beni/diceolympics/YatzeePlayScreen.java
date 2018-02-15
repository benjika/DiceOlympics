package com.example.beni.diceolympics;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by luput on 02/12/2018.
 */

public class YatzeePlayScreen extends Activity {

    public final static String FINAL_SCORE = "Final_Score";

    static int[] Dices = new int[5];
    static boolean[] IsMark = new boolean[5];
    boolean[] ScorePlaced = new boolean[13];
    int[] mone = new int[7];
    static MediaPlayer diceShakeSound;
    SensorManager shakeManager;
    static int NumOfRoll;

    static ImageButton Dice_1;
    static ImageButton Dice_2;
    static ImageButton Dice_3;
    static ImageButton Dice_4;
    static ImageButton Dice_5;
    static TextView Num_Of_Roll;

    static MediaPlayer buttonClickSound;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yatzee_play_screen);


        //mute btn
        final ImageButton btnMute = (ImageButton) findViewById(R.id.yatzee_play_screen_btn_mute);
        if (Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);
        final MediaPlayer buttonClickSound = MediaPlayer.create(YatzeePlayScreen.this, R.raw.buttonpress);
        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.buttonMutePress(btnMute);
            }
        });
        //end mute btn

        //Activate Shaker
        shakeManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Shaker.setNameOfGame("YatzeeGame");
        Shaker.startShaker(shakeManager);
        diceShakeSound = MediaPlayer.create(this, R.raw.shakerdice1);



        final Button Back_BTN = findViewById(R.id.Yatzee_Play_Screen_Back_BTN);
        final Button Roll_Dice_BTN = findViewById(R.id.Yatzee_Play_Screen_Roll_Dice_BTN);
        Dice_1 = findViewById(R.id.Yatzee_Dice_Num_One);
        Dice_2 = findViewById(R.id.Yatzee_Dice_Num_Two);
        Dice_3 = findViewById(R.id.Yatzee_Dice_Num_Three);
        Dice_4 = findViewById(R.id.Yatzee_Dice_Num_Four);
        Dice_5 = findViewById(R.id.Yatzee_Dice_Num_Five);
        final TextView Aces_score = findViewById(R.id.AcesScore);
        final TextView Twos_score = findViewById(R.id.TwosScore);
        final TextView Threes_score = findViewById(R.id.ThreesScore);
        final TextView Fours_score = findViewById(R.id.FoursScore);
        final TextView Fives_score = findViewById(R.id.FivesScore);
        final TextView Sixs_score = findViewById(R.id.SixsScore);
        final TextView Three_Of_A_Kind_score = findViewById(R.id.ThreeOfAKindScore);
        final TextView Four_Of_A_Kind_score = findViewById(R.id.FourOfAKindScore);
        final TextView Full_House_score = findViewById(R.id.FullHousScore);
        final TextView Small_Straight_score = findViewById(R.id.SmallStraightScore);
        final TextView Large_Straight_score = findViewById(R.id.LargeStraightScore);
        final TextView Yatzee_score = findViewById(R.id.YatzeeScore);
        final TextView Chance_score = findViewById(R.id.ChanceScore);
        final TextView High_Score_Total = findViewById(R.id.HighScoreTotal);
        final TextView Low_Score_Total = findViewById(R.id.LowScoreTotal);
        Num_Of_Roll = findViewById(R.id.NumOfRoll);
        final TextView Total_Score = findViewById(R.id.TotalScore);

        //init dices numbers
        for (int i = 0; i < 5; i++) {
            Dices[i] = 0;
        }
        //init marked dices
        for (int i = 0; i < 5; i++) {
            IsMark[i] = false;
        }
        //init categorz select
        for (int i = 0; i < 13; i++) {
            ScorePlaced[i] = false;
        }
        //init Mone Array
        for (int i = 0; i < 7; i++) {
            mone[i] = 0;
        }

        Back_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(YatzeePlayScreen.this,YatzeeEntrance.class);
                startActivity(intent);
                finish();
            }
        });


        Aces_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[0]) {
                        ScorePlaced[0] = true;
                        int sum = 0;
                        UpdateMone();
                        sum = mone[1];
                        RestarMoneAndDices();
                        Aces_score.setText(String.valueOf(sum));
                        Aces_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        Low_Score_Total.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + sum));
                        Total_Score.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + Integer.valueOf(High_Score_Total.getText().toString())));

                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Twos_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[1]) {
                        ScorePlaced[1] = true;
                        int sum = 0;
                        UpdateMone();
                        sum = mone[2] * 2;
                        RestarMoneAndDices();
                        Twos_score.setText(String.valueOf(sum));
                        Twos_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        Low_Score_Total.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + sum));
                        Total_Score.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + Integer.valueOf(High_Score_Total.getText().toString())));

                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Threes_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[2]) {
                        ScorePlaced[2] = true;
                        int sum = 0;
                        UpdateMone();
                        sum = mone[3] * 3;
                        RestarMoneAndDices();
                        Threes_score.setText(String.valueOf(sum));
                        Threes_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        Low_Score_Total.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + sum));
                        Total_Score.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + Integer.valueOf(High_Score_Total.getText().toString())));

                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Fours_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[3]) {
                        ScorePlaced[3] = true;
                        int sum = 0;
                        UpdateMone();
                        sum = mone[4] * 4;
                        RestarMoneAndDices();
                        Fours_score.setText(String.valueOf(sum));
                        Fours_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        Low_Score_Total.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + sum));
                        Total_Score.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + Integer.valueOf(High_Score_Total.getText().toString())));

                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Fives_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[4]) {
                        ScorePlaced[4] = true;
                        int sum = 0;
                        UpdateMone();
                        sum = mone[5] * 5;
                        RestarMoneAndDices();
                        Fives_score.setText(String.valueOf(sum));
                        Fives_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        Low_Score_Total.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + sum));
                        Total_Score.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + Integer.valueOf(High_Score_Total.getText().toString())));

                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Sixs_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[5]) {
                        ScorePlaced[5] = true;
                        int sum = 0;
                        UpdateMone();
                        sum = mone[6] * 6;
                        RestarMoneAndDices();
                        Sixs_score.setText(String.valueOf(sum));
                        Sixs_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        Low_Score_Total.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + sum));
                        Total_Score.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + Integer.valueOf(High_Score_Total.getText().toString())));

                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Three_Of_A_Kind_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[6]) {
                        ScorePlaced[6] = true;
                        int sum = 0;
                        boolean ThreeOfAKind = false;
                        UpdateMone();
                        for (int i = 1; i < 7; i++) {
                            if (mone[i] >= 3)
                                ThreeOfAKind = true;
                        }
                        if (ThreeOfAKind) {
                            for (int i = 0; i < 5; i++) {
                                sum += Dices[i];
                            }
                        }
                        RestarMoneAndDices();
                        Three_Of_A_Kind_score.setText(String.valueOf(sum));
                        Three_Of_A_Kind_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        High_Score_Total.setText(String.valueOf(sum + Integer.valueOf(High_Score_Total.getText().toString())));
                        Total_Score.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + Integer.valueOf(High_Score_Total.getText().toString())));

                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Four_Of_A_Kind_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[7]) {
                        ScorePlaced[7] = true;
                        int sum = 0;
                        boolean FourOfAKInd = false;
                        UpdateMone();
                        for (int i = 1; i < 7; i++) {
                            if (mone[i] >= 4)
                                FourOfAKInd = true;
                        }
                        if (FourOfAKInd) {
                            for (int i = 0; i < 5; i++) {
                                sum += Dices[i];
                            }
                        }
                        RestarMoneAndDices();
                        Four_Of_A_Kind_score.setText(String.valueOf(sum));
                        Four_Of_A_Kind_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        High_Score_Total.setText(String.valueOf(sum + Integer.valueOf(High_Score_Total.getText().toString())));
                        Total_Score.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + Integer.valueOf(High_Score_Total.getText().toString())));

                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Full_House_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[8]) {
                        ScorePlaced[8] = true;
                        boolean twotimes = false, threetimes = false;
                        UpdateMone();
                        for (int i = 1; i < 7; i++) {
                            if (mone[i] == 2) twotimes = true;
                            if (mone[i] == 3) threetimes = true;
                        }
                        RestarMoneAndDices();
                        if (twotimes && threetimes) {
                            Full_House_score.setText("25");
                            Full_House_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            High_Score_Total.setText(String.valueOf(25 + Integer.valueOf(High_Score_Total.getText().toString())));
                            Total_Score.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + Integer.valueOf(High_Score_Total.getText().toString())));
                        } else {
                            Full_House_score.setText("0");
                            Full_House_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        }
                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Small_Straight_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[9]) {
                        ScorePlaced[9] = true;
                        boolean SmallStr = false;
                        int StraithCounter = 1;
                        UpdateMone();
                        for (int i = 1; i < 6; i++) {
                            if (mone[i] >= 1 && mone[i + 1] >= 1) StraithCounter++;
                            if (StraithCounter >= 4) {
                                SmallStr = true;
                                break;
                            }
                            if (mone[i] == 0 || mone[i + 1] == 0) StraithCounter = 1;
                        }
                        RestarMoneAndDices();
                        if (SmallStr) {
                            Small_Straight_score.setText("30");
                            Small_Straight_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            High_Score_Total.setText(String.valueOf(30 + Integer.valueOf(High_Score_Total.getText().toString())));
                            Total_Score.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + Integer.valueOf(High_Score_Total.getText().toString())));
                        } else {
                            Small_Straight_score.setText("0");
                            Small_Straight_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        }
                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }

        });

        Large_Straight_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[10]) {
                        ScorePlaced[10] = true;
                        boolean LargeStr = false;
                        int StraithCounter = 1;
                        UpdateMone();
                        for (int i = 0; i < 5; i++) {
                            if (mone[i] >= 1 && mone[i + 1] >= 1) StraithCounter++;
                            if (StraithCounter >= 5) {
                                LargeStr = true;
                                break;
                            }
                            if (mone[i] == 0 || mone[i + 1] == 0) StraithCounter = 1;
                        }
                        RestarMoneAndDices();
                        if (LargeStr) {
                            Large_Straight_score.setText("40");
                            Large_Straight_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            High_Score_Total.setText(String.valueOf(30 + Integer.valueOf(High_Score_Total.getText().toString())));
                            Total_Score.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + Integer.valueOf(High_Score_Total.getText().toString())));
                        } else {
                            Large_Straight_score.setText("0");
                            Large_Straight_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        }
                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Yatzee_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[11]) {
                        ScorePlaced[11] = true;
                        boolean yatzee = false;
                        UpdateMone();
                        for (int i = 0; i < 7; i++) {
                            if (mone[i] == 5) yatzee = true;
                        }
                        RestarMoneAndDices();
                        if (yatzee) {
                            Yatzee_score.setText("50");
                            Yatzee_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            High_Score_Total.setText(String.valueOf(50 + Integer.valueOf(High_Score_Total.getText().toString())));
                            Total_Score.setText(String.valueOf(Integer.valueOf(Low_Score_Total.getText().toString()) + Integer.valueOf(High_Score_Total.getText().toString())));
                        } else {
                            Yatzee_score.setText("0");
                            Yatzee_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        }
                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }

        });

        Chance_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (oktosave(Integer.valueOf(Num_Of_Roll.getText().toString()))) {
                    if (!ScorePlaced[12]) {
                        ScorePlaced[12] = true;
                        int sum = 0;
                        for (int i = 0; i < 5; i++) {
                            sum += Dices[i];
                            Dices[i] = 0;
                        }
                        Chance_score.setText(String.valueOf(sum));
                        Chance_score.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        High_Score_Total.setText(String.valueOf(Integer.valueOf(High_Score_Total.getText().toString()) + sum));
                        Total_Score.setText(String.valueOf(Integer.valueOf(Integer.valueOf(Total_Score.getText().toString())) + sum));

                        Num_Of_Roll.setText("3");
                        ResetDice(Dice_1, Dice_2, Dice_3, Dice_4, Dice_5);
                        if(CheackIfFinish()){
                            if(CheakIfNewChamp(Integer.valueOf(Total_Score.getText().toString()))){
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGameTopPlayer.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(YatzeePlayScreen.this,YatzeeEndGame.class);
                                intent.putExtra(FINAL_SCORE,Total_Score.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Choose, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(YatzeePlayScreen.this, R.string.MustThrow, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Dice_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (IsMark[0]) {
                    ChangeDiceWhite(Dice_1,Dices[0]);
                } else {
                    ChangeDiceRed(Dice_1,Dices[0]);
                }
                IsMark[0] = !(IsMark[0]);
            }
        });
        Dice_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (IsMark[1]) {
                    ChangeDiceWhite(Dice_2,Dices[1]);
                } else {
                    ChangeDiceRed(Dice_2,Dices[1]);
                }
                IsMark[1] = !(IsMark[1]);
            }
        });
        Dice_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (IsMark[2]) {
                    ChangeDiceWhite(Dice_3,Dices[2]);
                } else {
                    ChangeDiceRed(Dice_3,Dices[2]);
                }

                IsMark[2] = !(IsMark[2]);
            }
        });
        Dice_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (IsMark[3]) {
                    ChangeDiceWhite(Dice_4,Dices[3]);
                } else {
                    ChangeDiceRed(Dice_4,Dices[3]);
                }
                IsMark[3] = !(IsMark[3]);
            }
        });
        Dice_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                if (IsMark[4]) {
                    ChangeDiceWhite(Dice_5,Dices[4]);
                } else {
                    ChangeDiceRed(Dice_5,Dices[4]);
                }
                IsMark[4] = !(IsMark[4]);
            }
        });

        Roll_Dice_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                NumOfRoll = Integer.valueOf(Num_Of_Roll.getText().toString());
                if (NumOfRoll > 0)
                    UniversalShake();
                else
                    Toast.makeText(YatzeePlayScreen.this, R.string.Cant_Roll, Toast.LENGTH_SHORT).show();

            }
        });
    }

    static  void RollTheDIce(ImageButton Dice_To_Roll,int NumOfDice){
        switch (NumOfDice) {
            case 1:
                Dice_To_Roll.setImageResource(R.drawable.diceone);
                break;
            case 2:
                Dice_To_Roll.setImageResource(R.drawable.dicetwo);
                break;
            case 3:
                Dice_To_Roll.setImageResource(R.drawable.dicethree);
                break;
            case 4:
                Dice_To_Roll.setImageResource(R.drawable.dicefour);
                break;
            case 5:
                Dice_To_Roll.setImageResource(R.drawable.dicefive);
                break;
            case 6:
                Dice_To_Roll.setImageResource(R.drawable.dicesix);
                break;
        }
    }

    public void ChangeDiceWhite(ImageButton DiceToChange,int NumOfDice){
        switch (NumOfDice) {
            case 1:
                DiceToChange.setImageResource(R.drawable.diceone);
                break;
            case 2:
                DiceToChange.setImageResource(R.drawable.dicetwo);
                break;
            case 3:
                DiceToChange.setImageResource(R.drawable.dicethree);
                break;
            case 4:
                DiceToChange.setImageResource(R.drawable.dicefour);
                break;
            case 5:
                DiceToChange.setImageResource(R.drawable.dicefive);
                break;
            case 6:
                DiceToChange.setImageResource(R.drawable.dicesix);
                break;
        }
    }

    public void ChangeDiceRed(ImageButton DiceToChange,int NumOfDice){
        switch (NumOfDice) {
            case 1:
                DiceToChange.setImageResource(R.drawable.diceonered);
                break;
            case 2:
                DiceToChange.setImageResource(R.drawable.dicetwored);
                break;
            case 3:
                DiceToChange.setImageResource(R.drawable.dicethreered);
                break;
            case 4:
                DiceToChange.setImageResource(R.drawable.dicefourred);
                break;
            case 5:
                DiceToChange.setImageResource(R.drawable.dicefivered);
                break;
            case 6:
                DiceToChange.setImageResource(R.drawable.dicesixred);
                break;
        }

    }

    public void UpdateMone() {
        for (int i = 0; i < 5; i++) {
            mone[Dices[i]]++;
        }
    }

    public void RestarMoneAndDices() {
        for (int i = 0; i < 7; i++) {
            mone[i] = 0;
        }
        for (int i = 0; i < 5; i++) {
            Dices[i] = 0;
        }
    }

    public void ResetDice(ImageButton Dice_1, ImageButton Dice_2, ImageButton Dice_3, ImageButton Dice_4, ImageButton Dice_5) {
        Dice_1.setImageResource(R.drawable.diceone);
        Dice_2.setImageResource(R.drawable.diceone);
        Dice_3.setImageResource(R.drawable.diceone);
        Dice_4.setImageResource(R.drawable.diceone);
        Dice_5.setImageResource(R.drawable.diceone);
        for (int i = 0; i < 5; i++) {
            IsMark[i] = false;
        }
    }

    //cheack end game after all 13 category are cheak
    public boolean CheackIfFinish() {
        for (int i = 0; i < 13; i++) {
            if (ScorePlaced[i] == false) return false;
        }
        return true;
    }

    public boolean CheakIfNewChamp(int Total_Score) {
        SharedPreferences sp = getSharedPreferences("details", MODE_PRIVATE);
        String score = sp.getString("high_score", "");
        int topscore;
        if (score.equals(""))
            topscore = 0;
        else
            topscore = Integer.valueOf(score);
        //select if this is new high score
        if (topscore > Total_Score) {
            return false;
        } else {
            return true;
        }
    }

    public boolean oktosave (int numoftries){
        if(numoftries<3) return true;
        return false;
    }

    static void UniversalShake() {
        if (NumOfRoll > 0) {
            if (!Sounds.getIsMute()) diceShakeSound.start();
            if (!IsMark[0]) {
                int diceNum1 = (int) (Math.random() * 6 + 1);
                RollTheDIce(Dice_1, diceNum1);
                Dices[0] = diceNum1;
            }
            if (!IsMark[1]) {
                int diceNum2 = (int) (Math.random() * 6 + 1);
                RollTheDIce(Dice_2, diceNum2);
                Dices[1] = diceNum2;
            }
            if (!IsMark[2]) {
                int diceNum3 = (int) (Math.random() * 6 + 1);
                RollTheDIce(Dice_3, diceNum3);
                Dices[2] = diceNum3;
            }
            if (!IsMark[3]) {
                int diceNum4 = (int) (Math.random() * 6 + 1);
                RollTheDIce(Dice_4, diceNum4);
                Dices[3] = diceNum4;
            }
            if (!IsMark[4]) {
                int diceNum5 = (int) (Math.random() * 6 + 1);
                RollTheDIce(Dice_5, diceNum5);
                Dices[4] = diceNum5;
            }
            NumOfRoll--;
            Num_Of_Roll.setText(String.valueOf(NumOfRoll));
        }
    }


}