package com.example.beni.diceolympics;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MidnightEntrance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midnight_entrance);

        final Button btnStartGame = (Button) findViewById(R.id.midnight_Entrance_btn_Start);
        final Button btnInstructions = (Button) findViewById(R.id.midnight_Entrance_btn_Instructions);
        final Button btnBack = (Button) findViewById(R.id.midnight_Entrance_btn_Back);

        final MediaPlayer buttonClickSound = MediaPlayer.create(MidnightEntrance.this, R.raw.buttonpress);

        final String[] getNamesArr = getIntent().getStringArrayExtra("NameArr");
        final int[] getScores = getIntent().getIntArrayExtra("ScoresArr");


        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                /*Intent intent = new Intent(MidnightEntrance.this, MidnightGame.class);
                intent.putExtra("NameArr", NamesArr);
                intent.putExtra("ScoresArr", Scores);
                startActivity(intent);
                finish();*/
                Toast.makeText(MidnightEntrance.this, "Will Work", Toast.LENGTH_SHORT).show();
            }
        });

        btnInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(MidnightEntrance.this, MidnightInstructions.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                final Intent intent = new Intent(MidnightEntrance.this, ChooseGame.class);
                intent.putExtra("NameArr", getNamesArr);
                intent.putExtra("ScoresArr", getScores);
                startActivity(intent);
                finish();
            }
        });
    }
}
