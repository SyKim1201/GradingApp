package com.example.gradingapp.ui.search;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gradingapp.DataBaseHelper;
import com.example.gradingapp.Grade;
import com.example.gradingapp.GradeListAdapter;
import com.example.gradingapp.R;
import com.example.gradingapp.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    //array of data for course spinner
    private String courses[] = {"PROG 8480", "PROG 8470", "PROG 8460", "PROG 8450"};

    //List to hold data for recyclerview
    List<Grade> gList = new ArrayList<>();
    RecyclerView rvSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        rvSearch = view.findViewById(R.id.rvSearch);

        EditText edtID = (EditText) view.findViewById(R.id.editTextID);
        Button btnID = (Button) view.findViewById(R.id.buttonID);
        Button btnCourse = (Button) view.findViewById(R.id.buttonProg);

        //Populate spinner with data from array using adapter
        Spinner spnCourses = (Spinner) view.findViewById(R.id.spinnerProg);
        ArrayAdapter adptCourses = new ArrayAdapter<String> ( view.getContext(), android.R.layout.simple_spinner_item, courses);
        adptCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCourses.setAdapter(adptCourses);

        //Hide search edittext, spinner, and buttons
        edtID.setVisibility(EditText.INVISIBLE);
        btnID.setVisibility(Button.INVISIBLE);
        btnCourse.setVisibility(Button.INVISIBLE);
        spnCourses.setVisibility(Spinner.INVISIBLE);

        RadioGroup rgSearch = (RadioGroup) view.findViewById(R.id.rgSearch);

        //Show corresponding search elements depending on selected radio button
        rgSearch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = radioGroup.findViewById(i);
                if(rb.getId() == R.id.radioID) {
                    edtID.setVisibility(EditText.VISIBLE);
                    btnID.setVisibility(Button.VISIBLE);
                    btnCourse.setVisibility(Button.INVISIBLE);
                    spnCourses.setVisibility(Spinner.INVISIBLE);
                } else if (rb.getId() == R.id.radioCode) {
                    edtID.setVisibility(EditText.INVISIBLE);
                    btnID.setVisibility(Button.INVISIBLE);
                    btnCourse.setVisibility(Button.VISIBLE);
                    spnCourses.setVisibility(Spinner.VISIBLE);
                }
            }
        });

        //Search button when search by ID is selected
        btnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Input ID from edittext
                int ID = Integer.parseInt(String.valueOf(edtID.getText()));

                //Clear the list
                gList.clear();

                //Search data from database using databasehelper and populate the list
                DataBaseHelper dbh = new DataBaseHelper(getActivity());
                //Search by ID
                Cursor cursor = dbh.viewDataByID(ID);
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

                //Set layout manager for positioning items in recyclerview
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                rvSearch.setLayoutManager(layoutManager);
                //Set adapter to create viewholders and bind data
                GradeListAdapter gradeListAdapter = new GradeListAdapter(gList, view.getContext());
                rvSearch.setAdapter(gradeListAdapter);
                gradeListAdapter.notifyDataSetChanged();

            }
        });

        btnCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Selected course name from spinner
                String course = String.valueOf(spnCourses.getSelectedItem());

                //Clear the list
                gList.clear();

                //Search data from database using databasehelper and populate the list
                DataBaseHelper dbh = new DataBaseHelper(getActivity());
                //Search by course name
                Cursor cursor = dbh.viewDataByCourse(course);
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

                //Set layout manager for positioning items in recyclerview
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                rvSearch.setLayoutManager(layoutManager);
                //Set adapter to create viewholders and bind data
                GradeListAdapter gradeListAdapter = new GradeListAdapter(gList, view.getContext());
                rvSearch.setAdapter(gradeListAdapter);
                gradeListAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}