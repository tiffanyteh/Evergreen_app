package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import Domain.User;

public class signup extends AppCompatActivity {
    EditText edtPassword1, edtName;
    ImageButton btnSignUp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtPassword1 = (EditText)findViewById(R.id.password1);
        edtName = (EditText)findViewById(R.id.username1);
        btnSignUp1 = (ImageButton)findViewById(R.id.signupbtn1);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(signup.this);
                mDialog.setMessage("Please wait....");
                mDialog.show();

                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(edtName.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(signup.this, "Username already registered",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mDialog.dismiss();

                            User user = new User(edtName.getText().toString(),
                                    edtPassword1.getText().toString(),"","","",
                                    "https://firebasestorage.googleapis.com/v0/b/evergreen-af6a9.appspot.com/o/" +
                                            "images%2Fprofile1.png?alt=media&token=860e24e0-b23a-40c2-a3f9-423e7425a327");
                            table_user.child(edtName.getText().toString()).setValue(user);
                            Toast.makeText(signup.this, "Sign Up successfully!",
                                    Toast.LENGTH_SHORT).show();
                            finish();
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
