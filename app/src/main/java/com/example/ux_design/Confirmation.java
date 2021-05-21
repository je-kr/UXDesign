package com.example.ux_design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Confirmation extends AppCompatActivity {
        TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        date = findViewById(R.id.textView23);

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();


        date.setText(selectedDate);
        date.setVisibility(View.VISIBLE);



    }
}