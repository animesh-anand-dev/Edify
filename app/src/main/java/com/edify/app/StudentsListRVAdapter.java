package com.edify.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class StudentsListRVAdapter extends RecyclerView.Adapter<StudentsListRVAdapter.ViewHolder>{

    private ArrayList<Students> studentsArrayList;
    private Context context;

    public StudentsListRVAdapter(ArrayList<Students> teachersArrayList, Context context) {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            studentNameTV = itemView.findViewById(R.id.card_student_name);
            studentGenderTV = itemView.findViewById(R.id.card_student_gender);
            studentClassTV = itemView.findViewById(R.id.card_student_class);
            studentBoardTV = itemView.findViewById(R.id.card_student_board);
            studentSubjectsTV = itemView.findViewById(R.id.card_student_subject_);
        }
    }
}

