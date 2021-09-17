package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Domain.Common;
import Domain.User;

public class login extends AppCompatActivity {
    EditText edtName, edtPassword;
    ImageButton btnSignIn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtName = (EditText)findViewById(R.id.username);
        edtPassword = (EditText)findViewById(R.id.password);
        btnSignIn1 = (ImageButton)findViewById(R.id.loginbtn1);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(login.this);
                mDialog.setMessage("Please wait....");
                mDialog.show();

                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if user is signed up
                        if(dataSnapshot.child(edtName.getText().toString()).exists()) {

                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtName.getText().toString()).getValue(User.class);
                            user.setName(edtName.getText().toString());
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Intent onb1intent = new Intent(login.this,onboarding1.class);
                                Common.currentUser = user;
                                startActivity(onb1intent);
                                finish();
                                table_user.removeEventListener(this);
                                Toast.makeText(login.this, "Sign in successfully!",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(login.this, "Sign in failed!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(login.this, "The user does not exist",
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
}
