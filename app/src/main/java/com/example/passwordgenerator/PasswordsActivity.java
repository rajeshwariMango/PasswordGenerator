package com.example.passwordgenerator;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PasswordsActivity extends AppCompatActivity {
    private RecyclerView passwordsRecyclerView;
    private PasswordAdapter passwordAdapter;
    private List<String> passwordList;
    private PasswordDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwords);

        dbHelper = new PasswordDBHelper(this);
        passwordList = getPasswordsFromDatabase();

        passwordsRecyclerView = findViewById(R.id.passwordsRecyclerView);
        passwordAdapter = new PasswordAdapter(passwordList);
        passwordsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        passwordsRecyclerView.setAdapter(passwordAdapter);
    }

    private List<String> getPasswordsFromDatabase() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                PasswordContract.PasswordEntry.COLUMN_PASSWORD
        };

        Cursor cursor = db.query(
                PasswordContract.PasswordEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<String> passwords = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(PasswordContract.PasswordEntry.COLUMN_PASSWORD));
                passwords.add(password);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return passwords;
    }
}
