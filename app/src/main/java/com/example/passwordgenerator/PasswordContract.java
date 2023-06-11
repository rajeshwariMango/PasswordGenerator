package com.example.passwordgenerator;

import android.provider.BaseColumns;

public class PasswordContract {

    private PasswordContract() {}

    public static final class PasswordEntry implements BaseColumns {
        public static final String TABLE_NAME = "passwords";
        public static final String COLUMN_PASSWORD = "password";
    }
}
