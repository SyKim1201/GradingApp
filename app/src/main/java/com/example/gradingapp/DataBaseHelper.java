package com.example.gradingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String dbName = "College.db";
    public static final int version = 1;
    public static final String TABLE_NAME = "Grades";
    public static final String COL1 = "id";
    public static final String COL2 = "firstName";
    public static final String COL3 = "lastName";
    public static final String COL4 = "course";
    public static final String COL5 = "credits";
    public static final String COL6 = "marks";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+ " ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL2+" TEXT, "+COL3+" TEXT, "+COL4+" TEXT, "+COL5+" INTEGER, "+COL6+" INTEGER);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //Constructor
    public DataBaseHelper (@Nullable Context context) {
        super(context, dbName, null, version);
    }

    //Create table
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    //Update table
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    //Insert grade into table
    public boolean InsertGrade(Grade grade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,grade.getfName());
        contentValues.put(COL3,grade.getlName());
        contentValues.put(COL4,grade.getCourse());
        contentValues.put(COL5,grade.getCredits());
        contentValues.put(COL6,grade.getMarks());

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else { return true; }
    }

    //Get all grades from table
    public Cursor viewData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;
        cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Get grades from table by ID
    public Cursor viewDataByID(int ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;
        cursor = db.rawQuery("select * from " + TABLE_NAME + " WHERE " + COL1 + "= ?", new String[] {String.valueOf(ID)});

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Get grades from table by course name
    public Cursor viewDataByCourse(String Course) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;
        cursor = db.rawQuery("select * from " + TABLE_NAME + " WHERE " + COL4 + "= ?", new String[] {Course});

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

}
