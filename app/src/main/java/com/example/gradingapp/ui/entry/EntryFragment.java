package com.example.gradingapp.ui.entry;

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

import com.example.gradingapp.DataBaseHelper;
import com.example.gradingapp.Grade;
import com.example.gradingapp.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gradingapp.databinding.FragmentEntryBinding;

public class EntryFragment extends Fragment {

    //array of data for course spinner
    private String courses[] = {"PROG 8480", "PROG 8470", "PROG 8460", "PROG 8450"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout
        View view = inflater.inflate(R.layout.fragment_entry, container, false);

        //Databasehelper for inserting to database
        DataBaseHelper dbh = new DataBaseHelper(getActivity());

        //Populate spinner with data from array using adapter
        Spinner spnCourses = (Spinner) view.findViewById(R.id.spinCourse);
        ArrayAdapter adptCourses = new ArrayAdapter<String> ( view.getContext(), android.R.layout.simple_spinner_item, courses);
        adptCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCourses.setAdapter(adptCourses);

        EditText editFName = (EditText) view.findViewById(R.id.editTextFName);
        EditText editLName = (EditText) view.findViewById(R.id.editTextLName);
        EditText editMarks = (EditText) view.findViewById(R.id.editTextMarks);
        RadioGroup rgCredits = (RadioGroup) view.findViewById(R.id.rgCredits);

        Button btnSubmit = (Button) view.findViewById(R.id.buttonSubmit);

        //Onclick listener for submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Read data from user input
                String fName = String.valueOf(editFName.getText());
                String lName = String.valueOf(editLName.getText());
                String course = String.valueOf(spnCourses.getSelectedItem());
                RadioButton rb = (RadioButton) rgCredits.findViewById(rgCredits.getCheckedRadioButtonId());

                Integer credits = Integer.parseInt(String.valueOf(rb.getText()));
                Integer marks = Integer.parseInt(String.valueOf(editMarks.getText()));

                //Insert data into database
                Grade grade = new Grade(0, fName, lName,course, credits,marks);
                Boolean inserted = dbh.InsertGrade(grade);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}