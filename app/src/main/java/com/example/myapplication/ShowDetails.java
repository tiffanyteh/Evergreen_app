package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Domain.CartItem;
import Domain.Common;
import Domain.PlantDomain;

public class ShowDetails extends AppCompatActivity {

    private TextView addToCartBtn, share, wishlist;
    private TextView titleTxt,priceTxt,descriptionTxt,numberOrderTxt;
    private ImageView plusBtn, minusBtn, picPlant;
    private PlantDomain object;
    private int numberOrder =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        initView();
        getBundle();

    }

    private void getBundle() {

        object = (PlantDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picPlant);

        titleTxt.setText(object.getTitle());
        priceTxt.setText(object.getPrice().toString());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_wishlist = database.getReference("Wishlist").child("User");

        table_wishlist.child(Common.currentUser.getName()).
                addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                    String planttitle = Acacia.getValue(String.class);
                    if(planttitle.equals(titleTxt.getText().toString())){
                        wishlist.setText("Remove From WishList");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if item is already wishlisted
                String text = wishlist.getText().toString();

                if(text.equals("Remove From WishList")){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference table_wishlist1 = database.getReference("Wishlist").
                            child("User").child(Common.currentUser.getName());

                    table_wishlist1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                                String planttitle = Acacia.getValue(String.class);
                                if(planttitle.equals(titleTxt.getText().toString())){
                                    Acacia.getRef().removeValue();
                                    wishlist.setText("Add To WishList");
                                    Toast.makeText(ShowDetails.this, "Item Removed From Wishlist",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{
                    table_wishlist.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(Common.currentUser.getName()).exists()){
                                table_wishlist.child(Common.currentUser.getName()).push().
                                        setValue(titleTxt.getText().toString());
                                Toast.makeText(ShowDetails.this, "Added to Wishlist",
                                        Toast.LENGTH_SHORT).show();
                                wishlist.setText("Remove From WishList");
                            }
                            else{
                                table_wishlist.child(Common.currentUser.getName()).push().
                                        setValue(titleTxt.getText().toString());
                                Toast.makeText(ShowDetails.this, "Added to Wishlist",
                                        Toast.LENGTH_SHORT).show();
                                wishlist.setText("Remove From WishList");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Plant Name: " +
                        titleTxt.getText().toString()
                + "\n" + "Price: RM" + priceTxt.getText().toString() + "\n" + "Description: " +
                        descriptionTxt.getText().toString());
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder>1){
                    numberOrder = numberOrder - 1;
                    numberOrderTxt.setText(String.valueOf(numberOrder));
                }else{
                    Toast.makeText(ShowDetails.this, "Item quantity must be more than 1", Toast.LENGTH_SHORT).show();
                }

            }
        });

        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        final DatabaseReference table_cart = database1.getReference("Cart").child("User");

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_cart.child(Common.currentUser.getName()).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int count = 0;
                        if(dataSnapshot.exists()){
                            for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                                String planttitle = Acacia.child("name").getValue(String.class);
                                if(planttitle.equals(titleTxt.getText().toString())){
                                    Acacia.getRef().removeValue();
                                    Double subprice = Double.parseDouble(priceTxt.getText().toString())
                                            * Integer.parseInt(numberOrderTxt.getText().toString());

                                    CartItem cartItem = new CartItem(titleTxt.getText().toString(),
                                            subprice,
                                            Integer.parseInt(numberOrderTxt.getText().toString()),
                                            object.getPic(), Double.parseDouble(priceTxt.getText().toString()));
                                    table_cart.child(Common.currentUser.getName()).push().setValue(cartItem);
                                    count = count + 1;
                                    Toast.makeText(ShowDetails.this, "Item Added to Cart!",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            if(count<1){
                                Double subprice = Double.parseDouble(priceTxt.getText().toString())
                                        * Integer.parseInt(numberOrderTxt.getText().toString());
                                CartItem cartItem = new CartItem(titleTxt.getText().toString(),
                                        subprice,
                                        Integer.parseInt(numberOrderTxt.getText().toString()),
                                        object.getPic(), Double.parseDouble(priceTxt.getText().toString()));
                                table_cart.child(Common.currentUser.getName()).push().setValue(cartItem);
                                Toast.makeText(ShowDetails.this, "Item Added to Cart!",
                                        Toast.LENGTH_SHORT).show();
                            }


                        }
                        else{
                            Double subprice = Double.parseDouble(priceTxt.getText().toString())
                                    * Integer.parseInt(numberOrderTxt.getText().toString());
                            CartItem cartItem = new CartItem(titleTxt.getText().toString(),
                                    subprice,
                                    Integer.parseInt(numberOrderTxt.getText().toString()),
                                    object.getPic(),Double.parseDouble(priceTxt.getText().toString()));
                            table_cart.child(Common.currentUser.getName()).push().setValue(cartItem);
                            Toast.makeText(ShowDetails.this, "Item Added to Cart!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        priceTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.addBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picPlant = findViewById(R.id.plantPic);
        wishlist = findViewById(R.id.wishlist);
        share = findViewById(R.id.share);
    }

}
