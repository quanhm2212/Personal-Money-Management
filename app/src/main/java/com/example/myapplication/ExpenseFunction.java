package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExpenseFunction extends AppCompatActivity {
    
    AccountDB myDB;
    Pointer Acc;
    EditText expenseCategory, expenseAmount, expenseLocation, expenseDay, expenseMonth, expenseYear;
    Button btnUpdateExpense, btnDeleteExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_function);
        
        myDB = new AccountDB(this);
        
        expenseCategory = findViewById(R.id.expenseCategory);
        expenseAmount = findViewById(R.id.expenseAmount);
        expenseLocation = findViewById(R.id.expenseLocation);
        expenseDay = findViewById(R.id.expenseDay);
        expenseMonth = findViewById(R.id.expenseMonth);
        expenseYear = findViewById(R.id.expenseYear);

        Intent intent = getIntent();
        Acc.expenseID = intent.getIntExtra("ExpenseID", 0);
        
        btnUpdateExpense = findViewById(R.id.btnUpdatetExpense);
        btnDeleteExpense = findViewById(R.id.btnDeleteExpense);
        
        btnUpdateExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = expenseCategory.getText().toString();
                Integer amount = Integer.parseInt(expenseAmount.getText().toString());
                String date = expenseYear.getText().toString() + "-" + expenseMonth.getText().toString() + "-" + expenseDay.getText().toString();
                String location = expenseLocation.getText().toString();

                Boolean result = myDB.updateExpense(Acc.expenseID, category, amount, date, location);

                if (result == true){
                    Toast.makeText(ExpenseFunction.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Expense.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ExpenseFunction.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeleteExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = myDB.deleteExpense(Acc.expenseID);

                if (result == true){
                    Toast.makeText(ExpenseFunction.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Expense.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ExpenseFunction.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}