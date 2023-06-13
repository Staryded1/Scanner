package com.example.scanner.db;

import android.app.TaskInfo;

public class MyConstans {
    public static final String TABLE_NAME = "Polzovateli";
    public static final String ID = "_Id";
    public static final String USERNAME = "Username";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";
    public static final String DB_NAME = "PP_Scanner.db";
    public static final int DB_VERSION = 1;
    public static final String DB_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERNAME + " TEXT, " + EMAIL + " TEXT, " + PASSWORD + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
