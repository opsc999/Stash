package com.example.stashtask2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

public class LoginActivity extends AppCompatActivity {

    //Declare btnLogin as private variable of type CardView
    private CardView btnLogin;
    //Declare txtRegister as private variable of type TextView
    private TextView txtRegister;

    private ShapeableImageView btnFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Set btnLogin to its corresponding CardView in activity_login.xml
        btnLogin = findViewById(R.id.btnLogin);
        //Set txtRegister to its corresponding TextView in activity_login.xml
        txtRegister = findViewById(R.id.tvRegister);



        btnFacebook = findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CategoryActivity.class));
            }
        });



        //Set an onClickListener when btnLogin is clicked
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Take the user from the LoginActivity to the MainActivity
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
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
}