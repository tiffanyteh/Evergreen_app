package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Domain.Order;

public class OrderDetails extends AppCompatActivity {
    private Button done;
    private TextView name, phoneno, address, itemname, itemprice, itemamt, total;
    private Order object1;

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

        object1 = (Order)getIntent().getSerializableExtra("object1");

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderDetails.this,MyOrders.class);
                startActivity(i);
            }
        });

        name.setText("Name: " + object1.getName());
        phoneno.setText("Phone Number: " + object1.getPhoneno());
        address.setText("Address: " + object1.getAddress());
        itemamt.setText("Item Amount: " + object1.getAmount());
        itemname.setText("Item Name: " + object1.getTitle());
        itemprice.setText("Item Price(RM): " + object1.getPrice());
        total.setText("Total Price(RM): " + object1.getTotal());

    }
}
