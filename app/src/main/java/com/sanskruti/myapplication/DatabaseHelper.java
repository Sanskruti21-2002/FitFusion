package com.sanskruti.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "fitness.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdata(name TEXT primary key, contact TEXT, dob TEXT, pass TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdata");
    }

    public Boolean insertuserdata(String name, String dob,String pass,String email,String contact) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("dob", dob);
        contentValues.put("pass", pass);
        contentValues.put("email", email);
        contentValues.put("contact", contact);

        long result = DB.insert("Userdata", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public Boolean updateuserdata(String name,String pass) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = DB.rawQuery("Select * from Userdata where name = ? AND dob=?",
                new String[]{name,pass});
        if (cursor.getCount() > 0) {
                return true;
        } else
            return false;

    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdata", null);
        return cursor;
    }
}
