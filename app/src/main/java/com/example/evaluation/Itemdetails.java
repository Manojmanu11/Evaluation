package com.example.evaluation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Itemdetails extends AppCompatActivity {
    private TextView itemDescription;
    CategoriesFragment categoriesFragment;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdetails);
        itemDescription=findViewById(R.id.item_Description);

    }
}