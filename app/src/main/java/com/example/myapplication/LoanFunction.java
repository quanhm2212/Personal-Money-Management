package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoanFunction extends AppCompatActivity {

    AccountDB myDB;
    Pointer Acc;
    EditText loanAmount, loanCategory, loanInterest, loanStartDay, loanStartMonth, loanStartYear, loanDueDay, loanDueMonth, loanDueYear;
    Button btnUpdateLoan, btnDeleteLoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_function);
        
        myDB = new AccountDB(this);

        Acc = new Pointer();

        Intent intent = getIntent();
        Acc.loanID = intent.getIntExtra("LoanID", 0);

        loanAmount = findViewById(R.id.loanAmount);
        loanCategory = findViewById(R.id.loanCategory);
        loanInterest = findViewById(R.id.loanInterest);
        loanStartDay = findViewById(R.id.loanStartDay);
        loanStartMonth = findViewById(R.id.loanStartMonth);
        loanStartYear = findViewById(R.id.loanStartYear);
        loanDueDay = findViewById(R.id.loanDueDay);
        loanDueMonth = findViewById(R.id.loanDueMonth);
        loanDueYear = findViewById(R.id.loanDueYear);

        btnUpdateLoan = findViewById(R.id.btnUpdateLoan);
        btnDeleteLoan = findViewById(R.id.btnDeleteLoan);

        btnUpdateLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer amount = Integer.parseInt(loanAmount.getText().toString());
                String category = loanCategory.getText().toString();
                Double interest = Double.parseDouble(loanInterest.getText().toString());
                String startDate = loanStartYear.getText().toString() + "-" + loanStartMonth.getText().toString() + "-" + loanStartDay.getText().toString();
                String dueDate = loanDueYear.getText().toString() + "-" + loanDueMonth.getText().toString() + "-" + loanDueDay.getText().toString();

                Boolean result = myDB.updateLoan(Acc.loanID, category, amount, startDate, dueDate, interest);
                if (result == true){
                    Toast.makeText(LoanFunction.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Loan.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoanFunction.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeleteLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = myDB.deleteLoan(Acc.loanID);

                if (result == true){
                    Toast.makeText(LoanFunction.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Expense.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoanFunction.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}