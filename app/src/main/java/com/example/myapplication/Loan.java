package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loan extends AppCompatActivity {

    AccountDB myDB;
    Pointer Acc;
    EditText loanAmount, loanCategory, loanInterest;
    Button btnInsertLoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        
        myDB = new AccountDB(this);

        Acc = new Pointer();

        loanAmount = findViewById(R.id.loanAmount);
        loanCategory = findViewById(R.id.loanCategory);
        loanInterest = findViewById(R.id.loanInterest);

        btnInsertLoan = findViewById(R.id.btnInsertLoan);

        btnInsertLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer amount = Integer.parseInt(loanAmount.getText().toString());
                String category = loanCategory.getText().toString();
                Integer interest = Integer.parseInt(loanInterest.getText().toString());

                Boolean result = myDB.insertLoan(Acc.userID, amount, category, interest);
                if (result == true){
                    Toast.makeText(Loan.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Loan.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}