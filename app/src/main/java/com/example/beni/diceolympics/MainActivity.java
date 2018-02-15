package com.example.beni.diceolympics;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Delays opening screen
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, AppEntrance.class);
                startActivity(intent);
                finish();
            }
        };

        Handler handler = new Handler();

        handler.postDelayed(r, 2000);
    }
}
