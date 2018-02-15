package com.example.beni.diceolympics;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by luput on 02/13/2018.
 */

public class YatzeeEndGame extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yatzee_end_game);

        TextView Your_Score = (TextView) findViewById(R.id.PlayerScore);
        String Final_Score = getIntent().getStringExtra(YatzeePlayScreen.FINAL_SCORE);
        Your_Score.setText(String.valueOf(Final_Score));
        final ImageButton btnMute = (ImageButton) findViewById(R.id.yatzee_End_Game_Mute);
        if (Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);
        final MediaPlayer buttonClickSound = MediaPlayer.create(YatzeeEndGame.this, R.raw.buttonpress);

        Button Back_BTN = findViewById(R.id.Yatzee_End_Game_Back);
        Back_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(YatzeeEndGame.this, YatzeeEntrance.class);
                startActivity(intent);
                finish();
            }
        });

        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.buttonMutePress(btnMute);
            }
        });
    }
}
