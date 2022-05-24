package com.example.stashtask2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private ImageButton btnRegBack;
    private TextView txtRegister;
    private EditText name, uEmail, uPassword, confirmedPassword;
    // initialise Firebase authentication
    private FirebaseAuth FirebaseAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegBack = findViewById(R.id.btnRegBack);
        txtRegister = findViewById(R.id.txtLogin);
        name = findViewById(R.id.etName);
        uEmail = findViewById(R.id.etEmail);
        uPassword = findViewById(R.id.etPassword);
        confirmedPassword = findViewById(R.id.etConfirmPassword);
        // initialise Firebase authentication
        FirebaseAuthentication = FirebaseAuth.getInstance();

        btnRegBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validation of a new user
                validateNewUser();

            }
        });
    }

    private void validateNewUser() {
        String userName = name.getText().toString().trim();
        String email = uEmail.getText().toString().trim();
        String password = uPassword.getText().toString().trim();
        String verifiedPassword = confirmedPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userName))
        {
            name.setError("Name cannot be empty");
            name.requestFocus();
        }
        else if (TextUtils.isEmpty(email) || (!Patterns.EMAIL_ADDRESS.matcher(email).matches()))
        {
            uEmail.setError("Invalid email");
            uEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password))
        {
            uPassword.setError("Password cannot be empty");
            uPassword.requestFocus();
        }
        else if (TextUtils.isEmpty(verifiedPassword))
        {
            confirmedPassword.setError("Password cannot be empty");
            confirmedPassword.requestFocus();
        }
        else if (!password.equals(verifiedPassword))
        {
            uPassword.setError("Passwords do not match");
            uPassword.requestFocus();
        }
        else
        {
            createNewUser();
        }
    }

    private void createNewUser() {
        String email = uEmail.getText().toString().trim();
        String password = uPassword.getText().toString().trim();
        // creating user in firebase authentication
        FirebaseAuthentication.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // add new user to firebase
                        addNewUser();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // account creation failed
                        Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        }

    private void addNewUser() {
        // current uid
        String uid = FirebaseAuthentication.getUid();

        // setting data in database
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Name", name);
        hashMap.put("Email", uEmail);
        hashMap.put("Password", uPassword);
        hashMap.put("Confirmed password", confirmedPassword);

        // setting data to database
        DatabaseReference StashDatabase = FirebaseDatabase.getInstance().getReference("Users");
        StashDatabase.child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // add data to database
                        Toast.makeText(RegisterActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                        // allow user to login on successful account creation
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // data failed to add
                        Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
