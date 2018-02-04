package com.example.beni.diceolympics;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class InputNames extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_names);

        Button btnChooseGame = (Button) findViewById(R.id.InputNames_btn_chooseGame);
        final ImageButton btnMute = (ImageButton)findViewById(R.id.inputNames_btn_mute);

        if(Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);

        final EditText playerOneName = (EditText) findViewById(R.id.PlayerOneInput);
        final EditText playerTwoName = (EditText) findViewById(R.id.PlayerTwoInput);

        final MediaPlayer buttonClickSound = MediaPlayer.create(InputNames.this, R.raw.buttonpress);

        btnChooseGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Sounds.getIsMute()) buttonClickSound.start();

                boolean FirstLegal;
                boolean SecondLegal;

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

                    FirstLegal = StringCheck(playerOneNameString);
                    SecondLegal = StringCheck(playerTwoNameString);

                    if (!FirstLegal) {

                        if (!SecondLegal)
                            Toast.makeText(InputNames.this, R.string.BothInputsIllegal, Toast.LENGTH_SHORT).show();

                        else {

                            Toast.makeText(InputNames.this, R.string.IllegalInputForPlayerOne, Toast.LENGTH_SHORT).show();
                        }

                    } else if (!SecondLegal) {

                        Toast.makeText(InputNames.this, R.string.IllegalInputForPlayerTwo, Toast.LENGTH_SHORT).show();
                    } else {

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

        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.buttonMutePress(btnMute);
            }
        });
    }

    public boolean StringCheck(String name) {

        char[] chars = name.toCharArray();

        for (char c : chars) if (!Character.isLetter(c)) return false;
        return true;
    }
}
