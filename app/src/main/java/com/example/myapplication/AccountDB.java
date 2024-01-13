package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AccountDB extends SQLiteOpenHelper {

    public AccountDB(Context context) {
        super(context, "SignIn.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Account(" +
                "email VARCHAR(20) PRIMARY KEY, " +
                "password VARCHAR(20) NOT NULL)");
        db.execSQL("CREATE TABLE Users(" +
                "userID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email VARCHAR(20) NOT NULL, " +
                "name VARCHAR(20) NOT NULL, " +
                "phone VARCHAR(10) NOT NULL, " +
                "walletID INTEGER NOT NULL, " +
                "FOREIGN KEY (email) REFERENCES Account(email), " +
                "FOREIGN KEY (walletID) REFERENCES Wallet(walletID))");
        db.execSQL("CREATE TABLE Wallet(" +
                "walletID INTEGER PRIMARY KEY," +
                "amount INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE Expense(" +
                "expenseID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "userID INTEGER NOT NULL, " +
                "category VARCHAR(10) NOT NULL, " +
                "amount INTEGER NOT NULL, " +
                "date TEXT NOT NULL," +
                "location VARCHAR(20) NOT NULL, " +
                "FOREIGN KEY (userID) REFERENCES Users(userID))");
        db.execSQL("CREATE TABLE Loan(" +
                "loanID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "userID INTEGER NOT NULL, " +
                "category VARCHAR(10) NOT NULL, " +
                "amount INTEGER NOT NULL, " +
                "startDate TEXT , " +
                "dueDate TEXT , " +
                "interest INTEGER NOT NULL, " +
                "FOREIGN KEY (userID) REFERENCES Users(userID))");
        db.execSQL("CREATE TABLE Investment(" +
                "investID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "userID INTEGER NOT NULL, " +
                "category VARCHAR(10) NOT NULL, " +
                "amount INTEGER NOT NULL, " +
                "startDate TEXT , " +
                "interest INTEGER NOT NULL, " +
                "FOREIGN KEY (userID) REFERENCES Users(userID))");
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
        else return true;
    }

    public Boolean checkEmail(String Table, String email){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT email FROM " + Table + " WHERE email = ?";
        Cursor cursor = myDB.rawQuery(query, new String[] {email});
        if (cursor.getCount()>0){
            cursor.close();
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
            cursor.close();
            return true;
        }
        else return false;
    }

    public String getEmail(String email, String password){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT email FROM Account WHERE email = ? AND password = ?", new String[] {email, password});
        if (cursor.moveToFirst()){
            String temp = cursor.getString(0);
            cursor.close();
            return temp;
        }
        else return null;
    }

    public Integer getUserID(String email){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT userID FROM Users WHERE email = ?";
        Cursor cursor = myDB.rawQuery(query, new String[] {email});
        if (cursor.moveToFirst()){
            Integer temp = cursor.getInt(0);
            cursor.close();
            return temp;
        }
        else return null;
    }

//    public Boolean checkID(String Table, Integer id){
//        SQLiteDatabase myDB = this.getReadableDatabase();
//        String query = "SELECT ID FROM " + Table + " WHERE ID = " + id;
//        Cursor cursor = myDB.rawQuery(query, null);
//        if (cursor.moveToFirst()){
//            return true;
//        }
//        else return false;
//
//    }

// User function
    public Boolean insertUser(String email, String name, String phone, Integer walletID){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("walletID", walletID);
        long result = myDB.insert("Users", null, contentValues);
        if (result == -1) {
            return false;
        }
        else return true;
    }
    public Boolean updateUser(String email, String name, String phone){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        String query = "email = ?";
        long result = myDB.update("Users", contentValues, query, new String[] {email});
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Boolean deleteUser(String Email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String query = "Email = " + Email;
        long result = myDB.delete("Users", query, null);
        if (result == -1){
            return false;
        }
        else return true;
    }
    public List<UserList> getUserInfo(String email){
        List<UserList> returnList = new ArrayList<>();
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT * FROM Users WHERE email = ?";
        Cursor cursor = myDB.rawQuery(query, new String[] {email});
        if (cursor.moveToFirst()){
            do{
                Integer UserID = cursor.getInt(0);
                String Email = cursor.getString(1);
                String Name = cursor.getString(2);
                String Phone = cursor.getString(3);
                Integer WalletID = cursor.getInt(4);

                UserList user = new UserList(UserID, Email, Name, Phone, WalletID);
                returnList.add(user);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return returnList;
    }

// Budget function
    public Boolean insertBudget(Integer Acc, String status, String goal){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", Acc);
        contentValues.put("status", status);
        contentValues.put("goal", goal);
        long result = myDB.insert("Budget", null, contentValues);
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Boolean updateBudget(Integer budgetID, String goal, String status){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("goal", goal);
        contentValues.put("status", status);
        String query = "budgetID = " + budgetID;
        long result = myDB.update("Budget", contentValues, query, null);
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Boolean deleteBudget(Integer budgetID){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String query = "budgetID = " + budgetID;
        long result = myDB.delete("Budget", query, null);
        if (result == -1){
            return false;
        }
        else return true;
    }

    public List<BudgetList> getBudgetList(Integer Acc){
        List<BudgetList> returnList = new ArrayList<>();
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT * FROM Budget WHERE ID = " + Acc;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Integer BudgetID = cursor.getInt(0);
                String Goal = cursor.getString(3);
                String Status = cursor.getString(2);

                BudgetList budget = new BudgetList(BudgetID, Goal, Status);
                returnList.add(budget);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        myDB.close();
        return returnList;
    }

    public Integer getNewBudgetID(){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT budgetID FROM Budget ", null);
        if (cursor.moveToFirst()){
            cursor.moveToLast();
            Integer temp = cursor.getInt(0);
            cursor.close();
            return temp;
        }
        else return 0;
    }

// Wallet function
    public Boolean checkWalletID(Integer Acc){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT walletID FROM Wallet WHERE walletID = " + Acc;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            cursor.close();
            return true;
        }
        else return false;
    }
    public Boolean insertWallet(Boolean temp, Integer Acc, Integer amount){
        if (temp == false){
            SQLiteDatabase myDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("walletID", Acc);
            contentValues.put("amount", amount);
            long result = myDB.insert("Wallet", null, contentValues);
            if (result == -1){
                return false;
            }
            else return true;
        }
        else return false;
    }
    public void updateWalletIDForUser(Integer Acc){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("walletID", Acc);
        String query = "userID = " + Acc;
        myDB.update("Users", contentValues, query, null);
    }
    public Boolean updateWallet(Integer Acc, Integer amount){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", amount);
        String query = "walletID = " + Acc;
        long result = myDB.update("Wallet", contentValues, query, null);
        if (result == -1){
            return false;
        }
        else return true;
    }

// Expense function
    public Boolean insertExpense(Integer Acc, String category, Integer amount, String location){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = myDB.rawQuery("SELECT DATE('now')", null);
        cursor.moveToFirst();
        contentValues.put("date", cursor.getString(0));
        contentValues.put("userID", Acc);
        contentValues.put("category", category);
        contentValues.put("amount", amount);
        contentValues.put("location", location);
        long result = myDB.insert("Expense", null, contentValues);
        cursor.close();
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Boolean updateExpense(Integer walletID, String category, Integer amount, String location){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", category);
        contentValues.put("amount", amount);
        contentValues.put("location", location);
        String query = "walletID = " + walletID;
        long result = myDB.update("Expense", contentValues, query, null);
        if (result == -1){
            return false;
        }
        else return true;
    }

//    public Boolean deleteExpense(){
//
//    }
    public List<ExpenseList> getExpenseList(Integer ID){
        List<ExpenseList> returnList = new ArrayList<>();
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT * FROM Expense WHERE userID = " + ID;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Integer ExpenseID = cursor.getInt(0);
                Integer UserID = cursor.getInt(1);
                String Date = cursor.getString(2);
                String Category = cursor.getString(3);
                Integer Amount = cursor.getInt(4);
                String Location = cursor.getString(5);

                ExpenseList expense = new ExpenseList(ExpenseID, UserID, Date, Category, Amount, Location);
                returnList.add(expense);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        myDB.close();
        return returnList;
}

//// Loan function
    public Boolean insertLoan(Integer Acc, Integer amount, String category, Integer interest){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID", Acc);
        contentValues.put("amount", amount);
        contentValues.put("category", category);
        contentValues.put("interest", interest);
        long result = myDB.insert("Loan", null, contentValues);
        if (result == -1){
            return false;
        }
        else return true;
    }
//    public Boolean updateLoan(){
//
//    }
//    public Boolean deleteLoan(){
//
//    }
//
//// Interest function
    public Boolean insertInvestment(Integer Acc, Integer amount, String category, Integer interest){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID", Acc);
        contentValues.put("amount", amount);
        contentValues.put("category", category);
        contentValues.put("interest", interest);
        long result = myDB.insert("Investment", null, contentValues);
        if (result == -1){
            return false;
        }
        else return true;
    }
//    public Boolean updateInterest(){
//
//    }
//    public Boolean deleteInterest(){
//
//    }

//    db.execSQL("INSERT INTO Expense (ID, category, amount, date, location) VALUES" +
//                "(1, food, 1, 20-1-2023, hanoi), " +
//                "(2, house, 2, 18-6-2023, hanoi), " +
//                "(3, clothes, 3, 11-2-2023, da_nang)");
//        db.execSQL("INSERT INTO Loan (ID, category, amount, startDate, dueDate, interest) VALUES" +
//                "(1, food, 1, 12-5-2023, 12-6-2023, 0.4), " +
//                "(2, house, 1, 22-5-2023, 22-9-2023, 0.2), " +
//                "(3, clothes, 1, 12-5-2023, 12-6-2023, 0.4)");
//        db.execSQL("INSERT INTO Investment (ID, category, amount, startDate, interest) VALUES" +
//                "(1, food, 1, 12-5-2023, 0.4), " +
//                "(1, food, 1, 12-5-2023, 0.4), " +
//                "(1, food, 1, 12-5-2023, 0.4)");

//    public void insert(){
//        SQLiteDatabase myDB = this.getWritableDatabase();
//        myDB.execSQL("INSERT INTO Expense (ID, category, amount, date, location) VALUES" +
//                "(1, 'food', 1, '20-1-2023', 'hanoi'), " +
//                "(2, 'house', 2, '18-6-2023', 'hanoi'), " +
//                "(3, 'clothes', 3, '11-2-2023', 'da_nang')");
//        myDB.execSQL("INSERT INTO Loan (ID, category, amount, startDate, dueDate, interest) VALUES" +
//                "(1, 'food', 1, '12-5-2023', '12-6-2023', 4), " +
//                "(2, 'house', 2, '22-5-2023', '22-9-2023', 2), " +
//                "(3, 'clothes', 3, '12-5-2023', '12-6-2023', 4)");
//        myDB.execSQL("INSERT INTO Investment (ID, category, amount, startDate, interest) VALUES" +
//                "(1, 'food', 1, '12-5-2023', 1), " +
//                "(2, 'house', 2, '12-5-2023', 2), " +
//                "(3, 'clothes', 3, '12-5-2023', 3)");
//    }

    public Integer getMonth(){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT strftime('%Y', date) FROM Expense", null);
        if (cursor.moveToFirst()){
            Integer temp = cursor.getInt(0);
            cursor.close();
            return temp;
        }
        else return 0;
    }

    public Integer totalWallet(Integer Acc){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String query = "SELECT Wallet.amount FROM Wallet, Users WHERE Wallet.walletID = Users.walletID AND userID = " + Acc;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            Integer temp = cursor.getInt(0);
            cursor.close();
            return temp;
        }
        else return 0;
    }

    public Integer totalLoan(Integer Acc){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String query = "SELECT SUM(Loan.amount) FROM Loan WHERE Loan.userID = " + Acc;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            Integer temp = cursor.getInt(0);
            cursor.close();
            return temp;
        }
        else return 0;
    }

    public Integer totalInvest(Integer Acc){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String query = "SELECT SUM(Investment.amount) FROM Investment WHERE Investment.userID = " + Acc;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            Integer temp = cursor.getInt(0);
            cursor.close();
            return temp;
        }
        else return 0;
    }

    public Integer totalExpense(Integer Acc){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String query = "SELECT SUM(Expense.amount) FROM Expense WHERE Expense.userID = " + Acc;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            Integer temp = cursor.getInt(0);
            cursor.close();
            return temp;
        }
        else return 0;
    }
}
