package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class onboarding2 extends AppCompatActivity {
    ImageButton nextbtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding2);
        nextbtn2 = (ImageButton)findViewById(R.id.nextbtn1);

        nextbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(onboarding2.this, home.class);

                startActivity(i);
            }
        });
    }
}
