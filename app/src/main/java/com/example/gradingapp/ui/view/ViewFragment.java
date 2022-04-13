package com.example.gradingapp.ui.view;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gradingapp.DataBaseHelper;
import com.example.gradingapp.Grade;
import com.example.gradingapp.GradeListAdapter;
import com.example.gradingapp.R;

import java.util.ArrayList;
import java.util.List;

public class ViewFragment extends Fragment {

    //List to hold data for recyclerview
    List<Grade> gList = new ArrayList<>();

    RecyclerView rvGrades;

    public ViewFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        //Get data from database using databasehelper and populate the list
        DataBaseHelper dbh = new DataBaseHelper(getActivity());
        Cursor cursor = dbh.viewData();
        if (cursor == null) {
            Toast.makeText(getContext(), "No record found", Toast.LENGTH_SHORT).show();
        } else {
            if (cursor.moveToFirst()) {
                do {
                    Grade grade = new Grade();
                    grade.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    grade.setfName(cursor.getString(cursor.getColumnIndex("firstName")));
                    grade.setlName(cursor.getString(cursor.getColumnIndex("lastName")));
                    grade.setCourse(cursor.getString(cursor.getColumnIndex("course")));
                    grade.setCredits(cursor.getInt(cursor.getColumnIndex("credits")));
                    grade.setMarks(cursor.getInt(cursor.getColumnIndex("marks")));
                    gList.add(grade);
                } while (cursor.moveToNext());
            }
            cursor.close();
            dbh.close();

        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        rvGrades = getView().findViewById(R.id.rvGrades);

        //Set layout manager for positioning items in recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        rvGrades.setLayoutManager(layoutManager);
        //Set adapter to create viewholders and bind data
        GradeListAdapter gradeListAdapter = new GradeListAdapter(gList, view.getContext());
        rvGrades.setAdapter(gradeListAdapter);
        gradeListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}