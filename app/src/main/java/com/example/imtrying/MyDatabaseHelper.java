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
    void CheckDB(){
        SQLiteDatabase db = this.getReadableDatabase();

        if (db != null){
            UploadGames();
        }
        else {
            UploadGames();
        }
    }

    void UploadGames(){
        long result;
        String[] types = new String[5];
        String[] years = new String[4];
        types[0] = "На знакомство";
        types[1] = "На сплочение";
        types[2] = "На выявление лидера";
        types[3] = "На снятие тактильного напряжения";
        types[4] = "Интеллектуалка";
        years[0] = "Младший";
        years[1] = "Средний";
        years[2] = "Старший";
        years[3] = "Любой";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", "Что я привез с собой");
        cv.put("Description", "Это упражнение не только знакомит детей, но и помогает им найти товарищей со схожими интересами.\n" +
                "\n" +
                "Дети садятся в круг. Каждый участник по очереди представляет товарищам 1–2 важные для него вещи (книгу, фотографию, мягкую игрушку) из тех, что он взял с собой в лагерь, и рассказывает о своих ощущениях, связанных с этими предметами. После небольшого рассказа участника, дети могут задать ему вопросы об этой веще.");
        cv.put("Type", types[0]);
        cv.put("Time", 10);
        cv.put("Year", years[3]);
        result = db.insert("Games",null ,cv);
        cv.put("Name", "Интервью");
        cv.put("Description", "Дети делятся по парам. За 2 минуты один из партнёров должен узнать своего напарника. Затем они меняются. После они должны представить остальным детям своего партнёра.");
        cv.put("Type", types[0]);
        cv.put("Time", 15);
        cv.put("Year", years[3]);
        result = db.insert("Games",null ,cv);
        cv.put("Name", "Интервью");
        cv.put("Description", "Дети делятся по парам. За 2 минуты один из партнёров должен узнать своего напарника. Затем они меняются. После они должны представить остальным детям своего партнёра.");
        cv.put("Type", types[0]);
        cv.put("Time", 15);
        cv.put("Year", years[3]);
        result = db.insert("Games",null ,cv);
    }
}
