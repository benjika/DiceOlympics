package com.example.beni.diceolympics;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class AppEntrance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_entrance);

        Button btnOnePlayer = (Button) findViewById(R.id.app_btn_singlePlayers);
        Button btnTwoPlayers = (Button) findViewById(R.id.app_btn_twoPlayers);
        Button btnExitApp = (Button) findViewById(R.id.app_btn_exitApp);
        final ImageButton btnMute = (ImageButton) findViewById(R.id.app_btn_mute);

        //Gets the mute status and sets the button image by it
        if (Sounds.getIsMute()) btnMute.setImageResource(R.drawable.mute);
        else btnMute.setImageResource(R.drawable.unmute);

        final MediaPlayer buttonClickSound = MediaPlayer.create(AppEntrance.this, R.raw.buttonpress);

        btnOnePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(AppEntrance.this, YatzeeEntrance.class);
                startActivity(intent);
            }
        });

        btnTwoPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sounds.getIsMute()) buttonClickSound.start();
                Intent intent = new Intent(AppEntrance.this, InputNames.class);
                startActivity(intent);
            }
        });

        //Listener on the Exit App button
        btnExitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Sounds.getIsMute()) buttonClickSound.start();

                //A dialog to make sure the user wants to exit
                AlertDialog.Builder builder = new AlertDialog.Builder(AppEntrance.this);

                builder.setTitle("");
                builder.setMessage(R.string.areYouSureToExit);

                //If the user is sure to exit
                builder.setPositiveButton(R.string.yesWantToExit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (!Sounds.getIsMute()) buttonClickSound.start();
                        finish();
                        System.exit(0);
                    }
                });

                //If the user wants to stay
                builder.setNegativeButton(R.string.noWantToStay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (!Sounds.getIsMute()) buttonClickSound.start();
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //Listener on the Mute button
        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.buttonMutePress(btnMute);
            }
        });
    }


}
