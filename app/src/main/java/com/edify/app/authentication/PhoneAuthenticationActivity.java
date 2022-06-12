package com.edify.app.authentication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.edify.app.MainActivity;
import com.edify.app.R;
import com.edify.app.StudentMainActivity;
import com.edify.app.TeacherMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class PhoneAuthenticationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private TextInputEditText phone_num;
    private Button otp_send;
    private TextView resend;
    private TextInputEditText otp;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String phone;
    private ProgressBar mProgressBar;
    private String category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_authentication);

        phone_num = (TextInputEditText) findViewById(R.id.phone_verify_input);
        otp_send = (Button) findViewById(R.id.phone_verify_get_otp);
        otp = (TextInputEditText) findViewById(R.id.otp_verify_input);
        mProgressBar = (ProgressBar) findViewById(R.id.phone_verify_progressBar);
        category = getIntent().getStringExtra("category");

        Toast.makeText(PhoneAuthenticationActivity.this, "You are selected " +category, Toast.LENGTH_SHORT).show();

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        // Turn off phone auth app verification.
        //mAuth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);


        phone_num.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (phone_num.getText().length() >= 10)
                    Toast.makeText(PhoneAuthenticationActivity.this, "Please enter 10 digit no. Only !", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        otp_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "otp send button clicked");

                if (TextUtils.isEmpty(phone_num.getText().toString())) {
                    // when mobile number text field is empty
                    // displaying a toast message.
                    Toast.makeText(PhoneAuthenticationActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    // if the text field is not empty we are calling our
                    // send OTP method for getting OTP from Firebase.
                    phone = "+91" + phone_num.getText().toString();

                    PhoneAuthOptions options =
                            PhoneAuthOptions.newBuilder(mAuth)
                                    .setPhoneNumber(phone)       // Phone number to verify
                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                    .setActivity(PhoneAuthenticationActivity.this)                 // Activity (for callback binding)
                                    .setCallbacks(mCallBacks)          // OnVerificationStateChangedCallbacks
                                    .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                    Log.d(TAG, "OTP sending process starts");
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });



        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }
                Toast.makeText(PhoneAuthenticationActivity.this, "Verification Failed, please try again!", Toast.LENGTH_LONG).show();
                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                Intent otpIntent = new Intent(PhoneAuthenticationActivity.this, OtpVerifyActivity.class);
                otpIntent.putExtra("AuthCredentials", mVerificationId);
                otpIntent.putExtra("PhoneNumber", phone);
                otpIntent.putExtra("category",category);
                startActivity(otpIntent);
                finish();
            }
        };
    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            sendUserToMain();
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
                            }
                        }
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