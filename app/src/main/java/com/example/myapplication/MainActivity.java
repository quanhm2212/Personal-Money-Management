package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Pointer Acc;
    AccountDB myDB;
    Button btnUser, btnWallet, btnExpense, btnLoan, btnInterest, btnTotal, btnNextMonth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUser = findViewById(R.id.btnUser);
        btnWallet = findViewById(R.id.btnWallet);
        btnExpense = findViewById(R.id.btnExpense);
        btnLoan = findViewById(R.id.btnLoan);
        btnInterest = findViewById(R.id.btnInterest);
        btnTotal = findViewById(R.id.btnTotal);
        btnNextMonth = findViewById(R.id.btnNextMonth);

        Acc = new Pointer();

        myDB = new AccountDB(this);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                startActivity(intent);
            }
        });

        btnWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Wallet.class);
                startActivity(intent);
            }
        });

        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Expense.class);
                startActivity(intent);
            }
        });

        btnLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Loan.class);
                startActivity(intent);
            }
        });

        btnInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Investment.class);
                startActivity(intent);
            }
        });

        btnTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Total.class);
                startActivity(intent);
            }
        });

        btnNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NextMonth.class);
                startActivity(intent);
            }
        });
    }
}