package com.example.beni.diceolympics;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * Created by Beni on 02-Feb-18.
 */

public class GameFunctions {

    //Roll Dice function for Pigdice and Who's Starting(a screen to determine who
                                                        //starting Pigdice/Midnight games)
    //return a new value from the dice
    static int rollDice(String NameOfGame, ImageView dice)
    {

        int diceNum = (int) (Math.random() * 6 + 1);

        dice.setVisibility(View.VISIBLE);


        switch (diceNum) {
            case 1:
                if (NameOfGame.equals("Pigdice")) {
                    PigdiceGame.dice.setImageResource(R.drawable.diceone);
                    PigdiceGame.SaveScoreBTN.setVisibility(View.GONE);
                } else if (NameOfGame.equals("Who's Starting")) {
                    dice.setImageResource(R.drawable.diceone);
                }
                return 1;

            case 2:
                if (NameOfGame == "Pigdice") {
                    PigdiceGame.dice.setImageResource(R.drawable.dicetwo);
                    PigdiceGame.SaveScoreBTN.setVisibility(View.VISIBLE);
                } else if (NameOfGame.equals("Who's Starting")) {
                    dice.setImageResource(R.drawable.dicetwo);
                }
                return 2;

            case 3:
                if (NameOfGame == "Pigdice") {
                    PigdiceGame.dice.setImageResource(R.drawable.dicethree);
                    PigdiceGame.SaveScoreBTN.setVisibility(View.VISIBLE);
                } else if (NameOfGame.equals("Who's Starting")) {
                    dice.setImageResource(R.drawable.dicethree);
                }
                return 3;

            case 4:
                if (NameOfGame == "Pigdice") {
                    PigdiceGame.dice.setImageResource(R.drawable.dicefour);
                    PigdiceGame.SaveScoreBTN.setVisibility(View.VISIBLE);
                } else if (NameOfGame.equals("Who's Starting")) {
                    dice.setImageResource(R.drawable.dicefour);
                }
                return 4;

            case 5:
                if (NameOfGame == "Pigdice") {
                    PigdiceGame.dice.setImageResource(R.drawable.dicefive);
                    PigdiceGame.SaveScoreBTN.setVisibility(View.VISIBLE);
                } else if (NameOfGame.equals("Who's Starting")) {
                    dice.setImageResource(R.drawable.dicefive);
                }
                return 5;

            case 6:
                if (NameOfGame == "Pigdice") {
                    PigdiceGame.dice.setImageResource(R.drawable.dicesix);
                    PigdiceGame.SaveScoreBTN.setVisibility(View.VISIBLE);
                } else if (NameOfGame.equals("Who's Starting")) {
                    dice.setImageResource(R.drawable.dicefive);
                }
                return 6;

        }

        return 0;
    }

    //
    //return a new value from the dice
    static int rollDice(ImageButton dice)
    {

        int diceNum = (int) (Math.random() * 6 + 1);


        switch (diceNum) {
            case 1:
                dice.setImageResource(R.drawable.diceone);
                return 1;

            case 2:
                dice.setImageResource(R.drawable.dicetwo);
                return 2;

            case 3:
                dice.setImageResource(R.drawable.dicethree);
                return 3;

            case 4:
                dice.setImageResource(R.drawable.dicefour);
                return 4;

            case 5:
                dice.setImageResource(R.drawable.dicefive);
                return 5;

            case 6:
                dice.setImageResource(R.drawable.dicesix);
                return 6;

        }

        return 0;
    }

}
