package com.example.passwordgenerator;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {
    private Button generateButton;
    private TextView passwordTextView;

    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_-+=<>?";
    private PasswordDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new PasswordDBHelper(this);

        generateButton = findViewById(R.id.generateButton);
        passwordTextView = findViewById(R.id.passwordTextView);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = generatePassword(10); // Change the password length as desired
                passwordTextView.setText(password);

                // Save the generated password to the database
                savePassword(password);
            }
        });
    }

    private String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            sb.append(ALLOWED_CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    private void savePassword(String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PasswordContract.PasswordEntry.COLUMN_PASSWORD, password);

        long newRowId = db.insert(PasswordContract.PasswordEntry.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Password saved to database", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error saving password", Toast.LENGTH_SHORT).show();
        }
    }
}
