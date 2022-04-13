package com.example.gradingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GradeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Grade> gList;

    //Constructor with list of grades
    public GradeListAdapter (List<Grade> list, Context context) {
        super();
        gList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create a viewholder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Bind data from list to viewholder
        Grade gradeAdapter = gList.get(position);
        ((ViewHolder) holder).txtId.setText("ID: "+Integer.toString(gradeAdapter.getId()));
        ((ViewHolder) holder).txtFName.setText(gradeAdapter.getfName());
        ((ViewHolder) holder).txtLName.setText(gradeAdapter.getlName());
        ((ViewHolder) holder).txtCourse.setText(gradeAdapter.getCourse());
        ((ViewHolder) holder).txtCredits.setText("Credits: "+Integer.toString(gradeAdapter.getCredits()));
        ((ViewHolder) holder).txtMarks.setText("Marks: " + Integer.toString(gradeAdapter.getMarks()));
    }

    @Override
    public int getItemCount() {
        //Get number of items in the grade list
        return gList.size();
    }

    //Viewholder class used for adapter
    class  ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtId;
        public TextView txtFName;
        public TextView txtLName;
        public TextView txtCourse;
        public TextView txtCredits;
        public TextView txtMarks;

        public ViewHolder(View itemView) {
            super(itemView);
            txtId = (TextView) itemView.findViewById(R.id.txtID);
            txtFName = (TextView) itemView.findViewById(R.id.txtFName);
            txtLName = (TextView) itemView.findViewById(R.id.txtLName);
            txtCourse = (TextView) itemView.findViewById(R.id.txtCourse);
            txtCredits = (TextView) itemView.findViewById(R.id.txtCredits);
            txtMarks = (TextView) itemView.findViewById(R.id.txtMarks);
        }
    }


}
