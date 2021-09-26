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

import Domain.User;

public class signup extends AppCompatActivity {
    EditText edtPassword1, edtName, edtEmail;
    ImageButton btnSignUp1;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        edtPassword1 = (EditText)findViewById(R.id.password1);
        edtName = (EditText)findViewById(R.id.username1);
        btnSignUp1 = (ImageButton)findViewById(R.id.signupbtn1);
        edtEmail = (EditText)findViewById(R.id.email1);

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

                        if(edtName.getText().toString().isEmpty() || edtEmail.getText().toString().isEmpty() ||
                        edtPassword1.getText().toString().isEmpty()){
                            mDialog.dismiss();
                            Toast.makeText(signup.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mDialog.dismiss();

                            //upload data to firebase authentication
                            String user_email = edtEmail.getText().toString().trim();
                            String user_password = edtPassword1.getText().toString().trim();

                            firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).
                                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                        if(firebaseUser!=null){
                                            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        User user = new User(edtName.getText().toString(),
                                                                edtEmail.getText().toString(),"","",
                                                                "https://firebasestorage.googleapis.com/v0/b/evergreen-af6a9.appspot.com/o/" +
                                                                        "images%2Fprofile1.png?alt=media&token=860e24e0-b23a-40c2-a3f9-423e7425a327");
                                                        table_user.child(firebaseUser.getUid()).setValue(user);
                                                        Toast.makeText(signup.this, "Successfully Sign Up, Verification Email Sent", Toast.LENGTH_SHORT).show();
                                                        firebaseAuth.signOut();
                                                    }
                                                    else{
                                                        Toast.makeText(signup.this, "Verification Email Not Sent", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                    else{
                                        Toast.makeText(signup.this, "Sign Up Failed",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

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
