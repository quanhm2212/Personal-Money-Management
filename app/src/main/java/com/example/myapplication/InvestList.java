package com.example.myapplication;

public class InvestList {
    public Integer InvestID;
    public Integer UserID;
    public String Category;
    public Integer Amount;
    public String StartDate;
    public Double Interest;

    public InvestList(Integer investID, Integer userID, String category, Integer amount, String startDate, Double interest) {
        InvestID = investID;
        UserID = userID;
        Category = category;
        Amount = amount;
        StartDate = startDate;
        Interest = interest;
    }

    public Integer getInvestID() {
        return InvestID;
    }

    public void setInvestID(Integer investID) {
        InvestID = investID;
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

    public Double getInterest() {
        return Interest;
    }

    public void setInterest(Double interest) {
        Interest = interest;
    }
}
