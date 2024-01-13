package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Investment extends AppCompatActivity {
    
    EditText investmentAmount, investmentCategory, investmentInterest;
    Button btnInsertInvestment;
    AccountDB myDB;
    Pointer Acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);
        
        myDB = new AccountDB(this);
        
        investmentAmount = findViewById(R.id.investmentAmount);
        investmentCategory = findViewById(R.id.investmentCategory);
        investmentInterest = findViewById(R.id.investmentInterest);
        
        btnInsertInvestment = findViewById(R.id.btnInsertInvestment);
        
        btnInsertInvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer amount = Integer.parseInt(investmentAmount.getText().toString());
                String category = investmentCategory.getText().toString();
                Integer interest = Integer.parseInt(investmentInterest.getText().toString());
                
                Boolean result = myDB.insertInvestment(Acc.userID, amount, category, interest);
                if (result == true){
                    Toast.makeText(Investment.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Investment.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}