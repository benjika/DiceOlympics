package com.example.beni.diceolympics;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by luput on 02/12/2018.
 */

public class YatzeeTopPlayer extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yatzee_top_ten);

        TextView Name = findViewById(R.id.TopPlayer);
        TextView Score = findViewById(R.id.TopPlayerScore);
        Button Back = findViewById(R.id.Top_Playes_Show_Back);
        final ImageButton btnMute = (ImageButton) findViewById(R.id.Yatzee_Top_Player_mute);
        if(Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);
        final MediaPlayer buttonClickSound = MediaPlayer.create(YatzeeTopPlayer.this, R.raw.buttonpress);

        SharedPreferences sp = getSharedPreferences("details",MODE_PRIVATE);
        String userName  = sp.getString("user_name","");
        Name.setText(userName);
        String score = sp.getString("high_score","");
        Score.setText(score);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(YatzeeTopPlayer.this,YatzeeEntrance.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
