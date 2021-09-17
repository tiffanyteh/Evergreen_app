package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter.ListAdapter;
import Domain.PlantDomain;

import static androidx.constraintlayout.widget.StateSet.TAG;

public class TreeList extends AppCompatActivity {
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
                Intent i = new Intent(TreeList.this, home.class);
                startActivity(i);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL,false);
        recyler_list = (RecyclerView) findViewById(R.id.recyclerView_list);
        recyler_list.setLayoutManager(linearLayoutManager);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_plant = database.getReference("PlantDomain").child("Category").
                child("Tree");

        table_plant.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<PlantDomain> tree = new ArrayList<>();

                for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                    String title = Acacia.child("title").getValue(String.class);
                    String pic = Acacia.child("pic").getValue(String.class);
                    String description = Acacia.child("description").getValue(String.class);
                    Double price = Acacia.child("price").getValue(Double.class);
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
