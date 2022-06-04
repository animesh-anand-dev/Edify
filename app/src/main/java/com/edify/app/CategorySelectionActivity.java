package com.edify.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CategorySelectionActivity extends AppCompatActivity {
    CardView teacher, student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);
        teacher = (CardView) findViewById(R.id.teacher);
        student= (CardView) findViewById(R.id.student);
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelectionActivity.this, PhoneAuthenticationActivity.class);
                startActivity(intent);

            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelectionActivity.this, OtpVerifyActivity.class);
                startActivity(intent);

            }
        });
    }
}