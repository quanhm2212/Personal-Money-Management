package com.example.myapplication;

import org.w3c.dom.Text;

public class ExpenseList {
    public Integer ExpenseID;
    public Integer UserID;
    public String Date;
    public String Category;
    public Integer Amount;
    public String Location;

    public ExpenseList(Integer expenseID, Integer userID, String category, Integer amount, String date, String location) {
        this.ExpenseID = expenseID;
        this.UserID = userID;
        this.Date = date;
        this.Category = category;
        this.Amount = amount;
        this.Location = location;
    }

    public Integer getExpenseID() {
        return ExpenseID;
    }

    public void setExpenseID(Integer expenseID) {
        ExpenseID = expenseID;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
