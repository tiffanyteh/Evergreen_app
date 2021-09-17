package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

public class SearchList extends AppCompatActivity {
    ImageView backpress1;
    private RecyclerView.Adapter adapter3;
    private RecyclerView recyler_list;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_plants);

        backpress1 = findViewById(R.id.backpress1);

        backpress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchList.this, home.class);
                startActivity(i);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyler_list = (RecyclerView) findViewById(R.id.recyclerView_list);
        recyler_list.setLayoutManager(linearLayoutManager);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_plant = database.getReference("PlantDomain").child("Category");

        table_plant.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<PlantDomain> tree = new ArrayList<>();

                for(DataSnapshot Acacia: dataSnapshot.child("Tree").getChildren()){
                    String title = Acacia.child("title").getValue(String.class);
                    String pic = Acacia.child("pic").getValue(String.class);
                    String description = Acacia.child("description").getValue(String.class);
                    Double price = Acacia.child("price").getValue(Double.class);
                    String width = Acacia.child("width").getValue(String.class);
                    String height = Acacia.child("height").getValue(String.class);
                    tree.add(new PlantDomain(title, pic, description, price, height, width));

                }

                for(DataSnapshot Acacia: dataSnapshot.child("Palm Tree").getChildren()){
                    String title = Acacia.child("title").getValue(String.class);
                    String pic = Acacia.child("pic").getValue(String.class);
                    String description = Acacia.child("description").getValue(String.class);
                    Double price = Acacia.child("price").getValue(Double.class);
                    String width = Acacia.child("width").getValue(String.class);
                    String height = Acacia.child("height").getValue(String.class);
                    tree.add(new PlantDomain(title, pic, description, price, height, width));

                }

                for(DataSnapshot Acacia: dataSnapshot.child("Flower").getChildren()){
                    String title = Acacia.child("title").getValue(String.class);
                    String pic = Acacia.child("pic").getValue(String.class);
                    String description = Acacia.child("description").getValue(String.class);
                    Double price = Acacia.child("price").getValue(Double.class);
                    String width = Acacia.child("width").getValue(String.class);
                    String height = Acacia.child("height").getValue(String.class);
                    tree.add(new PlantDomain(title, pic, description, price, height, width));

                }

                for(DataSnapshot Acacia: dataSnapshot.child("Cactus").getChildren()){
                    String title = Acacia.child("title").getValue(String.class);
                    String pic = Acacia.child("pic").getValue(String.class);
                    String description = Acacia.child("description").getValue(String.class);
                    Double price = Acacia.child("price").getValue(Double.class);
                    String width = Acacia.child("width").getValue(String.class);
                    String height = Acacia.child("height").getValue(String.class);
                    tree.add(new PlantDomain(title, pic, description, price, height, width));

                }

                int size1 = tree.size();

                ArrayList<PlantDomain> list = new ArrayList<>();
                for(int i=0; i<size1; i++){
                        if(tree.get(i).getTitle().toLowerCase().contains(value.toLowerCase())){
                            String title1 = tree.get(i).getTitle();
                            String pic1 = tree.get(i).getPic();
                            String description1 = tree.get(i).getDescription();
                            Double price1 = tree.get(i).getPrice();
                            String height1 = tree.get(i).getHeight();
                            String width1 = tree.get(i).getWidth();
                            list.add(new PlantDomain(title1, pic1, description1, price1, height1,
                                    width1));

                        }

                }

                if(list.size()==0){
                    Toast.makeText(SearchList.this, "No Plants Found", Toast.LENGTH_SHORT).show();
                }
                else{
                    adapter3= new ListAdapter(list);
                    recyler_list.setAdapter(adapter3);
                    adapter3.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }
}
