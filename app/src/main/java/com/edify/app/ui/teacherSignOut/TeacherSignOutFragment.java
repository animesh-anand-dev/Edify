package com.edify.app.ui.teacherSignOut;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.edify.app.R;
import com.google.firebase.auth.FirebaseAuth;


public class TeacherSignOutFragment extends Fragment {

    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.teacher_fragment_signout, container, false);

        final FragmentActivity c = getActivity();

        AlertDialog.Builder b = new AlertDialog.Builder(c);
        b.setTitle("Sign Out");
        b.setMessage("Are you sure you want to Sign Out from Edify ?");
        b.setIcon(R.mipmap.ic_launcher);
        b.setCancelable(false);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog a = b.create();
        a.show();


        return view;
    }
}