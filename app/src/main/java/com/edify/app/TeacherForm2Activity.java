package com.edify.app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class TeacherForm2Activity extends AppCompatActivity {
    private TextInputEditText teach_address , teach_subject , teach_lang , teach_qualification , teach_profession ,teach_about ;
    public String teach_method_teaching;
    private Button teach_form2_to_submit;
    private String teach_phone_num;
    private String uid;
    private String device_token;
    private FirebaseFirestore db;

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

        teach_phone_num = getIntent().getStringExtra("PHONENUMBER");
        uid = getIntent().getStringExtra("UID");
        device_token = getIntent().getStringExtra("TOKEN");

        db = FirebaseFirestore.getInstance();

        teach_form2_to_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "submitted button clicked successfully", Toast.LENGTH_LONG).show();

                HashMap<String,Object> teacher_data = new HashMap<>();
                teacher_data.put("uid", getIntent().getStringExtra("UID"));
                teacher_data.put("deviceToken", getIntent().getStringExtra("TOKEN"));
                teacher_data.put("mobileNumber", getIntent().getStringExtra("PHONENUMBER"));
                teacher_data.put("teacherName",getIntent().getStringExtra("t_name"));
                teacher_data.put("teacherEmail",getIntent().getStringExtra("t_email"));
                teacher_data.put("teacherDob",getIntent().getStringExtra("t_dob"));
                teacher_data.put("teacherPinCode",getIntent().getStringExtra("t_pin_code"));
                teacher_data.put("teacherGender",getIntent().getStringExtra("t_gender"));
                teacher_data.put("teacherAddress",teach_address.getText().toString());
                teacher_data.put("teacherSubject",teach_subject.getText().toString());
                teacher_data.put("teacherLanguage",teach_lang.getText().toString());
                teacher_data.put("teacherQualification",teach_qualification.getText().toString());
                teacher_data.put("teacherProfession",teach_profession.getText().toString());
                teacher_data.put("teacherAbout",teach_about.getText().toString());

                Toast.makeText(getApplicationContext(), "submitted button clicked successfully", Toast.LENGTH_LONG).show();

                db.collection("UsersList").document(uid).collection("teacher").document(teach_phone_num)
                        .set(teacher_data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Log.d(TAG, "DocumentSnapshot successfully written!");
                                Toast.makeText(getApplicationContext(), "Data submitted successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                                Toast.makeText(getApplicationContext(), "Something went Wrong!", Toast.LENGTH_SHORT).show();
                            }
                        });
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