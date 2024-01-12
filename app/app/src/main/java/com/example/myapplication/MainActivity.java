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
    Button btnUser, btnBudget, btnExpense, btnLoan, btnInterest, btnTest3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUser = findViewById(R.id.btnUser);
        btnBudget = findViewById(R.id.btnBudget);
        btnExpense = findViewById(R.id.btnExpense);
        btnLoan = findViewById(R.id.btnLoan);
        btnInterest = findViewById(R.id.btnInterest);
        btnTest3 = findViewById(R.id.btnTest3);

        Acc = new Pointer();

        myDB = new AccountDB(this);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                startActivity(intent);
            }
        });

        btnBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Budget.class);
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
                Intent intent = new Intent(getApplicationContext(), Interest.class);
                startActivity(intent);
            }
        });

        btnTest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, Acc.userID.toString() + Acc.email.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}