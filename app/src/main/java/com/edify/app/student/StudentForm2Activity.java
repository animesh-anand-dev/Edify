package com.edify.app.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.edify.app.R;
import com.google.android.material.textfield.TextInputEditText;

public class StudentForm2Activity extends AppCompatActivity {

    private TextInputEditText std_address, std_class, std_school, std_board, std_marks, std_subjects;
    private Button std_form2_to_form3;
    private String phoneNum;
    private String uid;
    private String devicetoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form2);

        std_address = (TextInputEditText) findViewById(R.id.std_address_input);
        std_class = (TextInputEditText) findViewById(R.id.std_class_input);
        std_school = (TextInputEditText) findViewById(R.id.std_school_input);
        std_board = (TextInputEditText) findViewById(R.id.std_board_input);
        std_marks = (TextInputEditText) findViewById(R.id.std_percentage_input);
        std_subjects = (TextInputEditText) findViewById(R.id.std_sub_input);
        std_form2_to_form3 = (Button) findViewById(R.id.std_form2_next);

        std_form2_to_form3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toForm3 = new Intent(StudentForm2Activity.this,StudentForm3Activity.class);
                toForm3.putExtra("PHONENUMBER", getIntent().getStringExtra("PHONENUMBER"));
                toForm3.putExtra("UID", getIntent().getStringExtra("UID"));
                toForm3.putExtra("TOKEN", getIntent().getStringExtra("TOKEN"));
                toForm3.putExtra("s_name",getIntent().getStringExtra("s_name"));
                toForm3.putExtra("s_mob",getIntent().getStringExtra("s_mob"));
                toForm3.putExtra("s_email",getIntent().getStringExtra("s_email"));
                toForm3.putExtra("s_dob",getIntent().getStringExtra("s_dob"));
                toForm3.putExtra("s_pin_code",getIntent().getStringExtra("s_pin_code"));
                toForm3.putExtra("s_gender",getIntent().getStringExtra("s_gender"));
                toForm3.putExtra("s_address",std_address.getText().toString());
                toForm3.putExtra("s_class",std_class.getText().toString());
                toForm3.putExtra("s_school",std_school.getText().toString());
                toForm3.putExtra("s_board",std_board.getText().toString());
                toForm3.putExtra("s_marks",std_marks.getText().toString());
                toForm3.putExtra("s_subjects",std_subjects.getText().toString());
                startActivity(toForm3);
            }
        });
    }
}