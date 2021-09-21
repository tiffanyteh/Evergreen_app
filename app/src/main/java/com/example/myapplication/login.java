package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtName = (EditText)findViewById(R.id.username);
        edtPassword = (EditText)findViewById(R.id.password);
        btnSignIn1 = (ImageButton)findViewById(R.id.loginbtn1);

        firebaseAuth = FirebaseAuth.getInstance();


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

                        if(edtName.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()){
                            mDialog.dismiss();
                            Toast.makeText(login.this, "No email or password provided", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            mDialog.dismiss();
                            firebaseAuth.signInWithEmailAndPassword(edtName.getText().toString(), edtPassword.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                //direct to onboarding page
                                                FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
                                                Boolean emailflag = firebaseUser.isEmailVerified();

                                                if(emailflag){
                                                    User user = dataSnapshot.child(firebaseUser.getUid()).getValue(User.class);
                                                    user.setName(dataSnapshot.child(firebaseUser.getUid()).child("name").getValue(String.class));
                                                    Common.currentUser = user;
                                                    Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                    startActivity(new Intent(login.this, onboarding1.class));
                                                }
                                                else{
                                                    Toast.makeText(login.this, "Please verify your email", Toast.LENGTH_SHORT).show();
                                                    firebaseAuth.signOut();
                                                }



                                            }
                                            else{
                                                Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
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
