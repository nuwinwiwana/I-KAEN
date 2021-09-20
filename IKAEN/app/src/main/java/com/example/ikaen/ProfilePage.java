package com.example.ikaen;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;


public class ProfilePage extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ikaen-a3973-default-rtdb.asia-southeast1.firebasedatabase.app/");
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    String UID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    FirebaseAuth fAuth;
    TextView fullname;
    TextView email;
    TextView phone;
    ImageView profileImageView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        fullname = findViewById(R.id.fullnametv);
        email = findViewById(R.id.emailtv);
        phone = findViewById(R.id.phoneTv);
        profileImageView = findViewById(R.id.imageView2);
        getprofile();
        fAuth = FirebaseAuth.getInstance();
        final Button editBtn = findViewById(R.id.editbtn);


        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImageView);
            }
        });


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this, editprofilepage.class));
            }
        });


    }


    public void getprofile() {
        //get data once
        databaseReference.child("users").child(UID).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                //Log.d("firebase", String.valueOf(task.getResult().getValue()));
                usermodel userData = Objects.requireNonNull(task.getResult()).getValue(usermodel.class);
                assert userData != null;
                fullname.setText(userData.getFullname());
                email.setText(userData.getemail());
                phone.setText(userData.getPhone());
                //add get profile image example provided below
                //profileImageView.setImageURI(userData.getProfilePicture());
                //to use this, u need to modify usermodel class by adding this
                //private Uri profilePicture;
                //public usermodel(....., Uri profilePicture){
                //......;
                // this.profilePicture = profilePicture;
                //}
                //public Uri getProfilePicture(){ return profilePicture;}
            }
        });

        DatabaseReference myRef = database.getReference("users/" + UID);


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                usermodel user = dataSnapshot.getValue(usermodel.class);
                assert user != null;
                fullname.setText(user.getFullname());
                email.setText(user.getemail());
                phone.setText(user.getPhone());
                Log.d(TAG, "Value is: " + user.getFullname());
                Log.d(TAG, "Value is: " + user.getemail());
                Log.d(TAG, "Value is: " + user.getPhone());
                Log.d(TAG, "Value is: " + user.getPassword());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}