package com.example.myapplication;

public class BudgetList {
    public Integer BudgetID;
    public String Goal;
    public String Status;

    public BudgetList(Integer BudgetID, String Goal, String Status){
        this.BudgetID = BudgetID;
        this.Goal = Goal;
        this.Status = Status;
    }

    public Integer getBudgetID() {
        return BudgetID;
    }

    public void setBudgetID(Integer budgetID) {
        BudgetID = budgetID;
    }

    public String getGoal() {
        return Goal;
    }

    public void setGoal(String goal) {
        Goal = goal;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
