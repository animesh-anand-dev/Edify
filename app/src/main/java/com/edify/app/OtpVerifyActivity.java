package com.edify.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class OtpVerifyActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputEditText phone_num;
    private TextView resend;
    private Button otp_verify;
    private TextInputEditText otp;
    private String mAuthVerificationId, phoneNum, deviceToken;
    private FirebaseUser mCurrentUser;
    private ProgressBar mProgressBarOtp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);

       resend = (TextView) findViewById(R.id.resend_btn);
        otp = (TextInputEditText) findViewById(R.id.otp_input);
        otp_verify = (Button) findViewById(R.id.otp_verify);
        mProgressBarOtp = (ProgressBar) findViewById(R.id.progressBarOtp);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        mAuthVerificationId = getIntent().getStringExtra("AuthCredentials");
        phoneNum = getIntent().getStringExtra("PhoneNumber");

        otp_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_otp = otp.getText().toString();

                if (TextUtils.isEmpty(input_otp) || input_otp.length()<6){
                    Toast.makeText(getApplicationContext(), "Please enter correct OTP", Toast.LENGTH_LONG).show();
                }else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mAuthVerificationId, input_otp);
                    signInWithPhoneAuthCredential(credential);
                    mProgressBarOtp.setVisibility(View.VISIBLE);
                }
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Resend not available at this time",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        FirebaseUser user = Objects.requireNonNull(task.getResult()).getUser();
                        String uid = Objects.requireNonNull(user).getUid();
                        final FirebaseFirestore db = FirebaseFirestore.getInstance();
                        final DocumentReference docRef = db.collection("UsersList").document(uid);

                        FirebaseInstallations.getInstance().getToken(true).addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()){
                                deviceToken = task1.getResult().getToken();
                            }
                        });

                        docRef.get().addOnSuccessListener(documentSnapshot -> {
                            if (documentSnapshot.exists()) {
                                sendUserToMain();
                            }else {
                                Intent intent = new Intent(OtpVerifyActivity.this, MainActivity.class);
//                                intent.putExtra("PHONENUMBER", phoneNum);
//                                intent.putExtra("UID", uid);
//                                intent.putExtra("TOKEN", deviceToken);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }

                        });

                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                        }
                    } else
                    {
                        Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    protected void onStart() {
        super.onStart();

        if (mCurrentUser != null) {
            sendUserToMain();
        }
    }

    public void sendUserToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}