package com.example.beni.diceolympics;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by luput on 02/12/2018.
 */

public class YatzeeInstructions extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yatzee_insructions);

        final Button btnBack=(Button)findViewById(R.id.Yatzee_Instructions_btn_Back);

        final ImageButton btnMute = (ImageButton) findViewById(R.id.Yatzee_Instructions_btn_mute);
        if(Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);

        final MediaPlayer buttonClickSound = MediaPlayer.create(YatzeeInstructions.this, R.raw.buttonpress);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(YatzeeInstructions.this, YatzeeEntrance.class);
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
