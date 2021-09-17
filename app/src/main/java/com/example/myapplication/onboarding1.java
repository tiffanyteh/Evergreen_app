package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class onboarding1 extends AppCompatActivity {

    ImageButton nextbtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding1);
        nextbtn1 = (ImageButton)findViewById(R.id.nextbtn);

        nextbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(onboarding1.this, onboarding2.class);

                startActivity(i);
            }
        });
    }
}
