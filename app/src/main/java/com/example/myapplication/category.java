package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class category extends AppCompatActivity {

    ImageView backpress;
    private ImageView home, category, cart, profile;
    private TextView tree, cactus, flower, palmtree;
    private FloatingActionButton camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        backpress = findViewById(R.id.backpress);
        tree = (TextView)findViewById(R.id.tree);
        cactus = (TextView)findViewById(R.id.cactus);
        flower = (TextView)findViewById(R.id.flower);
        palmtree = (TextView)findViewById(R.id.palmtree);

        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(category.this, home.class);
                startActivity(i);
            }
        });

        tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(category.this, TreeList.class);
                startActivity(i);
            }
        });

        cactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(category.this, CactusList.class);
                startActivity(i);
            }
        });

        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(category.this, FlowerList.class);
                startActivity(i);
            }
        });

        palmtree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(category.this, PalmTreeList.class);
                startActivity(i);
            }
        });

        home = (ImageView) findViewById(R.id.imageView4);
        category = (ImageView) findViewById(R.id.imageView5);
        cart = (ImageView) findViewById(R.id.imageView10);
        profile = (ImageView) findViewById(R.id.imageView3);
        camera = (FloatingActionButton) findViewById(R.id.camera);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(category.this, home.class);
                startActivity(i);
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //nothing
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(category.this, Cart.class);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(category.this, profile.class);
                startActivity(i);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(category.this, ARList.class);
                startActivity(i);
            }
        });
    }
}
