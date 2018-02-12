package com.example.beni.diceolympics;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class InputNames extends AppCompatActivity {

    private MediaPlayer buttonClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_names);

        Button btnChooseGame = (Button) findViewById(R.id.InputNames_btn_chooseGame);
        final ImageButton btnMute = (ImageButton) findViewById(R.id.inputNames_btn_mute);

        //Gets the mute status and sets the button image by it
        if (Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);

        final EditText playerOneName = (EditText) findViewById(R.id.PlayerOneInput);
        final EditText playerTwoName = (EditText) findViewById(R.id.PlayerTwoInput);

        buttonClickSound = MediaPlayer.create(InputNames.this, R.raw.buttonpress);

        btnChooseGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Sounds.getIsMute()) buttonClickSound.start();

                String playerOneNameString = playerOneName.getText().toString();
                String playerTwoNameString = playerTwoName.getText().toString();


                if (playerOneNameString.matches("")) {

                    if (playerTwoNameString.matches("")) {
                        Toast.makeText(InputNames.this, R.string.NoInputForAnyPlayer, Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(InputNames.this, R.string.NoInputForPlayerOne, Toast.LENGTH_SHORT).show();
                } else if (playerTwoNameString.matches("")) {

                    Toast.makeText(InputNames.this, R.string.NoInputForPlayerTwo, Toast.LENGTH_SHORT).show();

                } else {

                    boolean FirstLegal = StringCheck(playerOneNameString);
                    boolean SecondLegal = StringCheck(playerTwoNameString);

                    //First name is Illegal
                    if (!FirstLegal) {

                        //First and second Illegal
                        if (!SecondLegal) {
                            Toast.makeText(InputNames.this, R.string.BothInputsIllegal, Toast.LENGTH_SHORT).show();
                        }

                        //Both names are illegal
                        else {
                            Toast.makeText(InputNames.this, R.string.IllegalInputForPlayerOne, Toast.LENGTH_SHORT).show();
                        }
                    }

                    //First name legal but second name is illegal
                    else if (!SecondLegal) {
                        Toast.makeText(InputNames.this, R.string.IllegalInputForPlayerTwo, Toast.LENGTH_SHORT).show();
                    }

                    //If both names are legal
                    else {

                        //Passing the given names and new score(0,0)
                        //to Choose Game Screen
                        String[] Names = {playerOneNameString, playerTwoNameString};
                        int[] Scores = {0, 0};

                        Intent intent = new Intent(InputNames.this, ChooseGame.class);
                        intent.putExtra("NameArr", Names);
                        intent.putExtra("ScoresArr", Scores);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        //A listener on the mute button
        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.buttonMutePress(btnMute);
            }
        });
    }

    //Listener on System's back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            if (!Sounds.getIsMute()) buttonClickSound.start();
            Intent intent = new Intent(InputNames.this, AppEntrance.class);
            startActivity(intent);
            finish();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    //Gets a string and checks if it's a word(with no spaces)
    //If yes returns true, else returns false
    private boolean StringCheck(String name) {

        char[] chars = name.toCharArray();

        for (char c : chars) if (!Character.isLetter(c)) return false;
        return true;
    }
}
