package com.example.beni.diceolympics;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChooseGame extends AppCompatActivity {

    private MediaPlayer buttonClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        Button btnPlayPigDice = (Button) findViewById(R.id.chooseGame_btn_playPigDice);
        Button btnPlayMidnight = (Button) findViewById(R.id.chooseGame_btn_playMidnight);
        final ImageButton btnMute = (ImageButton) findViewById(R.id.chooseGame_btn_mute);

        if (Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);


        buttonClickSound = MediaPlayer.create(ChooseGame.this, R.raw.buttonpress);

        final String[] getNamesArr = getIntent().getStringArrayExtra("NameArr");
        final int[] getScores = getIntent().getIntArrayExtra("ScoresArr");

        btnPlayPigDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
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
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(ChooseGame.this, MidnightEntrance.class);
                intent.putExtra("NameArr", getNamesArr);
                intent.putExtra("ScoresArr", getScores);
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
            Intent intent = new Intent(ChooseGame.this, InputNames.class);
            startActivity(intent);
            finish();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
