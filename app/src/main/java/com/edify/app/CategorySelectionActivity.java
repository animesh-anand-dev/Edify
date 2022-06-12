package com.edify.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.edify.app.authentication.PhoneAuthenticationActivity;
import com.edify.app.student.StudentFormActivity;
import com.edify.app.teachers.TeacherFormActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CategorySelectionActivity extends AppCompatActivity {
    CardView teacher, student;
    private FirebaseUser mCurrentUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);
        teacher = (CardView) findViewById(R.id.teacher);
        student= (CardView) findViewById(R.id.student);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelectionActivity.this, PhoneAuthenticationActivity.class);
//                intent.putExtra("PHONENUMBER", getIntent().getStringExtra("PHONENUMBER"));
//                intent.putExtra("UID", getIntent().getStringExtra("UID"));
//                intent.putExtra("TOKEN", getIntent().getStringExtra("TOKEN"));
                intent.putExtra("category","teacher");
                startActivity(intent);
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategorySelectionActivity.this, PhoneAuthenticationActivity.class);
//                intent.putExtra("PHONENUMBER", getIntent().getStringExtra("PHONENUMBER"));
//                intent.putExtra("UID", getIntent().getStringExtra("UID"));
//                intent.putExtra("TOKEN", getIntent().getStringExtra("TOKEN"));
                intent.putExtra("category","student");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mCurrentUser != null) {
            sendUserToMain();
        }
    }

    public void sendUserToMain() {
        String uid = mCurrentUser.getUid();
        //Toast.makeText(getApplicationContext(),uid,Toast.LENGTH_LONG).show();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference docRefTeacher;
        final DocumentReference docRefStudent;
        docRefTeacher = db.collection("Teachers").document(uid);
        //docRefStudent = db.collection("Students").document(uid);

        docRefTeacher.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                startActivity(new Intent(getApplicationContext(), TeacherMainActivity.class));
                finish();
            }
            else
            {
                startActivity(new Intent(getApplicationContext(), StudentMainActivity.class));
                finish();
            }
        });
    }
}