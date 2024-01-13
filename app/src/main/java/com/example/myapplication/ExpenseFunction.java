package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExpenseFunction extends AppCompatActivity {
    
    AccountDB myDB;
    Pointer Acc;
    EditText expenseCategory, expenseAmount, expenseLocation;
    Button btnInsertExpense2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_function);
        
        myDB = new AccountDB(this);
        
        expenseCategory = findViewById(R.id.expenseCategory);
        expenseAmount = findViewById(R.id.expenseAmount);
        expenseLocation = findViewById(R.id.expenseLocation);
        
        btnInsertExpense2 = findViewById(R.id.btnInsertExpense2);
        
        btnInsertExpense2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = expenseCategory.getText().toString();
                Integer amount = Integer.parseInt(expenseAmount.getText().toString());
                String location = expenseLocation.getText().toString();
                
                Boolean result = myDB.insertExpense(Acc.userID, category, amount, location);
                
                if (result == true){
                    Toast.makeText(ExpenseFunction.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ExpenseFunction.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}