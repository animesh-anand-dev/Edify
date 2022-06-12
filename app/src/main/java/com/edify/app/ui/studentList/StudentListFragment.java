package com.edify.app.ui.studentList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edify.app.R;
import com.edify.app.Students;
import com.edify.app.StudentsListRVAdapter;
import com.edify.app.Teachers;
import com.edify.app.TeachersListRVAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class StudentListFragment extends Fragment {


        private View view;
        private RecyclerView studentsRV;
        private ArrayList<Students> studentsArrayList ;
        private StudentsListRVAdapter studentsRVAdapter;
        private FirebaseFirestore db;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.teacher_fragment_students, container, false);
            final FragmentActivity c = getActivity();

            studentsRV = (RecyclerView) view.findViewById(R.id.student_list_recycler);
            db = FirebaseFirestore.getInstance();

            studentsArrayList = new ArrayList<>();
            studentsRV.setHasFixedSize(true);
            studentsRV.setLayoutManager(new LinearLayoutManager(c));

            studentsRVAdapter = new StudentsListRVAdapter(studentsArrayList, c);
            studentsRV.setAdapter(studentsRVAdapter);

            db.collection("Students").get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if(!queryDocumentSnapshots.isEmpty()) {
                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d : list) {
                                    Students t = d.toObject(Students.class);
                                    studentsArrayList.add(t);
                                }
                                studentsRVAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(c, "No data found in Database", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(c, "Fail to get the data", Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }

}