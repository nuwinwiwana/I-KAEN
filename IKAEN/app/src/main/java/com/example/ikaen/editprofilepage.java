package com.example.ikaen;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;

//disable this until complete update
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;

public class editprofilepage extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ikaen-a3973-default-rtdb.asia-southeast1.firebasedatabase.app/");
    String UID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid(); // edited
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //edited
    EditText profileFullName, profileEmail, profilePhone;
    ImageView profileImageView;
    FirebaseAuth fAuth;
    Button saveBtn, ChgPassBtn, ChgImgBtn;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofilepage);
        ChgPassBtn = findViewById(R.id.ChgPassBtn);
        ChgImgBtn = findViewById(R.id.ChgImgBtn);
        profileFullName = findViewById(R.id.fullnametv);
        profileEmail = findViewById(R.id.emailtv);
        profilePhone = findViewById(R.id.phoneTv);
        profileImageView = findViewById(R.id.imageView2);
        saveBtn = findViewById(R.id.saveBtn);
        fAuth = FirebaseAuth.getInstance();

        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
             Picasso.get().load(uri).into(profileImageView);
            }
        });



        //get data once
        databaseReference.child("users").child(UID).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                //Log.d("firebase", String.valueOf(task.getResult().getValue()));
                usermodel userData = Objects.requireNonNull(task.getResult()).getValue(usermodel.class);
                assert userData != null;
                profileFullName.setText(userData.getFullname());
                profileEmail.setText(userData.getemail());
                profilePhone.setText(userData.getPhone());
                //add get profile image example provided below
                //profilePicture.setImageURI(userData.getProfilePicture());
                //to use this, u need to modify usermodel class by adding this
                //private Uri profilePicture;
                //public usermodel(....., Uri profilePicture){
                //......;
                // this.profilePicture = profilePicture;
                //}
                //public Uri getProfilePicture(){ return profilePicture;}
            }
        });

        saveBtn.setOnClickListener(v -> {
            if (profileFullName.getText().toString().isEmpty() || profileEmail.getText().toString().isEmpty() || profilePhone.getText().toString().isEmpty()) {
                Toast.makeText(editprofilepage.this, "One or Many fields are empty.", Toast.LENGTH_SHORT).show();
                return;
            }

            final String email = profileEmail.getText().toString();
            user.updateEmail(email).addOnSuccessListener(unused -> {
                Toast.makeText(editprofilepage.this, "saved", Toast.LENGTH_SHORT).show();
                //update database after done update email
                databaseReference.child("users").child(user.getUid()).child("fullname").setValue(profileFullName.getText().toString());
                databaseReference.child("users").child(user.getUid()).child("email").setValue(email);
                databaseReference.child("users").child(user.getUid()).child("phone").setValue(profilePhone.getText().toString());
                //                Toast.makeText(editprofilepage.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                //                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                //                finish();
            }).addOnFailureListener(e -> Toast.makeText(editprofilepage.this, e.getMessage(), Toast.LENGTH_SHORT).show());
//                profileEmail.setText(email);
//                profileFullName.setText(fullName);
//                profilePhone.setText(phone);
//                Log.d(TAG, "onCreate: " + fullName + " " + email + " " + phone);
        });

        ChgImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
            }
        });

        ChgPassBtn.setOnClickListener(v -> {
            //password reset or change password?
            final EditText resetPassword = new EditText(v.getContext());
            final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle("Change Password?");
            passwordResetDialog.setMessage("Enter New Password");
            passwordResetDialog.setView(resetPassword);

            passwordResetDialog.setPositiveButton("Yes", (dialog, which) -> {
                //validation if empty input
                String newPassword = resetPassword.getText().toString();
                if(!newPassword.equals("")){
                    user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            databaseReference.child("users").child(user.getUid()).child("password").setValue(newPassword);
                            Toast.makeText(editprofilepage.this, "Password Changed Successfully.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(e -> Toast.makeText(editprofilepage.this, "Password Changed Failed.", Toast.LENGTH_SHORT).show());
                }else{
                    Toast.makeText(editprofilepage.this, "Password field empty!.", Toast.LENGTH_SHORT).show();
                }
            });

            passwordResetDialog.setNegativeButton("No", (dialog, which) -> {
                // close the dialog
            });

            passwordResetDialog.create().show();

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();

             //   profileImageView.setImageURI(imageUri);

                uploadImageToFirebase(imageUri);


            }
        }

    }

    private void uploadImageToFirebase(Uri imageUri) {
        //upload image to firebase storage
        StorageReference fileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(editprofilepage.this,"Image Upload.", Toast.LENGTH_SHORT).show();
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImageView);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(editprofilepage.this,"Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
