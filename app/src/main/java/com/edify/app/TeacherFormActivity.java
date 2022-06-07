package com.edify.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.textfield.TextInputEditText;

public class TeacherFormActivity extends AppCompatActivity {
    private TextInputEditText teach_name , teach_mob , teach_email , teach_dob , teach_pin_code;
    public String teach_gender;
    private Button teach_form1_to_form2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form);
        teach_name =(TextInputEditText) findViewById(R.id.teach_name_input);
        teach_mob =(TextInputEditText) findViewById(R.id.teach_mobile_input);
        teach_email =(TextInputEditText) findViewById(R.id.teach_email_input);
        teach_dob =(TextInputEditText) findViewById(R.id.teach_dob_input);
        teach_pin_code =(TextInputEditText) findViewById(R.id.teach_pincode_input);
        teach_form1_to_form2 =(Button) findViewById(R.id.teach_form2_next);

        teach_form1_to_form2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent teach_to_form2 = new Intent(TeacherFormActivity.this , TeacherForm2Activity.class);
                teach_to_form2.putExtra("t_name",teach_name.getText().toString());
                teach_to_form2.putExtra("t_mob",teach_mob.getText().toString());
                teach_to_form2.putExtra("t_email",teach_email.getText().toString());
                teach_to_form2.putExtra("t_dob",teach_dob.getText().toString());
                teach_to_form2.putExtra("t_pin_code",teach_pin_code.getText().toString());
                teach_to_form2.putExtra("t_gender",teach_gender);
                startActivity(teach_to_form2);
            }
        });

    }

    public void onGenderButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.teach_male_button:
                if (checked)
                    teach_gender = "Male";
                break;
            case R.id.teach_female_button:
                if (checked)
                    teach_gender = "Female";
                break;
        }
    }
}