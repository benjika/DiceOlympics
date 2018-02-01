package com.example.beni.diceolympics;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AppEntrance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_entrance);

        Button btnOnePlayer = (Button) findViewById(R.id.app_btn_singlePlayers);
        Button btnTwoPlayers = (Button) findViewById(R.id.app_btn_twoPlayers);
        Button btnExitApp = (Button) findViewById(R.id.app_btn_exitApp);

        final MediaPlayer buttonClickSound = MediaPlayer.create(AppEntrance.this , R.raw.buttonpress);

        btnOnePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickSound.start();
                /*Intent intent = new Intent(AppEntrance.this, NewGameActivity .class);
                startActivity(intent);*/
                Toast.makeText(AppEntrance.this,"Will work",Toast.LENGTH_SHORT).show();
            }
        });

        btnTwoPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             buttonClickSound.start();
               Intent intent = new Intent(AppEntrance.this, InputNames.class);
                startActivity(intent);
            }
        });

        btnExitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickSound.start();
                final AlertDialog.Builder builder = new AlertDialog.Builder(AppEntrance.this);
                builder.setTitle("");
                builder.setMessage(R.string.areYouSureToExit);

                builder.setPositiveButton(R.string.yesWantToExit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        buttonClickSound.start();
                        finish();
                        System.exit(0);
                    }
                });

                builder.setNegativeButton(R.string.noWantToStay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        buttonClickSound.start();
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


}
