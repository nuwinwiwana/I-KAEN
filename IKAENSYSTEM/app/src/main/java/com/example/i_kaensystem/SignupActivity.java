package com.example.i_kaensystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

     private FirebaseAuth firebaseAuth;
     private EditText etName, etUsername, etEmail, etPassword, etConfirmPassword;
     private Button btRegister;
    final int MIN_PASSWORD_LENGTH = 6;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        viewInitializations();

        firebaseAuth = firebaseAuth.getInstance();

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    String et_Email= etEmail.getText().toString().trim();
                    String et_Password = etPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(et_Email, et_Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                            }else{
                                Toast.makeText(SignupActivity.this, "Sign Up unsuccessful", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

            }
        }
        });

    }

    private boolean validate() {
        boolean result = false;

        String name = etName.getText().toString();
        String Password = etPassword.getText().toString();
        String email = etEmail.getText().toString();

        if (name.isEmpty() && Password.isEmpty() && email.isEmpty()){
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    private void viewInitializations() {
        etName = findViewById(R.id.et_name);
        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btRegister = findViewById(R.id.bt_register);



        // To show back button in actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    // Checking if the input in form is valid
    private boolean validateInput() {
        if (etName.getText().toString().equals("")) {
            etName.setError("Please Enter First Name");
            return false;
        }
        if (etUsername.getText().toString().equals("")) {
            etUsername.setError("Please Enter Last Name");
            return false;
        }
        if (etEmail.getText().toString().equals("")) {
            etEmail.setError("Please Enter Email");
            return false;
        }
        if (etPassword.getText().toString().equals("")) {
            etPassword.setError("Please Enter Password");
            return false;
        }
        if (etConfirmPassword.getText().toString().equals("")) {
            etConfirmPassword.setError("Please Enter Confirm Password");
            return false;
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.getText().toString())) {
            etEmail.setError("Please Enter Valid Email");
            return false;
        }

        // checking minimum password Length
        if (etPassword.getText().length() < MIN_PASSWORD_LENGTH) {
            etPassword.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters");
            return false;
        }

        // Checking if repeat password is same
        if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
            etConfirmPassword.setError("Password does not match");
            return false;
        }
        return true;
    }

    boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Hook Click Event

    public void performSignUp (View v) {
        if (validateInput()) {

            // Input is valid, here send data to your server

            String Name = etName.getText().toString();
            String lasName = etUsername.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String repeatPassword = etConfirmPassword.getText().toString();
            Toast.makeText(this,"Sign Up Success",Toast.LENGTH_SHORT).show();
            // Here you can call you API

        }
    }

}
