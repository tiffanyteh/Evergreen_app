package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Domain.Order;

public class Receipt extends AppCompatActivity {

    private Button done;
    private TextView name, phoneno, address, itemname, itemprice, itemamt, total;
    private Order object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        done = (Button)findViewById(R.id.btn_done);
        name = (TextView)findViewById(R.id.ordername);
        phoneno = (TextView)findViewById(R.id.orderphone);
        address = (TextView)findViewById(R.id.orderaddress);
        itemname = (TextView)findViewById(R.id.orderitem);
        itemprice = (TextView)findViewById(R.id.orderitemprice);
        itemamt = (TextView)findViewById(R.id.orderitemamt);
        total = (TextView)findViewById(R.id.orderitemtotal);

        object = (Order)getIntent().getSerializableExtra("object");

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Receipt.this,home.class);
                startActivity(i);
            }
        });

        name.setText("Name: " + object.getName());
        phoneno.setText("Phone Number: " + object.getPhoneno());
        address.setText("Address: " + object.getAddress());
        itemamt.setText("Item Amount: " + object.getAmount());
        itemname.setText("Item Name: " + object.getTitle());
        itemprice.setText("Item Price(RM): " + object.getPrice());
        total.setText("Total Price(RM): " + object.getTotal());

    }
}
