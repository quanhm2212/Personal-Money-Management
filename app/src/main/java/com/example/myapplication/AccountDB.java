package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AccountDB extends SQLiteOpenHelper {
    public AccountDB(Context context) {
        super(context, "SignIn.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE login (ID Integer primary key autoincrement, email Text, password Text)");
        db.execSQL("CREATE TABLE users (ID Integer primary key autoincrement, name Text, sex Text, dob Text)");
        db.execSQL("CREATE TABLE budget (ID Integer primary key autoincrement, current_money Integer, goal Integer, month Integer)");
        db.execSQL("CREATE TABLE expense (ID Integer primary key autoincrement, salary Integer, food Integer, housing Integer, month Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists login");
        db.execSQL("DROP TABLE if exists users");
        db.execSQL("DROP TABLE if exists budget");
        db.execSQL("DROP TABLE if exists expense");
    }

    public Boolean insertData(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = myDB.insert("login", null, contentValues);
        if (result == -1) {
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM login WHERE email = ?", new String[] {email});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkPassword(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM login WHERE email = ? and password = ?", new String[] {email, password});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean insertUser(String name, String sex, String dob){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("sex", sex);
        contentValues.put("dob", dob);
        long result = myDB.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean insertBudget(Integer current_money, Integer goal, Integer month){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("current_money", current_money);
        contentValues.put("goal", goal);
        contentValues.put("month", month);
        long result = myDB.insert("budget", null, contentValues);
        if (result == -1) {
            return false;
        }
        else{
            return true;
        }
    }
}
