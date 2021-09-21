package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter.CartAdapter;
import Domain.CartItem;
import Domain.Common;

public class Cart extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    RecyclerView recyler_cart;
    Button btn_place_order;
    TextView totalprice;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL,false);
        recyler_cart = (RecyclerView)findViewById(R.id.recycler_cart);
        recyler_cart.setLayoutManager(linearLayoutManager);

        btn_place_order = (Button)findViewById(R.id.btn_place_order);
        totalprice = (TextView)findViewById(R.id.totalprice);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();

        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference table_user = database.getReference("User").
                        child(firebaseUser.getUid());
                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String title = dataSnapshot.child("address").getValue(String.class);
                        if(!title.isEmpty()){
                            Intent i = new Intent(Cart.this, Payment.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(Cart.this, "No Address Provided in Profile",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_cart = database.getReference("Cart").child("User").
                child(Common.currentUser.getName());

        table_cart.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<CartItem> cartItemList = new ArrayList<>();
                ArrayList<Double> prices = new ArrayList<>();
                for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                    String title = Acacia.child("name").getValue(String.class);
                    String pic = Acacia.child("pic").getValue(String.class);
                    int amount = Acacia.child("amount").getValue(Integer.class);
                    Double price = Acacia.child("price").getValue(Double.class);
                    Double initalp = price * amount;
                    prices.add(Double.valueOf(initalp));
                    cartItemList.add(new CartItem(title, price, amount, pic));
                }
                int size= prices.size();
                Double total=0.0;
                for(int i=0; i<size;i++){
                    total = total + prices.get(i);
                }

                totalprice.setText("Total Price: RM" + String.format("%.2f", total));

                adapter= new CartAdapter(cartItemList);
                recyler_cart.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
