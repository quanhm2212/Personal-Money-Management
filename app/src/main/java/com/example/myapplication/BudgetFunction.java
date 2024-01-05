package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BudgetFunction extends AppCompatActivity {

    AccountDB myDB;
    Pointer Acc;
    EditText goal, status;
    Button btnUpdateBudget, btnDeleteBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_function);
        
        goal = findViewById(R.id.goal);
        status = findViewById(R.id.status);
        
        btnUpdateBudget = findViewById(R.id.btnUpdateBudget);
        btnDeleteBudget = findViewById(R.id.btnDeleteBudget);
        
        myDB = new AccountDB(this);
        
        Intent intent = getIntent();
        Acc.budgetID = intent.getIntExtra("BudgetID", 0);
        
        btnUpdateBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Goal = goal.getText().toString();
                String Status = status.getText().toString();
                Boolean result = myDB.updateBudget(Acc.budgetID, Goal, Status);
                if (result == true){
                    Toast.makeText(BudgetFunction.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Budget.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(BudgetFunction.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        btnDeleteBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = myDB.deleteBudget(Acc.budgetID);
                if (result == true){
                    Toast.makeText(BudgetFunction.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Budget.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(BudgetFunction.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}