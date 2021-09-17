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

import Adapter.ListAdapter;
import Domain.PlantDomain;

public class FlowerList extends AppCompatActivity {
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
                Intent i = new Intent(FlowerList.this, home.class);
                startActivity(i);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyler_list = (RecyclerView) findViewById(R.id.recyclerView_list);
        recyler_list.setLayoutManager(linearLayoutManager);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_plant = database.getReference("PlantDomain").child("Category").child("Flower");

        table_plant.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<PlantDomain> tree = new ArrayList<>();

                for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                    String title = Acacia.child("title").getValue(String.class);
                    String pic = Acacia.child("pic").getValue(String.class);
                    Double price = Acacia.child("price").getValue(Double.class);
                    String description = Acacia.child("description").getValue(String.class);
                    String width = Acacia.child("width").getValue(String.class);
                    String height = Acacia.child("height").getValue(String.class);
                    tree.add(new PlantDomain(title, pic, description, price, height, width));

                }

                adapter3= new ListAdapter(tree);
                recyler_list.setAdapter(adapter3);
                adapter3.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }
}
