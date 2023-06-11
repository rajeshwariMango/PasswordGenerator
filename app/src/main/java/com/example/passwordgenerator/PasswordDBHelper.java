package com.example.passwordgenerator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PasswordDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "passwords.db";
    private static final int DATABASE_VERSION = 1;

    public PasswordDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_PASSWORD_TABLE = "CREATE TABLE " +
                PasswordContract.PasswordEntry.TABLE_NAME + " (" +
                PasswordContract.PasswordEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PasswordContract.PasswordEntry.COLUMN_PASSWORD + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_PASSWORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PasswordContract.PasswordEntry.TABLE_NAME);
        onCreate(db);
    }
}
