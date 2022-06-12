package com.edify.app.student;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.edify.app.MainActivity;
import com.edify.app.R;
import com.edify.app.StudentMainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class StudentForm3Activity extends AppCompatActivity {

    private TextInputEditText std_hours, std_days, std_num_days, std_time_from, std_time_to;
    private Button std_form_submit;
    private String phoneNum;
    private String uid;
    private String devicetoken;
    private FirebaseFirestore db;

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

        phoneNum = getIntent().getStringExtra("PHONENUMBER");
        uid = getIntent().getStringExtra("UID");
        devicetoken = getIntent().getStringExtra("TOKEN");

        db = FirebaseFirestore.getInstance();

        std_form_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "submitted button clicked successfully", Toast.LENGTH_LONG).show();

                HashMap<String,Object> userData = new HashMap<>();
                userData.put("uid", getIntent().getStringExtra("UID"));
                userData.put("deviceToken", getIntent().getStringExtra("TOKEN"));
                userData.put("mobileNumber", getIntent().getStringExtra("PHONENUMBER"));
                userData.put("studentName", getIntent().getStringExtra("s_name"));
                userData.put("studentEmail", getIntent().getStringExtra("s_email"));
                userData.put("studentDob", getIntent().getStringExtra("s_dob"));
                userData.put("studentPinCode", getIntent().getStringExtra("s_pin_code"));
                userData.put("studentGender", getIntent().getStringExtra("s_gender"));
                userData.put("studentAddress", getIntent().getStringExtra("s_address"));
                userData.put("studentClass", getIntent().getStringExtra("s_class"));
                userData.put("studentSchool", getIntent().getStringExtra("s_school"));
                userData.put("studentBoard", getIntent().getStringExtra("s_board"));
                userData.put("studentMarks", getIntent().getStringExtra("s_marks"));
                userData.put("studentSubjects", getIntent().getStringExtra("s_subjects"));
                userData.put("studentHours", std_hours.getText().toString());
                userData.put("studentDays", std_days.getText().toString());
                userData.put("studentNumDays", std_num_days.getText().toString());
                userData.put("studentTimeFrom", std_time_from.getText().toString());
                userData.put("studentTimeTo", std_time_to.getText().toString());

                Toast.makeText(getApplicationContext(), "submitted button clicked successfully", Toast.LENGTH_LONG).show();


                db.collection("Students").document(uid)
                        .set(userData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Log.d(TAG, "DocumentSnapshot successfully written!");
                                Toast.makeText(getApplicationContext(), "Data submitted successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), StudentMainActivity.class);
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
}