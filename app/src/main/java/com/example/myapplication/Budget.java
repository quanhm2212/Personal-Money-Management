package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Budget extends AppCompatActivity {

    List<BudgetList> budgetList = new ArrayList<BudgetList>();
    AccountDB myDB;
    Pointer Acc;
    Button btnInsertBudget;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        myDB = new AccountDB(this);

        budgetList = myDB.getBudgetList(Acc.userID);

        recyclerView = findViewById(R.id.lv_budgetLists);

        btnInsertBudget = findViewById(R.id.btnInsertBudget);

        btnInsertBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = myDB.insertBudget(Acc.userID, "No data", "No data");
                if (result == true){
                    Toast.makeText(Budget.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Budget.this, "Failed", Toast.LENGTH_SHORT).show();
                }

                budgetList.add(new BudgetList(myDB.getNewBudgetID(), "No data", "No data"));
                mAdapter.notifyItemInserted(budgetList.size() - 1);
                recyclerView.scrollToPosition(budgetList.size() - 1);
            }
        });

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new BudgetAdapter(budgetList, Budget.this);
        recyclerView.setAdapter(mAdapter);
    }
}