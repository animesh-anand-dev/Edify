package com.edify.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class PhoneAuthenticationActivity extends AppCompatActivity {
    TextInputEditText textInputEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_authentication);

        textInputEditText=(TextInputEditText)findViewById(R.id.text_input_editText);

        textInputEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (textInputEditText.getText().length()>=10)
                    Toast.makeText(PhoneAuthenticationActivity.this, "No More!", Toast.LENGTH_SHORT).show();
                    return  false;
            }
        });

    }
}