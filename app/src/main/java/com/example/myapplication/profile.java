package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

import Domain.Common;

public class profile extends AppCompatActivity {

    private ImageView home, category, cart, profile;
    private FloatingActionButton camera;
    private TextView logout, edtprofile, name, myorders, wishlist, edtprofile2;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ImageView profilepic;
    DatabaseReference reference, reference1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        home = (ImageView) findViewById(R.id.imageView4);
        category = (ImageView) findViewById(R.id.imageView5);
        cart = (ImageView) findViewById(R.id.imageView10);
        profile = (ImageView) findViewById(R.id.imageView3);
        camera = (FloatingActionButton) findViewById(R.id.camera);
        logout = (TextView) findViewById(R.id.logout);
        edtprofile = (TextView)findViewById(R.id.edtprofile);
        name = (TextView)findViewById(R.id.name);
        wishlist = (TextView)findViewById(R.id.wishlist);
        myorders = (TextView)findViewById(R.id.myorders);
        edtprofile2 = (TextView)findViewById(R.id.edtprofile2);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        profilepic = (ImageView)findViewById(R.id.profilepic);
        reference1 = FirebaseDatabase.getInstance().getReference("User");

        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                String link = datasnapshot.child(Common.currentUser.getName()).child("pic").
                        getValue(String.class);
                Picasso.get().load(link).into(profilepic);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        name.setText(Common.currentUser.getName());

        edtprofile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });

        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(profile.this, MyOrders.class);
                startActivity(i);
            }
        });

        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(profile.this, Wishlist.class);
                startActivity(i);
            }
        });

        edtprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(profile.this, editProfile.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(profile.this, MainActivity.class);
                startActivity(i);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(profile.this, home.class);
                startActivity(i);
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(profile.this, category.class);
                startActivity(i);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(profile.this, Cart.class);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nothing
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(profile.this, ARList.class);
                startActivity(i);
            }
        });


    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            profilepic.setImageURI(imageUri);

            uploadPicture();
        }
    }

    private void uploadPicture(){
        final ProgressDialog pd = new ProgressDialog(this);

        pd.setTitle("Uploading Image...");
        pd.show();

        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                pd.dismiss();
                                reference = FirebaseDatabase.getInstance().getReference("User");
                                reference.child(Common.currentUser.getName()).child("pic").
                                        setValue(uri.toString());
                                Toast.makeText(profile.this, "Image Uploaded",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed To Upload",
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
