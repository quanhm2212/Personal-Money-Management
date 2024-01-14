package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InvestmentFunction extends AppCompatActivity {
    
    EditText investmentAmount, investmentCategory, investmentInterest, investStartDay, investStartMonth, investStartYear;
    Button btnUpdateInvest, btnDeleteInvest;
    AccountDB myDB;
    Pointer Acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_function);
        
        myDB = new AccountDB(this);

        Acc = new Pointer();

        Intent intent = getIntent();
        Acc.investID = intent.getIntExtra("InvestID", 0);
        
        investmentAmount = findViewById(R.id.investmentAmount);
        investmentCategory = findViewById(R.id.investmentCategory);
        investmentInterest = findViewById(R.id.investmentInterest);
        investStartDay = findViewById(R.id.investStartDay);
        investStartMonth = findViewById(R.id.investStartMonth);
        investStartYear = findViewById(R.id.investStartYear);

        btnUpdateInvest = findViewById(R.id.btnUpdateInvest);
        btnDeleteInvest = findViewById(R.id.btnDeleteInvest);

        btnUpdateInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer amount = Integer.parseInt(investmentAmount.getText().toString());
                String category = investmentCategory.getText().toString();
                Double interest = Double.parseDouble(investmentInterest.getText().toString());
                String startDate = investStartYear.getText().toString() + "-" + investStartMonth.getText().toString() + "-" + investStartDay.getText().toString();
                
                Boolean result = myDB.updateInvestment(Acc.investID, amount, category, startDate, interest);
                if (result == true){
                    Toast.makeText(InvestmentFunction.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Investment.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(InvestmentFunction.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeleteInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = myDB.deleteInvest(Acc.investID);
                if (result == true){
                    Toast.makeText(InvestmentFunction.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Investment.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(InvestmentFunction.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}