package com.example.imtrying;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "Counselor.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "Games";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_DESCRIPTION = "Description";
    private static final String COLUMN_TYPE = "Type";
    private static final String COLUMN_TIME = "Time";
    private static final String COLUMN_YEAR = "Year";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Массив запросов на создание таблиц
        /*String[] queries = new String[9];
        queries[0] = "CREATE TABLE Users (_id INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, middleName TEXT," +
                " lastName TEXT, Role TEXT, Login TEXT, Password TEXT)";
        queries[1] = "CREATE TABLE Candles (_id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Description TEXT," +
                " Time TEXT, Type TEXT, Year TEXT)";
        queries[2] = "CREATE TABLE Games (_id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Description TEXT," +
                " Time TEXT, Type TEXT, Year TEXT)";

        for(int i = 0; i < 100; i++){
            db.execSQL(queries[i]);
        }*/


        // Резервный запрос на создание таблицы Users
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_TYPE +
                        " TEXT, " + COLUMN_TIME + " TEXT, "+ COLUMN_YEAR + " TEXT);";
        db.execSQL(query);

        /*
        query = "CREATE TABLE " + "Games" + " (" + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name" + " TEXT, " + "Description" + " TEXT, " + "Type" +
                " TEXT, " + "Time" + " TEXT, "+ "Years" + " TEXT);";
        db.execSQL(query);
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    void addGame(String name, String description, String type, String time, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Description", description);
        cv.put("Type", type);
        cv.put("Time", time);
        cv.put("Year", year);
        long result = db.insert("Games",null ,cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    boolean AuthUser(String login, String password){

        return true;
    }
}
