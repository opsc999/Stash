package com.example.stashtask2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

    Dialog categoryDialog;
    LinearLayoutCompat categoryCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryDialog = new Dialog(this);
    }

    public void ShowCategoryPopup(View v)
    {
        categoryCards = findViewById(R.id.categoryCards);

        EditText etCatName;
        EditText etCatGoal;
        TextView txtCloseCategory;
        CardView btnCreateCategory;
        categoryDialog.setContentView(R.layout.pop_category);
        txtCloseCategory = (TextView) categoryDialog.findViewById(R.id.txtCloseCategory);
        btnCreateCategory = (CardView) categoryDialog.findViewById(R.id.btnCreateCategory);
        etCatName = (EditText) categoryDialog.findViewById(R.id.etCatName);
        etCatGoal = (EditText) categoryDialog.findViewById(R.id.etCatGoal);

        txtCloseCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryDialog.dismiss();
            }
        });

        btnCreateCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardView newCard = new CardView(CategoryActivity.this);
                getLayoutInflater().inflate(R.layout.category_cardview, newCard);

                TextView t = newCard.findViewById(R.id.catNameText);
                TextView t2 = newCard.findViewById(R.id.catGoalText);

                String current = etCatName.getText().toString();
                String current2 = etCatGoal.getText().toString();

                t.setText(current);
                t2.setText(current2);
                newCard.setTag(current);
                newCard.setTag(current2);

                categoryCards.addView(newCard);

                categoryDialog.dismiss();
            }
        });

        categoryDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        categoryDialog.show();
    }

}