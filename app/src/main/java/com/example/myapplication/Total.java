package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Total extends AppCompatActivity {

    AccountDB myDB;
    Pointer Acc;
    TextView viewTotalWallet, viewTotalLoan, viewTotalInvest, viewTotalExpense, viewTotalMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        myDB = new AccountDB(this);

        Acc = new Pointer();

        Integer totalMoney = myDB.totalWallet(Acc.userID) + myDB.totalInvest(Acc.userID) - myDB.totalLoan(Acc.userID) - myDB.totalExpense(Acc.userID);

        viewTotalWallet = findViewById(R.id.viewTotalWallet);
        viewTotalLoan = findViewById(R.id.viewTotalLoan);
        viewTotalInvest = findViewById(R.id.viewTotalInvest);
        viewTotalExpense = findViewById(R.id.viewTotalExpense);
        viewTotalMoney = findViewById(R.id.viewTotalMoney);

        viewTotalWallet.setText(myDB.totalWallet(Acc.userID).toString());
        viewTotalLoan.setText(myDB.totalLoan(Acc.userID).toString());
        viewTotalInvest.setText(myDB.totalInvest(Acc.userID).toString());
        viewTotalExpense.setText(myDB.totalExpense(Acc.userID).toString());
        viewTotalMoney.setText(totalMoney.toString());
    }
}