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
                "interest REAL NOT NULL, " +
                "FOREIGN KEY (userID) REFERENCES Users(userID))");
        db.execSQL("CREATE TABLE Investment(" +
                "investID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "userID INTEGER NOT NULL, " +
                "category VARCHAR(10) NOT NULL, " +
                "amount INTEGER NOT NULL, " +
                "startDate TEXT , " +
                "interest REAL NOT NULL, " +
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

//// Budget function
//    public Boolean insertBudget(Integer Acc, String status, String goal){
//        SQLiteDatabase myDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("ID", Acc);
//        contentValues.put("status", status);
//        contentValues.put("goal", goal);
//        long result = myDB.insert("Budget", null, contentValues);
//        if (result == -1){
//            return false;
//        }
//        else return true;
//    }
//    public Boolean updateBudget(Integer budgetID, String goal, String status){
//        SQLiteDatabase myDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("goal", goal);
//        contentValues.put("status", status);
//        String query = "budgetID = " + budgetID;
//        long result = myDB.update("Budget", contentValues, query, null);
//        if (result == -1){
//            return false;
//        }
//        else return true;
//    }
//    public Boolean deleteBudget(Integer budgetID){
//        SQLiteDatabase myDB = this.getWritableDatabase();
//        String query = "budgetID = " + budgetID;
//        long result = myDB.delete("Budget", query, null);
//        if (result == -1){
//            return false;
//        }
//        else return true;
//    }
//
//    public List<BudgetList> getBudgetList(Integer Acc){
//        List<BudgetList> returnList = new ArrayList<>();
//        SQLiteDatabase myDB = this.getReadableDatabase();
//        String query = "SELECT * FROM Budget WHERE ID = " + Acc;
//        Cursor cursor = myDB.rawQuery(query, null);
//        if (cursor.moveToFirst()){
//            do{
//                Integer BudgetID = cursor.getInt(0);
//                String Goal = cursor.getString(3);
//                String Status = cursor.getString(2);
//
//                BudgetList budget = new BudgetList(BudgetID, Goal, Status);
//                returnList.add(budget);
//            }
//            while (cursor.moveToNext());
//        }
//        cursor.close();
//        myDB.close();
//        return returnList;
//    }
//    public Integer getNewBudgetID(){
//        SQLiteDatabase myDB = this.getReadableDatabase();
//        Cursor cursor = myDB.rawQuery("SELECT budgetID FROM Budget ", null);
//        if (cursor.moveToFirst()){
//            cursor.moveToLast();
//            Integer temp = cursor.getInt(0);
//            cursor.close();
//            return temp;
//        }
//        else return 0;
//    }

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
    public Boolean deleteWallet(Integer ID){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String query = "walletID = " + ID;
        long result = myDB.delete("Wallet", query, null);
        if (result == -1){
            return false;
        }
        else return true;
    }

// Expense function
    public Boolean insertExpense(Integer Acc, String category, Integer amount, String date, String location){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        Cursor cursor = myDB.rawQuery("SELECT DATE('now')", null);
//        cursor.moveToFirst();
//        contentValues.put("date", cursor.getString(0));
        contentValues.put("userID", Acc);
        contentValues.put("category", category);
        contentValues.put("amount", amount);
        contentValues.put("date", date);
        contentValues.put("location", location);
        long result = myDB.insert("Expense", null, contentValues);
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Boolean updateExpense(Integer expenseID, String category, Integer amount, String date, String location){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", category);
        contentValues.put("amount", amount);
        contentValues.put("date", date);
        contentValues.put("location", location);
        String query = "expenseID = " + expenseID;
        long result = myDB.update("Expense", contentValues, query, null);
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Boolean deleteExpense(Integer ID){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String query = "expenseID = " + ID;
        long result = myDB.delete("Expense", query, null);
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Integer getNewExpenseID(){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT expenseID FROM Expense ", null);
        if (cursor.moveToFirst()){
            cursor.moveToLast();
            Integer temp = cursor.getInt(0);
            cursor.close();
            return temp;
        }
        else return 0;
}
    public List<ExpenseList> getExpenseList(Integer ID){
        List<ExpenseList> returnList = new ArrayList<>();
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT * FROM Expense WHERE userID = " + ID;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Integer ExpenseID = cursor.getInt(0);
                Integer UserID = cursor.getInt(1);
                String Category = cursor.getString(2);
                Integer Amount = cursor.getInt(3);
                String Date = cursor.getString(4);
                String Location = cursor.getString(5);

                ExpenseList expense = new ExpenseList(ExpenseID, UserID, Category, Amount, Date, Location);
                returnList.add(expense);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return returnList;
}

//// Loan function
    public Boolean insertLoan(Integer Acc, String category, Integer amount, String startDate, String dueDate, Double interest){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID", Acc);
        contentValues.put("amount", amount);
        contentValues.put("category", category);
        contentValues.put("startDate", startDate);
        contentValues.put("dueDate", dueDate);
        contentValues.put("interest", interest);
        long result = myDB.insert("Loan", null, contentValues);
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Boolean updateLoan(Integer loanID, String category, Integer amount, String startDate, String dueDate, Double interest){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", amount);
        contentValues.put("category", category);
        contentValues.put("startDate", startDate);
        contentValues.put("dueDate", dueDate);
        contentValues.put("interest", interest);
        String query = "loanID = " + loanID;
        long result = myDB.update("Loan", contentValues, query, null);
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Boolean deleteLoan(Integer ID){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String query = "loanID = " + ID;
        long result = myDB.delete("Loan", query, null);
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Integer getNewLoanID(){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT loanID FROM Loan ", null);
        if (cursor.moveToFirst()){
            cursor.moveToLast();
            Integer temp = cursor.getInt(0);
            cursor.close();
            return temp;
        }
        else return 0;
    }
    public List<LoanList> getLoanList(Integer ID){
        List<LoanList> returnList = new ArrayList<>();
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT * FROM Loan WHERE userID = " + ID;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Integer LoanID = cursor.getInt(0);
                Integer UserID = cursor.getInt(1);
                String Category = cursor.getString(2);
                Integer Amount = cursor.getInt(3);
                String StartDate = cursor.getString(4);
                String DueDate = cursor.getString(5);
                Double Interest = cursor.getDouble(6);

                LoanList loan = new LoanList(LoanID, UserID, Category, Amount, StartDate, DueDate, Interest);
                returnList.add(loan);
            }
            while (cursor.moveToNext());
        }
    cursor.close();
    return returnList;
}
//
//// Interest function
    public Boolean insertInvestment(Integer Acc, Integer amount, String category, String startDate, Double interest){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID", Acc);
        contentValues.put("amount", amount);
        contentValues.put("category", category);
        contentValues.put("startDate", startDate);
        contentValues.put("interest", interest);
        long result = myDB.insert("Investment", null, contentValues);
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Boolean updateInvestment(Integer investID, Integer amount, String category, String startDate, Double interest){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", amount);
        contentValues.put("category", category);
        contentValues.put("startDate", startDate);
        contentValues.put("interest", interest);
        String query = "investID = " + investID;
        long result = myDB.update("Investment", contentValues, query, null);
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Boolean deleteInvest(Integer ID){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String query = "investID = " + ID;
        long result = myDB.delete("Investment", query, null);
        if (result == -1){
            return false;
        }
        else return true;
    }
    public Integer getNewInvestID(){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT investID FROM Investment ", null);
        if (cursor.moveToFirst()){
            cursor.moveToLast();
            Integer temp = cursor.getInt(0);
            cursor.close();
            return temp;
        }
        else return 0;
    }
    public List<InvestList> getInvestList(Integer ID){
        List<InvestList> returnList = new ArrayList<>();
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT * FROM Investment WHERE userID = " + ID;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Integer InvestID = cursor.getInt(0);
                Integer UserID = cursor.getInt(1);
                String Category = cursor.getString(2);
                Integer Amount = cursor.getInt(3);
                String StartDate = cursor.getString(4);
                Double Interest = cursor.getDouble(5);

                InvestList invest = new InvestList(InvestID, UserID, Category, Amount, StartDate, Interest);
                returnList.add(invest);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return returnList;
}

//    public Integer getMonth(){
//        SQLiteDatabase myDB = this.getReadableDatabase();
//        Cursor cursor = myDB.rawQuery("SELECT strftime('%m', date) FROM Expense", null);
//        if (cursor.moveToFirst()){
//            Integer temp = cursor.getInt(0);
//            cursor.close();
//            return temp;
//        }
//        else return 0;
//    }

    public Integer totalWallet(Integer Acc){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String query = "" +
                "SELECT Wallet.amount " +
                "FROM Wallet, Users " +
                "WHERE Wallet.walletID = Users.walletID AND userID = " + Acc;
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
        String query = "" +
                "SELECT SUM(Loan.amount) " +
                "FROM Loan " +
                "WHERE Loan.userID = " + Acc;
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
        String query = "" +
                "SELECT SUM(Investment.amount) " +
                "FROM Investment " +
                "WHERE Investment.userID = " + Acc;
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
        String query = "" +
                "SELECT SUM(Expense.amount) " +
                "FROM Expense " +
                "WHERE Expense.userID = " + Acc;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            Integer temp = cursor.getInt(0);
            cursor.close();
            return temp;
        }
        else return 0;
    }

    public Double nextMonthInterestFromLoan(Integer ID){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "" +
                "SELECT amount * interest " +
                "FROM Loan " +
                "WHERE Loan.userID = " + ID;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            Double temp = cursor.getDouble(0);
            cursor.close();
            return temp;
        }
        else return 0.0;
    }

    public Double nextMonthInterestFromInvestment(Integer ID){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "" +
                "SELECT amount * interest " +
                "FROM Investment " +
                "WHERE Investment.userID = " + ID;
        Cursor cursor = myDB.rawQuery(query, null);
        if (cursor.moveToFirst()){
            Double temp = cursor.getDouble(0);
            cursor.close();
            return temp;
        }
        else return 0.0;
    }
}
