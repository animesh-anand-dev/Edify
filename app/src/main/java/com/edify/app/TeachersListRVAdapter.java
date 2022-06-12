package com.edify.app;

import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                // initializing our text views.
                teacherNameTV = itemView.findViewById(R.id.card_teacher_name);
                teacherGenderTV = itemView.findViewById(R.id.card_teacher_gender);
                teacherQualificationTV = itemView.findViewById(R.id.card_teacher_qualification);
                teacherProfessionTV = itemView.findViewById(R.id.card_teacher_profession);
                teacherSubjectTV = itemView.findViewById(R.id.card_teacher_subject_);
        }
    }
}
