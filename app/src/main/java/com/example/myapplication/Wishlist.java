package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter.WishListAdapter;
import Domain.Common;
import Domain.PlantDomain;

public class Wishlist extends AppCompatActivity {

    ImageView backpress1;
    private RecyclerView.Adapter adapter3;
    private RecyclerView recyler_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_plants);

        backpress1 = findViewById(R.id.backpress1);

        backpress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Wishlist.this, profile.class);
                startActivity(i);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL,false);
        recyler_list = (RecyclerView) findViewById(R.id.recyclerView_list);
        recyler_list.setLayoutManager(linearLayoutManager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_plant = database.getReference("PlantDomain").child("Category");

        table_plant.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<PlantDomain> plants = new ArrayList<>();

                for(DataSnapshot Acacia: dataSnapshot.child("Tree").getChildren()){
                    String title = Acacia.child("title").getValue(String.class);
                    String pic = Acacia.child("pic").getValue(String.class);
                    String description = Acacia.child("description").getValue(String.class);
                    Double price = Acacia.child("price").getValue(Double.class);
                    String width = Acacia.child("width").getValue(String.class);
                    String height = Acacia.child("height").getValue(String.class);
                    plants.add(new PlantDomain(title, pic, description, price, height, width));

                }

                for(DataSnapshot Acacia: dataSnapshot.child("Palm Tree").getChildren()){
                    String title = Acacia.child("title").getValue(String.class);
                    String pic = Acacia.child("pic").getValue(String.class);
                    String description = Acacia.child("description").getValue(String.class);
                    Double price = Acacia.child("price").getValue(Double.class);
                    String width = Acacia.child("width").getValue(String.class);
                    String height = Acacia.child("height").getValue(String.class);
                    plants.add(new PlantDomain(title, pic, description, price, height, width));

                }

                for(DataSnapshot Acacia: dataSnapshot.child("Flower").getChildren()){
                    String title = Acacia.child("title").getValue(String.class);
                    String pic = Acacia.child("pic").getValue(String.class);
                    String description = Acacia.child("description").getValue(String.class);
                    Double price = Acacia.child("price").getValue(Double.class);
                    String width = Acacia.child("width").getValue(String.class);
                    String height = Acacia.child("height").getValue(String.class);
                    plants.add(new PlantDomain(title, pic, description, price, height, width));

                }

                for(DataSnapshot Acacia: dataSnapshot.child("Cactus").getChildren()){
                    String title = Acacia.child("title").getValue(String.class);
                    String pic = Acacia.child("pic").getValue(String.class);
                    String description = Acacia.child("description").getValue(String.class);
                    Double price = Acacia.child("price").getValue(Double.class);
                    String width = Acacia.child("width").getValue(String.class);
                    String height = Acacia.child("height").getValue(String.class);
                    plants.add(new PlantDomain(title, pic, description, price, height, width));

                }

                wishlist(plants);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }


    public void wishlist(ArrayList <PlantDomain> plants){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_wishlist = database.getReference("Wishlist").child("User").
                child(Common.currentUser.getName());
        table_wishlist.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> title = new ArrayList<>();
                for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                    String planttitle = Acacia.getValue(String.class);
                    title.add(planttitle);
                }

                int size1 = plants.size();
                int size = title.size();


                ArrayList<PlantDomain> list = new ArrayList<>();
                for(int i=0; i<size1; i++){
                    for(int r=0; r<size; r++){
                        if(plants.get(i).getTitle().equals(title.get(r))){
                            String title1 = plants.get(i).getTitle();
                            String pic1 = plants.get(i).getPic();
                            String description1 = plants.get(i).getDescription();
                            Double price1 = plants.get(i).getPrice();
                            String height1 = plants.get(i).getHeight();
                            String width1 = plants.get(i).getWidth();
                            list.add(new PlantDomain(title1, pic1, description1, price1,
                                    height1, width1));

                        }
                    }

                }

                adapter3= new WishListAdapter(list);
                recyler_list.setAdapter(adapter3);
                adapter3.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

}
