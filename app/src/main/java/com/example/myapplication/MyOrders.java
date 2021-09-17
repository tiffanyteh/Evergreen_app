package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter.ListAdapter;
import Adapter.OrderAdapter;
import Domain.Common;
import Domain.Order;
import Domain.PlantDomain;

public class MyOrders extends AppCompatActivity {
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
                Intent i = new Intent(MyOrders.this, profile.class);
                startActivity(i);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL,false);
        recyler_list = (RecyclerView) findViewById(R.id.recyclerView_list);
        recyler_list.setLayoutManager(linearLayoutManager);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_order = database.getReference("Order").child("User").
                child(Common.currentUser.getName());

        table_order.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Order> orders = new ArrayList<>();

                for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                    String title = Acacia.child("title").getValue(String.class);
                    String price = Acacia.child("price").getValue(String.class);
                    String name = Acacia.child("name").getValue(String.class);
                    String phone = Acacia.child("phoneno").getValue(String.class);
                    String address = Acacia.child("address").getValue(String.class);
                    String amount = Acacia.child("amount").getValue(String.class);
                    Double total = Acacia.child("total").getValue(Double.class);
                    String orderid = Acacia.child("orderid").getValue(String.class);
                    orders.add(new Order(name, title, total, amount, price, address, phone,orderid));

                }

                adapter3= new OrderAdapter(orders);
                recyler_list.setAdapter(adapter3);
                adapter3.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
}
