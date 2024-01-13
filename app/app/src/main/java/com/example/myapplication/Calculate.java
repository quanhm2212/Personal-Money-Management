package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Calculate extends AppCompatActivity {

    AccountDB myDB;
    Pointer Acc;
    Button btnTotalWallet, btnTotalLoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        myDB = new AccountDB(this);

        btnTotalWallet = findViewById(R.id.btnTotalWallet);
        btnTotalLoan = findViewById(R.id.btnTotalLoan);

        btnTotalWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Calculate.this, myDB.totalWallet(Acc.userID).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btnTotalLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Calculate.this, myDB.totalLoan(Acc.userID).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}