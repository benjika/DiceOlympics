package com.example.beni.diceolympics;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PigdiceEntrance extends AppCompatActivity {

    private MediaPlayer buttonClickSound;

    private String[] getNamesArr;
    private int[] getScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pigdice_entrance);

        final Button btnStartGame = (Button) findViewById(R.id.pigdice_btn_Start);
        final Button btnInstructions = (Button) findViewById(R.id.pigdice_btn_Instructions);
        final ImageButton btnMute = (ImageButton) findViewById(R.id.Pigdice_entrance_btn_mute);
        if (Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);

        buttonClickSound = MediaPlayer.create(PigdiceEntrance.this, R.raw.buttonpress);

        getNamesArr = getIntent().getStringArrayExtra("NameArr");
        getScores = getIntent().getIntArrayExtra("ScoresArr");

        //Calls the Who's starting screen. From there it will go to Pigdice game screen
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(PigdiceEntrance.this, WhosStarting.class);
                intent.putExtra("NameArr", getNamesArr);
                intent.putExtra("ScoresArr", getScores);
                intent.putExtra("GameToPlay", "Pigdice");
                startActivity(intent);
                finish();
            }
        });

        //Calls Pigdice instructions screen
        btnInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(PigdiceEntrance.this, PigdiceInstructions.class);
                intent.putExtra("NameArr", getNamesArr);
                intent.putExtra("ScoresArr", getScores);
                startActivity(intent);
                finish();
            }
        });

        //Goes back to choose game screen
        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.buttonMutePress(btnMute);
            }
        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            if (!Sounds.getIsMute()) buttonClickSound.start();
            final Intent intent = new Intent(PigdiceEntrance.this, ChooseGame.class);
            intent.putExtra("NameArr", getNamesArr);
            intent.putExtra("ScoresArr", getScores);
            startActivity(intent);
            finish();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
