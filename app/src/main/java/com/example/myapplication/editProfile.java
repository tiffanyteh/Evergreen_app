package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import Domain.Common;

public class editProfile extends AppCompatActivity {
    EditText username, phoneno, address;
    ImageButton updatebtn;
    ImageView pic;

    DatabaseReference reference, reference1;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        reference = FirebaseDatabase.getInstance().getReference("User");

        username = (EditText)findViewById(R.id.username);
        phoneno = (EditText)findViewById(R.id.phoneno);
        updatebtn = (ImageButton)findViewById(R.id.updatebtn);
        address = (EditText)findViewById(R.id.address);
        pic = (ImageView)findViewById(R.id.profilepic);

        username.setText(Common.currentUser.getName());
        phoneno.setText(Common.currentUser.getPhoneno());
        address.setText(Common.currentUser.getAddress());
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        reference1 = FirebaseDatabase.getInstance().getReference("User");

        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                String link = datasnapshot.child(firebaseUser.getUid()).child("pic").
                        getValue(String.class);
                Picasso.get().load(link).into(pic);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!Common.currentUser.getPhoneno().equals(phoneno.getText().toString())){
                    reference.child(firebaseUser.getUid()).child("phoneno").
                            setValue(phoneno.getText().toString());
                    Common.currentUser.setPhoneno(phoneno.getText().toString());
                }

                if(!Common.currentUser.getName().equals(username.getText().toString())){
                    reference.child(firebaseUser.getUid()).child("name").
                            setValue(username.getText().toString());
                    Common.currentUser.setPassword(username.getText().toString());

                }

                if(!Common.currentUser.getAddress().equals(address.getText().toString())){
                    reference.child(firebaseUser.getUid()).child("address").
                            setValue(address.getText().toString());
                    Common.currentUser.setAddress(address.getText().toString());
                }

                Toast.makeText(editProfile.this, "Profile Details Updated!",
                        Toast.LENGTH_SHORT).show();

                Intent i = new Intent(editProfile.this, profile.class);
                startActivity(i);
            }

        });

    }
}
