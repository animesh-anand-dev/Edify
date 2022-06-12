package com.edify.app.ui.teachersList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.edify.app.R;
import com.edify.app.Teachers;
import com.edify.app.TeachersListRVAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TeachersListFragment extends Fragment {

    private View view;
    private RecyclerView teachersRV;
    private ArrayList<Teachers> teachersArrayList;
    private TeachersListRVAdapter teachersRVAdapter;
    private FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.student_fragment_teachers, container, false);
        final FragmentActivity c = getActivity();

        teachersRV = (RecyclerView) view.findViewById(R.id.teachers_list_recycler);
        db = FirebaseFirestore.getInstance();

        teachersArrayList = new ArrayList<>();
        teachersRV.setHasFixedSize(true);
        teachersRV.setLayoutManager(new LinearLayoutManager(c));

        teachersRVAdapter = new TeachersListRVAdapter(teachersArrayList, c);
        teachersRV.setAdapter(teachersRVAdapter);

        db.collection("Teachers").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                             for(DocumentSnapshot d : list) {
                                 Teachers t = d.toObject(Teachers.class);
                                 teachersArrayList.add(t);
                             }
                             teachersRVAdapter.notifyDataSetChanged();
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