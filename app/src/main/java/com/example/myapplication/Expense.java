package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Expense extends AppCompatActivity {

    List<ExpenseList> expenseList = new ArrayList<ExpenseList>();
    AccountDB myDB;
    Pointer Acc;
    Button btnInsertExpense, btnTest;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        
        myDB = new AccountDB(this);

        Acc = new Pointer();

        expenseList = myDB.getExpenseList(Acc.userID);
        
        btnInsertExpense = findViewById(R.id.btnInsertExpense);
        btnTest = findViewById(R.id.btnTest3);

        recyclerView = findViewById(R.id.lv_expenseList);
        
        btnInsertExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = myDB.insertExpense(Acc.userID, "test", 0, "test");
                if (result == true){
                    Toast.makeText(Expense.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Expense.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Expense.this, myDB.getMonth().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ExpenseAdapter(expenseList, Expense.this);
        recyclerView.setAdapter(mAdapter);
    }
}