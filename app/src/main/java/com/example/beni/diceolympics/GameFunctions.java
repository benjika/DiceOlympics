package com.example.beni.diceolympics;

import android.view.View;

import static com.example.beni.diceolympics.PigdiceGame.SaveScoreBTN;
import static com.example.beni.diceolympics.PigdiceGame.dice;

/**
 * Created by Beni on 02-Feb-18.
 */

public class GameFunctions {

    static int rollDice(String NameOfGame)
    //return a new value from the dice
    {

        int diceNum = (int) (Math.random() * 6 + 1);

        dice.setVisibility(View.VISIBLE);


        switch (diceNum) {
            case 1:
                if (NameOfGame == "Pigdice") {
                    PigdiceGame.dice.setImageResource(R.drawable.diceone);
                    PigdiceGame.SaveScoreBTN.setVisibility(View.GONE);
                }
                return 1;

            case 2:
                if (NameOfGame == "Pigdice") {
                    PigdiceGame.dice.setImageResource(R.drawable.dicetwo);
                    PigdiceGame.SaveScoreBTN.setVisibility(View.VISIBLE);
                }
                return 2;

            case 3:
                if (NameOfGame == "Pigdice") {
                    PigdiceGame.dice.setImageResource(R.drawable.dicethree);
                    PigdiceGame.SaveScoreBTN.setVisibility(View.VISIBLE);
                }
                return 3;

            case 4:
                if (NameOfGame == "Pigdice") {
                    PigdiceGame.dice.setImageResource(R.drawable.dicefour);
                    PigdiceGame.SaveScoreBTN.setVisibility(View.VISIBLE);
                }
                return 4;

            case 5:
                if (NameOfGame == "Pigdice") {
                    PigdiceGame.dice.setImageResource(R.drawable.dicefive);
                    PigdiceGame.SaveScoreBTN.setVisibility(View.VISIBLE);
                }
                return 5;

            case 6:
                if (NameOfGame == "Pigdice") {
                    PigdiceGame.dice.setImageResource(R.drawable.dicesix);
                    PigdiceGame.SaveScoreBTN.setVisibility(View.VISIBLE);
                }
                return 6;

        }

        return 0;
    }

}
