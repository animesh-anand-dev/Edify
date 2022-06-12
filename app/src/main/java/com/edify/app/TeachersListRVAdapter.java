package com.edify.app;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeachersListRVAdapter extends RecyclerView.Adapter<TeachersListRVAdapter.ViewHolder>{

    private ArrayList<Teachers> teachersArrayList;
    private Context context;


    public TeachersListRVAdapter(ArrayList<Teachers> teachersArrayList, Context context) {
        this.teachersArrayList = teachersArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public TeachersListRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.each_teacher_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeachersListRVAdapter.ViewHolder holder, int position) {
        Teachers teachers = teachersArrayList.get(position);
        holder.teacherNameTV.setText(teachers.getTeacherName());
        holder.teacherGenderTV.setText(teachers.getTeacherGender());
        holder.teacherQualificationTV.setText(teachers.getTeacherQualification());
        holder.teacherProfessionTV.setText(teachers.getTeacherProfession());
        holder.teacherSubjectTV.setText(teachers.getTeacherSubject());

        holder.callToTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = teachers.getMobileNumber();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:"+num));
                context.startActivity(dialIntent);
            }
        });

        holder.emailToTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to_email = teachers.getTeacherEmail();

                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:")); // only email apps should handle this
                i.putExtra(Intent.EXTRA_EMAIL,  new String[]{to_email});

                if (i.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(i);
                }
            }
        });

        holder.smsToTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mob = teachers.getMobileNumber();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+mob));  // This ensures only SMS apps respond
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return teachersArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView teacherNameTV;
        private final TextView teacherGenderTV;
        private final TextView teacherQualificationTV;
        private final TextView teacherProfessionTV;
        private final TextView teacherSubjectTV;
        private final ImageView callToTeacher;
        private final ImageView smsToTeacher;
        private final ImageView emailToTeacher;



            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                // initializing our text views.
                teacherNameTV = itemView.findViewById(R.id.card_teacher_name);
                teacherGenderTV = itemView.findViewById(R.id.card_teacher_gender);
                teacherQualificationTV = itemView.findViewById(R.id.card_teacher_qualification);
                teacherProfessionTV = itemView.findViewById(R.id.card_teacher_profession);
                teacherSubjectTV = itemView.findViewById(R.id.card_teacher_subject_);
                callToTeacher = itemView.findViewById(R.id.card_teacher_call_button);
                smsToTeacher = itemView.findViewById(R.id.card_teacher_sms_button);
                emailToTeacher = itemView.findViewById(R.id.card_teacher_email_button);
        }
    }
}
