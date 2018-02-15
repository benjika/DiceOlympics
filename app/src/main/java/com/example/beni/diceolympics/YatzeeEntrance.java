package com.example.beni.diceolympics;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by luput on 02/12/2018.
 */

public class YatzeeEntrance extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yatzee_startmenu);

        Button StartGame = findViewById(R.id.YatzeeStartGameBTN);
        Button Instructions = findViewById(R.id.YatzeeInstructionsBTN);
        Button TopPlayer = findViewById(R.id.YatzeeTopPlayerBTN);
        Button Back = findViewById(R.id.YatzeeBackBTN);


        final ImageButton btnMute = (ImageButton) findViewById(R.id.app_btn_mute);
        if(Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);
        final MediaPlayer buttonClickSound = MediaPlayer.create(YatzeeEntrance.this, R.raw.buttonpress);

        Instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(YatzeeEntrance.this,YatzeeInstructions.class);
                startActivity(intent);
                finish();
            }
        });

        StartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(YatzeeEntrance.this,YatzeePlayScreen.class);
                startActivity(intent);
                finish();
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(YatzeeEntrance.this,AppEntrance.class);
                startActivity(intent);
                finish();
            }
        });

        TopPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(YatzeeEntrance.this,YatzeeTopPlayer.class);
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
