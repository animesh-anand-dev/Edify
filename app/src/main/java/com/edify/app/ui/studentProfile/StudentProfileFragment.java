package com.edify.app.ui.studentProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.edify.app.R;
import com.edify.app.StudentMainActivity;
import com.edify.app.Students;
import com.edify.app.TeacherMainActivity;
import com.edify.app.Teachers;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class StudentProfileFragment extends Fragment {


    private View view;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private FirebaseFirestore db;
    private TextView studentProfileName;
    private TextView studentProfileGender;
    private TextView studentProfileMobile;
    private TextView studentProfileEmail;
    private TextView studentProfileClass;
    private TextView studentProfileSubject;
    private TextView studentProfilePinCode;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.student_fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        final FragmentActivity c = getActivity();

        studentProfileName = (TextView) view.findViewById(R.id.std_profile_name_input);
        studentProfileGender = (TextView) view.findViewById(R.id.std_profile_gender_input);
        studentProfileMobile = (TextView) view.findViewById(R.id.std_profile_mobileNumber);
        studentProfileEmail = (TextView) view.findViewById(R.id.std_profile_email_input);
        studentProfileClass = (TextView) view.findViewById(R.id.std_profile_class_input);
        studentProfileSubject = (TextView) view.findViewById(R.id.std_profile_subject_input);
        studentProfilePinCode = (TextView) view.findViewById(R.id.std_profile_pinCode_input);

        if (mCurrentUser != null) {
            String uid = mCurrentUser.getUid();
            //Toast.makeText(getApplicationContext(),uid,Toast.LENGTH_LONG).show();
            db = FirebaseFirestore.getInstance();
//        final DocumentReference docRefStudent;
//        docRefStudent = db.collection("Students").document(uid);

            db.collection("Students").document(uid)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Students students = documentSnapshot.toObject(Students.class);
                            studentProfileName.setText(students.getStudentName());
                            studentProfileGender.setText(students.getStudentGender());
                            studentProfileMobile.setText(students.getMobileNumber());
                            studentProfileEmail.setText(students.getStudentEmail());
                            studentProfileClass.setText(students.getStudentClass());
                            studentProfileSubject.setText(students.getStudentSubjects());
                            studentProfilePinCode.setText(students.getStudentPinCode());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(c, "Fail to get the data", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return view;
    }
}