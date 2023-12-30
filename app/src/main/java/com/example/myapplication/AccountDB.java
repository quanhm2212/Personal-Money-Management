package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
        db.execSQL("CREATE TABLE Account(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email VARCHAR(20) NOT NULL, " +
                "password VARCHAR(20) NOT NULL)");
        db.execSQL("CREATE TABLE Users(" +
                "ID INTEGER NOT NULL, " +
                "name VARCHAR(20) PRIMARY KEY, " +
                "sex VARCHAR(10) NOT NULL, " +
                "dob VARCHAR(10) NOT NULL, " +
                "phone VARCHAR(10) NOT NULL, " +
                "FOREIGN KEY (ID) REFERENCES Account(ID))");
        db.execSQL("CREATE TABLE Budget(" +
                "budgetID INTEGER PRIMARY KEY," +
                "ID INTEGER NOT NULL, " +
                "status VARCHAR(10) NOT NULL, " +
                "goal VARCHAR(20) NOT NULL, " +
                "FOREIGN KEY (ID) REFERENCES Account(ID))");
        db.execSQL("CREATE TABLE Expense(" +
                "expenseID INTEGER PRIMARY KEY, " +
                "ID INTEGER NOT NULL, " +
                "category VARCHAR(10) NOT NULL, " +
                "amount INTEGER NOT NULL, " +
                "date VARCHAR(10) NOT NULL," +
                "location VARCHAR(20) NOT NULL, " +
                "FOREIGN KEY (ID) REFERENCES Account(ID))");
        db.execSQL("CREATE TABLE Loan(" +
                "loanID INTEGER PRIMARY KEY, " +
                "ID INTEGER NOT NULL, " +
                "category VARCHAR(10) NOT NULL, " +
                "amount INTEGER NOT NULL, " +
                "startDate VARCHAR(10) NOT NULL, " +
                "dueDate VARCHAR(10) NOT NULL, " +
                "interest INTEGER NOT NULL, " +
                "FOREIGN KEY (ID) REFERENCES Account(ID))");
        db.execSQL("CREATE TABLE Investment(" +
                "investID INTEGER PRIMARY KEY, " +
                "ID INTEGER NOT NULL, " +
                "category VARCHAR(10) NOT NULL, " +
                "amount INTEGER NOT NULL, " +
                "startDate VARCHAR (10) NOT NULL, " +
                "interest INTEGER NOT NULL, " +
                "FOREIGN KEY (ID) REFERENCES Account(ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists Account");
        db.execSQL("DROP TABLE if exists Users");
        db.execSQL("DROP TABLE if exists Budget");
        db.execSQL("DROP TABLE if exists Expense");
        db.execSQL("DROP TABLE if exists Loan");
        db.execSQL("DROP TABLE if exists Investment");
    }

    public Boolean insertAccount(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = myDB.insert("Account", null, contentValues);
        if (result == -1) {
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM Account WHERE email = ?", new String[] {email});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkPassword(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM Account WHERE email = ? AND password = ?", new String[] {email, password});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Integer getID(String email, String password){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT ID FROM Account WHERE email = ? AND password = ?", new String[] {email, password});
        if (cursor.moveToFirst()){
            Integer temp = cursor.getInt(0);
            return temp;
        }
        else{
            return 0;
        }
    }

    public Boolean checkID(String Table, Integer id){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT ID FROM " + Table + " WHERE ID = " + id;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    // User function
    public Boolean insertUser(Boolean test,Integer ID, String name, String sex, String dob, String phone){
        if (test){
            return false;
        }
        else{
            SQLiteDatabase myDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", ID);
            contentValues.put("name", name);
            contentValues.put("sex", sex);
            contentValues.put("dob", dob);
            contentValues.put("phone", phone);
            long result = myDB.insert("Users", null, contentValues);
            if (result == -1) {
                return false;
            }
            else{
                return true;
            }
        }
    }
    public Boolean updateUser(Boolean test, Integer ID, String name, String sex, String dob, String phone){
        if (test){
            SQLiteDatabase myDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("sex", sex);
            contentValues.put("dob", dob);
            contentValues.put("phone", phone);
            String query = "ID = " + ID;
            long result = myDB.update("Users", contentValues, query, null);
            if (result == -1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
    public Boolean deleteUser(Integer ID){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String query = "ID = " + ID;;
        long result = myDB.delete("Users", query, null);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    // Budget function
    public Boolean insertBudget(){

    }
    public Boolean updateBudget(){

    }
    public Boolean deleteBudget(){

    }

    // Expense function
    public Boolean insertExpense(){

    }
    public Boolean updateExpense(){

    }
    public Boolean deleteExpense(){

    }

    // Loan function
    public Boolean insertLoan(){

    }
    public Boolean updateLoan(){

    }
    public Boolean deleteLoan(){

    }

    // Interest function
    public Boolean insertInterest(){

    }
    public Boolean updateInterest(){

    }
    public Boolean deleteInterest(){

    }
}
