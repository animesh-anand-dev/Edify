package com.edify.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class StudentForm3Activity extends AppCompatActivity {

    private TextInputEditText std_hours, std_days, std_num_days, std_time_from, std_time_to;
    private Button std_form_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form3);

        std_hours = (TextInputEditText) findViewById(R.id.std_hours_input);
        std_days = (TextInputEditText) findViewById(R.id.std_days_input);
        std_num_days = (TextInputEditText) findViewById(R.id.std_no_days_input);
        std_time_from = (TextInputEditText) findViewById(R.id.std_time_from);
        std_time_to = (TextInputEditText) findViewById(R.id.std_time_to);
        std_form_submit = (Button) findViewById(R.id.stu_form_submit);
    }
}