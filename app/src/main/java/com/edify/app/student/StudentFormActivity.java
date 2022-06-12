package com.edify.app.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.edify.app.R;
import com.google.android.material.textfield.TextInputEditText;

public class StudentFormActivity extends AppCompatActivity {

    private TextInputEditText std_name, std_mob, std_email, std_dob, std_pin_code;
    public String std_gender;
    private Button std_form1_to_form2;

    //private String stu_name, stu_mob, stu_email, stu_dob, stu_pin_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        std_name = (TextInputEditText) findViewById(R.id.std_name_input);
        std_mob = (TextInputEditText) findViewById(R.id.std_mobile_input);
        std_email = (TextInputEditText) findViewById(R.id.std_email_input);
        std_dob = (TextInputEditText) findViewById(R.id.std_dob_input);
        std_pin_code = (TextInputEditText) findViewById(R.id.std_pincode_input);
        std_form1_to_form2 = (Button) findViewById(R.id.std_form1_next);

        std_form1_to_form2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toForm2 = new Intent(StudentFormActivity.this,StudentForm2Activity.class);
                toForm2.putExtra("PHONENUMBER", getIntent().getStringExtra("PHONENUMBER"));
                toForm2.putExtra("UID", getIntent().getStringExtra("UID"));
                toForm2.putExtra("TOKEN", getIntent().getStringExtra("TOKEN"));
                toForm2.putExtra("s_name",std_name.getText().toString());
                toForm2.putExtra("s_mob",std_mob.getText().toString());
                toForm2.putExtra("s_email",std_email.getText().toString());
                toForm2.putExtra("s_dob",std_email.getText().toString());
                toForm2.putExtra("s_pin_code",std_pin_code.getText().toString());
                toForm2.putExtra("s_gender",std_gender);
                startActivity(toForm2);
            }
        });

    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.std_male_button:
                if (checked)
                    std_gender = "Male";
                    break;
            case R.id.std_female_button:
                if (checked)
                    std_gender = "Female";
                    break;
        }
    }
}