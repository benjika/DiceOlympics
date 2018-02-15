package com.example.beni.diceolympics;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MidnightInstructions extends AppCompatActivity {

    MediaPlayer buttonClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midnight_instructions);

        final Button btnBack = (Button) findViewById(R.id.Midnight_Instructions_btn_Back);

        final ImageButton btnMute = (ImageButton) findViewById(R.id.Midnight_Instructions_btn_mute);
        if (Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);

        buttonClickSound = MediaPlayer.create(MidnightInstructions.this, R.raw.buttonpress);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(MidnightInstructions.this, MidnightEntrance.class);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            if (!Sounds.getIsMute()) buttonClickSound.start();

            final Intent intent = new Intent(this, MidnightEntrance.class);
            startActivity(intent);
            finish();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
