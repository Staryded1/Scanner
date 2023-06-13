package com.example.scanner.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDbManager {
    private Context context;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context) {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }

    public void openDb() {
        db = myDbHelper.getWritableDatabase();
    }

    public void insertToDb(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put(MyConstans.USERNAME, username);
        cv.put(MyConstans.EMAIL, email);
        cv.put(MyConstans.PASSWORD, password);
        db.insert(MyConstans.TABLE_NAME, null, cv);
    }

    public List<String> getFromDb() {
        List<String> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstans.TABLE_NAME, null, null, null, null, null, null);

        int emailIndex = cursor.getColumnIndex(MyConstans.EMAIL); // Get the column index

        while (cursor.moveToNext()) {
            if (emailIndex >= 0) {
                String email = cursor.getString(emailIndex);
                tempList.add(email);
            }
        }

        cursor.close();
        return tempList;
    }

    public void closeDb() {
        myDbHelper.close();
    }
}
