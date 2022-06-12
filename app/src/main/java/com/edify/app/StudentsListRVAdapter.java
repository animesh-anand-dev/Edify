package com.edify.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class StudentsListRVAdapter extends RecyclerView.Adapter<StudentsListRVAdapter.ViewHolder>{

    private ArrayList<Students> studentsArrayList;
    private Context context;

    public StudentsListRVAdapter(ArrayList<Students> studentsArrayList, Context context) {
        this.studentsArrayList = studentsArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public StudentsListRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.each_student_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsListRVAdapter.ViewHolder holder, int position) {
        Students students = studentsArrayList.get(position);
        holder.studentNameTV.setText(students.getStudentName());
        holder.studentGenderTV.setText(students.getStudentGender());
        holder.studentClassTV.setText(students.getStudentClass());
        holder.studentBoardTV.setText(students.getStudentBoard());
        holder.studentSubjectsTV.setText(students.getStudentSubjects());

        holder.callToStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = students.getMobileNumber();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:"+num));
                context.startActivity(dialIntent);
            }
        });

        holder.emailToStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to_email = students.getStudentEmail();

                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:")); // only email apps should handle this
                i.putExtra(Intent.EXTRA_EMAIL,  new String[]{to_email});

                if (i.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(i);
                }
            }
        });

        holder.smsToStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mob = students.getMobileNumber();
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
        return studentsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView studentNameTV;
        private final TextView studentGenderTV;
        private final TextView studentClassTV;
        private final TextView studentBoardTV;
        private final TextView studentSubjectsTV;
        private final ImageView callToStudent;
        private final ImageView smsToStudent;
        private final ImageView emailToStudent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            studentNameTV = itemView.findViewById(R.id.card_student_name);
            studentGenderTV = itemView.findViewById(R.id.card_student_gender);
            studentClassTV = itemView.findViewById(R.id.card_student_class);
            studentBoardTV = itemView.findViewById(R.id.card_student_board);
            studentSubjectsTV = itemView.findViewById(R.id.card_student_subject_);
            callToStudent = itemView.findViewById(R.id.card_student_call_button);
            smsToStudent = itemView.findViewById(R.id.card_student_sms_button);
            emailToStudent = itemView.findViewById(R.id.card_student_email_button);
        }
    }
}

