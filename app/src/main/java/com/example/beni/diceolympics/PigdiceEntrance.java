package com.example.beni.diceolympics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PigdiceEntrance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pigdice_entrance);

        final Button btnStartGame = (Button) findViewById(R.id.pigdice_btn_Start);
        final Button btnInstructions = (Button) findViewById(R.id.pigdice_btn_Instructions);
        final Button btnBack = (Button) findViewById(R.id.pigdice_btn_Back);


        final String[] getNamesArr = getIntent().getStringArrayExtra("NameArr");
        final int[] getScores = getIntent().getIntArrayExtra("ScoresArr");


        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sounds.playButtonPress(PigdiceEntrance.this);
                Intent intent = new Intent(PigdiceEntrance.this, PigdiceGame.class);
                intent.putExtra("NameArr", getNamesArr);
                intent.putExtra("ScoresArr", getScores);
                startActivity(intent);
                finish();
            }
        });

        btnInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sounds.playButtonPress(PigdiceEntrance.this);
                Intent intent = new Intent(PigdiceEntrance.this, PigdiceInstructions.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sounds.playButtonPress(PigdiceEntrance.this);
                final Intent intent = new Intent(PigdiceEntrance.this, ChooseGame.class);
                intent.putExtra("NameArr", getNamesArr);
                intent.putExtra("ScoresArr", getScores);
                startActivity(intent);
                finish();
            }
        });


    }
}
