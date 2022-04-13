package com.example.gradingapp;

public class Grade {
    private int id;
    private String fName;
    private String lName;
    private String course;
    private int credits;
    private int marks;

    //Empty constructor
    public Grade() {

    }
    //Constructor with fields
    public Grade(int id, String fName, String lName, String course, int credits, int marks) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.course = course;
        this.credits = credits;
        this.marks = marks;
    }

    //Getter and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
