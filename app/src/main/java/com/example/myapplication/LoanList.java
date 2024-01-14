package com.example.myapplication;

public class LoanList {
    public Integer LoanID;
    public Integer UserID;
    public String Category;
    public Integer Amount;
    public String StartDate;
    public String DueDate;
    public Double Interest;

    public LoanList(Integer loanID, Integer userID, String category, Integer amount, String startDate, String dueDate, Double interest) {
        LoanID = loanID;
        UserID = userID;
        Category = category;
        Amount = amount;
        StartDate = startDate;
        DueDate = dueDate;
        Interest = interest;
    }

    public Integer getLoanID() {
        return LoanID;
    }

    public void setLoanID(Integer loanID) {
        LoanID = loanID;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
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

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public Double getInterest() {
        return Interest;
    }

    public void setInterest(Double interest) {
        Interest = interest;
    }
}
