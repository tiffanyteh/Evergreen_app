package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;
import com.craftman.cardform.Card;
import com.craftman.cardform.OnPayBtnClickListner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import Domain.Common;
import Domain.Order;

public class Payment extends AppCompatActivity {

    CardForm cardForm;
    Button buy;
    String title1="";
    String amount1="";
    String price1="";
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        com.craftman.cardform.CardForm cardForm = (com.craftman.cardform.CardForm)
                findViewById(R.id.cardform);
        TextView txtDes = (TextView)findViewById(R.id.payment_amount);
        Button btnPay = (Button)findViewById(R.id.btn_pay);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_cart = database.getReference("Cart").child("User").
                child(Common.currentUser.getName());

        table_cart.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Double> prices = new ArrayList<>();
                for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                    Double price = Acacia.child("price").getValue(Double.class);
                    prices.add(price);
                }
                int size= prices.size();
                Double total=0.0;
                for(int i=0; i<size;i++){
                    total = total + prices.get(i);
                }
                txtDes.setText("RM" + String.format("%.2f", total));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnPay.setText("Pay Now");
        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference table_cart = database.getReference("Cart").child("User").
                        child(Common.currentUser.getName());
                DatabaseReference table_cart1 = database.getReference("Cart").child("User").
                        child(Common.currentUser.getName());
                FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                final DatabaseReference table_order = database1.getReference("Order").child("User");


                table_cart.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i =0;
                        for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                            String title = Acacia.child("name").getValue(String.class);
                            int amount = Acacia.child("amount").getValue(Integer.class);
                            Double price = Acacia.child("price").getValue(Double.class);
                            if (i==0){
                                title1 = title1 + title;
                                amount1 = amount1 + amount;
                                price1 = price1 + String.format("%.2f", price);
                            }else{
                                title1 = title1 + "," + title;
                                amount1 = amount1 + "," + amount;
                                price1 = price1 + "," + String.format("%.2f", price);
                            }
                            i++;

                        }

                        table_order.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.child(Common.currentUser.getName()).exists()){
                                    String temp = txtDes.getText().toString();
                                    String total = temp.replace("RM","");
                                    calendar = Calendar.getInstance();
                                    dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
                                    date = dateFormat.format(calendar.getTime());
                                    Order order = new Order(Common.currentUser.getName(),title1,
                                            Double.parseDouble(total),
                                            amount1,price1,Common.currentUser.getAddress(),
                                            Common.currentUser.getPhoneno(), UUID.randomUUID().toString(),
                                            date);
                                    table_order.child(Common.currentUser.getName()).push().setValue(order);
                                    Toast.makeText(Payment.this, "Thank you for your payment!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Payment.this,Receipt.class);
                                    i.putExtra("object",order);
                                    startActivity(i);
                                }
                                else{
                                    calendar = Calendar.getInstance();
                                    dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
                                    date = dateFormat.format(calendar.getTime());
                                    String temp = txtDes.getText().toString();
                                    String total = temp.replace("RM","");
                                    Order order = new Order(Common.currentUser.getName(),title1,
                                            Double.parseDouble(total),
                                            amount1,price1,Common.currentUser.getAddress(),
                                            Common.currentUser.getPhoneno(),UUID.randomUUID().toString(),date);
                                    table_order.child(Common.currentUser.getName()).push().setValue(order);
                                    Toast.makeText(Payment.this, "Thank you for your payment!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Payment.this,Receipt.class);
                                    i.putExtra("object",order);
                                    startActivity(i);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                table_cart1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                            Acacia.child("amount").getRef().removeValue();
                            Acacia.child("name").getRef().removeValue();
                            Acacia.child("pic").getRef().removeValue();
                            Acacia.child("price").getRef().removeValue();
                            Acacia.child("oriprice").getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
