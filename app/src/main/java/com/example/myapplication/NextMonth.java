package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NextMonth extends AppCompatActivity {

    AccountDB myDB;
    Pointer Acc;
    TextView viewInterestLoan, viewInterestInvest, viewNextNetwoExW, viewNextLoan, viewNextInvest, viewNextNetwoEx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_month);

        myDB = new AccountDB(this);

        Acc = new Pointer();

        viewInterestLoan = findViewById(R.id.viewInterestLoan);
        viewInterestInvest = findViewById(R.id.viewInterestInvest);
        viewNextNetwoExW = findViewById(R.id.viewNextNetwoExW);
        viewNextLoan = findViewById(R.id.viewNextLoan);
        viewNextInvest = findViewById(R.id.viewNextInvest);
        viewNextNetwoEx = findViewById(R.id.viewNextNetwoEx);

        Double netWithoutExW = myDB.nextMonthInterestFromInvestment(Acc.userID) - myDB.nextMonthInterestFromLoan(Acc.userID);
        Double nextLoan = myDB.totalLoan(Acc.userID) + myDB.nextMonthInterestFromLoan(Acc.userID);
        Double nextInvestment = myDB.totalInvest(Acc.userID) + myDB.nextMonthInterestFromInvestment(Acc.userID);
        Integer netWithoutEx = myDB.totalWallet(Acc.userID) + myDB.totalInvest(Acc.userID) - myDB.totalLoan(Acc.userID);

        viewNextNetwoExW.setText(netWithoutExW.toString());
        viewNextLoan.setText(nextLoan.toString());
        viewNextInvest.setText(nextInvestment.toString());
        viewNextNetwoEx.setText(netWithoutEx.toString());

        viewInterestLoan.setText(myDB.nextMonthInterestFromLoan(Acc.userID).toString());
        viewInterestInvest.setText(myDB.nextMonthInterestFromInvestment(Acc.userID).toString());
    }
}