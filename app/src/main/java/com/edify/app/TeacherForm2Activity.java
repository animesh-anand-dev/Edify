package com.edify.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class TeacherForm2Activity extends AppCompatActivity {
    private TextInputEditText teach_address , teach_subject , teach_lang , teach_qualification , teach_profession ,teach_about ;
    public String teach_method_teaching;
    private Button teach_form2_to_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form2);

        teach_address =(TextInputEditText) findViewById(R.id.teach_address_input);
        teach_subject =(TextInputEditText) findViewById(R.id.teach_subject_input);
        teach_lang =(TextInputEditText) findViewById(R.id.teach_lang_input);
        teach_qualification =(TextInputEditText) findViewById(R.id.teach_qualification_input);
        teach_profession =(TextInputEditText) findViewById(R.id.teach_profession_input);
        teach_about =(TextInputEditText) findViewById(R.id.teach_about_input);
        teach_form2_to_submit=(Button) findViewById(R.id.teach_form_submit);

        teach_form2_to_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent teach_to_submit = new Intent(TeacherForm2Activity.this, TeacherFormActivity.class);
//                teach_to_submit.putExtra("t_address",teach_address.getText().toString());
//                teach_to_submit.putExtra("t_subject",teach_subject.getText().toString());
//                teach_to_submit.putExtra("t_lang",teach_lang.getText().toString());
//                teach_to_submit.putExtra("t_qualification",teach_qualification.getText().toString());
//                teach_to_submit.putExtra("t_profession",teach_profession.getText().toString());
//                teach_to_submit.putExtra("t_about",teach_about.getText().toString());
//                teach_to_submit.putExtra("t_method",teach_method_teaching);
//                startActivity(teach_to_submit);
                Toast.makeText(TeacherForm2Activity.this, getIntent().getStringExtra("t_gender"), Toast.LENGTH_SHORT).show();








            }
        });

    }
    public void onMethodOfTeachingButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.teach_offline_button:
                if (checked)
                    teach_method_teaching = "Offline";
                break;
            case R.id.teach_online_button:
                if (checked)
                    teach_method_teaching = "Online";
                break;
        }
    }
}