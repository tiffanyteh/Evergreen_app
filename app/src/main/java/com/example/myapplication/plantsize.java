package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import Domain.PlantDomain;

public class plantsize extends AppCompatActivity {

    private PlantDomain object1;
    private ImageView pic;
    private TextView title1, height, width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantsize);
        object1 = (PlantDomain) getIntent().getSerializableExtra("object");

        pic = (ImageView)findViewById(R.id.plantPic);
        title1 = (TextView)findViewById(R.id.titleTxt);
        height = (TextView)findViewById(R.id.height);
        width = (TextView)findViewById(R.id.width);

        int drawableResourceId = this.getResources().getIdentifier(object1.getPic(),
                "drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(pic);

        title1.setText(object1.getTitle());
        height.setText("Height: " + object1.getHeight());
        width.setText("Width: " + object1.getWidth());

    }
}
