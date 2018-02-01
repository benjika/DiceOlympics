package com.example.beni.diceolympics;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChooseGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        Button btnPlayPigDice = (Button) findViewById(R.id.chooseGame_btn_playPigDice);
        Button btnPlayMidnight = (Button) findViewById(R.id.chooseGame_btn_playMidnight);

        final MediaPlayer buttonClickSound = MediaPlayer.create(ChooseGame.this , R.raw.buttonpress);

        final String[] getNamesArr = getIntent().getStringArrayExtra("NameArr");
        final int[] getScores = getIntent().getIntArrayExtra("ScoresArr");

        btnPlayPigDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickSound.start();
                Intent intent = new Intent(ChooseGame.this, PigdiceEntrance.class);
                intent.putExtra("NameArr", getNamesArr);
                intent.putExtra("ScoresArr", getScores);
                startActivity(intent);
                finish();
            }
        });


        btnPlayMidnight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickSound.start();
                Intent intent = new Intent(ChooseGame.this, MidnightEntrance.class);
                intent.putExtra("NameArr", getNamesArr);
                intent.putExtra("ScoresArr", getScores);
                startActivity(intent);
                finish();

            }
        });

    }
}
