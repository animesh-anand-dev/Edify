package com.edify.app.ui.teacherProfile;

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
import com.edify.app.Teachers;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TeacherProfileFragment extends Fragment {
    private View view;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private FirebaseFirestore db;
    private TextView teacherProfileName;
    private TextView teacherProfileGender;
    private TextView teacherProfileMobile;
    private TextView teacherProfileEmail;
    private TextView teacherProfileQualification;
    private TextView teacherProfileSubject;
    private TextView teacherProfilePinCode;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.teacher_fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        final FragmentActivity c = getActivity();

        teacherProfileName = (TextView) view.findViewById(R.id.teach_profile_name_input);
        teacherProfileGender = (TextView) view.findViewById(R.id.teach_profile_gender_input);
        teacherProfileMobile = (TextView) view.findViewById(R.id.teach_profile_mobileNumber);
        teacherProfileEmail = (TextView) view.findViewById(R.id.teach_profile_email_input);
        teacherProfileQualification = (TextView) view.findViewById(R.id.teach_profile_qualification_input);
        teacherProfileSubject = (TextView) view.findViewById(R.id.teach_profile_subject_input);
        teacherProfilePinCode = (TextView) view.findViewById(R.id.teach_profile_pinCode_input);

        if (mCurrentUser != null) {
            String uid = mCurrentUser.getUid();
            //Toast.makeText(getApplicationContext(),uid,Toast.LENGTH_LONG).show();
            db = FirebaseFirestore.getInstance();
//        final DocumentReference docRefteacher;
//        docRefteacher = db.collection("Teachers").document(uid);

            db.collection("Teachers").document(uid)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Teachers teachers = documentSnapshot.toObject(Teachers.class);
                            teacherProfileName.setText(teachers.getTeacherName());
                            teacherProfileGender.setText(teachers.getTeacherGender());
                            teacherProfileMobile.setText(teachers.getMobileNumber());
                            teacherProfileEmail.setText(teachers.getTeacherEmail());
                            teacherProfileQualification.setText(teachers.getTeacherQualification());
                            teacherProfileSubject.setText(teachers.getTeacherSubject());
                            teacherProfilePinCode.setText(teachers.getTeacherPinCode());
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