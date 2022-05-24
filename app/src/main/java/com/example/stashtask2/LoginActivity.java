package com.example.stashtask2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    //Declare btnLogin as private variable of type CardView
    private CardView btnLogin;
    //Declare txtRegister as private variable of type TextView
    private TextView txtRegister;

    private ShapeableImageView btnFacebook;

    private EditText edtEmail, edtPassword;
    // initialise Firebase authentication
    FirebaseAuth FirebaseAuthentication;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference stashDatabase = database.getReference("message");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Set btnLogin to its corresponding CardView in activity_login.xml
        btnLogin = findViewById(R.id.btnLogin);
        //Set txtRegister to its corresponding TextView in activity_login.xml
        txtRegister = findViewById(R.id.tvRegister);

        // initialise components
        edtEmail = findViewById(R.id.etEmail);
        edtPassword = findViewById(R.id.etPassword);

        // initialise Firebase authentication
        FirebaseAuthentication = FirebaseAuth.getInstance();

        //Set an onClickListener when btnLogin is clicked
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        btnFacebook = findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CategoryActivity.class));
            }
        });

        //Set an onClickListener when txtRegister is clicked
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Take the user from the LoginActivity to the RegisterActivity
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void userLogin(){
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Email cannot be empty");
            edtEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Password cannot be empty");
            edtPassword.requestFocus();
        } else {
            FirebaseAuthentication.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in successful
                        FirebaseUser user = FirebaseAuthentication.getCurrentUser();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        Toast.makeText(LoginActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}