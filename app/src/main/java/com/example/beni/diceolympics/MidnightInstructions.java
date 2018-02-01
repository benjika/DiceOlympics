package com.example.beni.diceolympics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MidnightInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midnight_instructions);

        final Button btnBack = (Button) findViewById(R.id.Midnight_Instructions_btn_Back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MidnightInstructions.this, MidnightEntrance.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
