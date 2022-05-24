package com.example.stashtask2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Dialog collectionDialog;
    LinearLayoutCompat cards;

    // initialise Firebase authentication
    FirebaseAuth FirebaseAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collectionDialog = new Dialog(this);

        FirebaseAuthentication = FirebaseAuth.getInstance();
    }

    public void ShowCollectionPopup(View v)
    {
        cards = findViewById(R.id.cards);

        EditText etColName;
        TextView txtClose;
        CardView btnCreateCollection;
        collectionDialog.setContentView(R.layout.pop_collection);
        txtClose = (TextView) collectionDialog.findViewById(R.id.txtClose);
        btnCreateCollection = (CardView) collectionDialog.findViewById(R.id.btnCreateCollection);
        etColName = (EditText) collectionDialog.findViewById(R.id.etColName);

        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collectionDialog.dismiss();
            }
        });

        btnCreateCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardView newCard = new CardView(MainActivity.this);
                getLayoutInflater().inflate(R.layout.collection_cardview, newCard);

                TextView t = newCard.findViewById(R.id.colNameText);

                String current = etColName.getText().toString();

                t.setText(current);
                newCard.setTag(current);

                cards.addView(newCard);

                collectionDialog.dismiss();
            }
        });

        collectionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        collectionDialog.show();
    }

    // overriding the onStart method if a user is already logged in
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuthentication.getCurrentUser();
        if (user == null)
        {
            //Take the user from the LoginActivity to the MainActivity
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

}