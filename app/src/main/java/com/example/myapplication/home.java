package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Adapter.CategoryAdapter;
import Adapter.PopularAdapter;
import Domain.CategoryDomain;
import Domain.Common;
import Domain.PlantDomain;

public class home extends AppCompatActivity {

    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    private ImageView home, category, cart, profile, searchicon, profilepic;
    private FloatingActionButton camera;
    private TextView name;
    private EditText searchplant;
    DatabaseReference reference1;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewCategory();
        recyclerViewPopular();

        home = (ImageView) findViewById(R.id.imageView4);
        category = (ImageView) findViewById(R.id.imageView5);
        cart = (ImageView) findViewById(R.id.imageView10);
        profile = (ImageView) findViewById(R.id.imageView3);
        camera = (FloatingActionButton) findViewById(R.id.camera);
        name = (TextView)findViewById(R.id.name);
        searchplant = (EditText)findViewById(R.id.searchplant);
        searchicon = (ImageView)findViewById(R.id.searchicon);
        profilepic = (ImageView)findViewById(R.id.profilepic);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        reference1 = FirebaseDatabase.getInstance().getReference("User");


        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                String link = datasnapshot.child(firebaseUser.getUid()).child("pic").
                        getValue(String.class);
                Picasso.get().load(link).into(profilepic);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = searchplant.getText().toString();
                Intent i = new Intent(home.this, SearchList.class);
                i.putExtra("key",value);
                startActivity(i);
            }
        });

        name.setText("Hi, "+ Common.currentUser.getName());

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nothing happen
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this, category.class);
                startActivity(i);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this, Cart.class);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this, profile.class);
                startActivity(i);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this, ARList.class);
                startActivity(i);
            }
        });
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_plant = database.getReference("PlantDomain").child("Category").
                child("Tree");

        table_plant.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<PlantDomain> plantlist = new ArrayList<>();

                    for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                        String title = Acacia.child("title").getValue(String.class);
                        String pic = Acacia.child("pic").getValue(String.class);
                        String description = Acacia.child("description").getValue(String.class);
                        Double price = Acacia.child("price").getValue(Double.class);
                        String width = Acacia.child("width").getValue(String.class);
                        String height = Acacia.child("height").getValue(String.class);
                        plantlist.add(new PlantDomain(title, pic,description,price, height, width));
                    }

                    adapter2= new PopularAdapter(plantlist);
                    recyclerViewPopularList.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Tree","cat_1"));
        categoryList.add(new CategoryDomain("Palm Tree","cat_2"));
        categoryList.add(new CategoryDomain("Flower","cat_3"));
        categoryList.add(new CategoryDomain("Cactus","cat_4"));

        adapter=new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);


    }
}

