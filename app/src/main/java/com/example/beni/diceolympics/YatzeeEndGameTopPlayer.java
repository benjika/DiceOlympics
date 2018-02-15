package com.example.beni.diceolympics;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by luput on 02/13/2018.
 */

public class YatzeeEndGameTopPlayer extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yatzee_end_game_top_player);

        final Button Back = findViewById(R.id.Yatzee_End_Game_Top_Player_Back);
        final Button Save_New_High_Score = findViewById(R.id.Save_Best_Score_Name);
        final EditText Name = findViewById(R.id.Enter_New_Champ_Name);
        final TextView Score = findViewById(R.id.TopPlayerScore);
        final ImageButton btnMute = (ImageButton) findViewById(R.id.Yatzee_End_Game_Champ_mute);

        if(Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);
        final MediaPlayer buttonClickSound = MediaPlayer.create(YatzeeEndGameTopPlayer.this, R.raw.buttonpress);

        String Final_Score = getIntent().getStringExtra(YatzeePlayScreen.FINAL_SCORE);
        Score.setText(Final_Score);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(YatzeeEndGameTopPlayer.this,YatzeeEntrance.class);
                startActivity(intent);
                finish();
            }
        });

        Save_New_High_Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                boolean OK = true;
                String name = Name.getText().toString();
                char[] chars = name.toCharArray();
                for (char c : chars) if (!Character.isLetter(c)) OK = false;
                if(!OK){
                    Toast.makeText(YatzeeEndGameTopPlayer.this, R.string.IllegalInputForChamp, Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences sp = getSharedPreferences("details",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("user_name",name);
                    editor.putString("high_score",Score.getText().toString());
                    editor.commit();
                    Toast.makeText(YatzeeEndGameTopPlayer.this,R.string.Saved,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(YatzeeEndGameTopPlayer.this,YatzeeEntrance.class);
                    startActivity(intent);
                    finish();
                }
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
