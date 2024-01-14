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

public class Loan extends AppCompatActivity {

    List<LoanList> loanList = new ArrayList<LoanList>();
    AccountDB myDB;
    Pointer Acc;
    Button btnInsertLoan2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter loanAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        myDB = new AccountDB(this);

        Acc = new Pointer();

        loanList = myDB.getLoanList(Acc.userID);

        btnInsertLoan2 = findViewById(R.id.btnInsertLoan2);

        recyclerView = findViewById(R.id.lv_loanList);

        btnInsertLoan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = myDB.insertLoan(Acc.userID, "No data", 0, "No data", "No data", 0.0);
                if (result == true){
                    Toast.makeText(Loan.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Loan.this, "Failed", Toast.LENGTH_SHORT).show();
                }
                loanList.add(new LoanList(myDB.getNewLoanID(), Acc.userID, "No data", 0, "No data", "No data", 0.0));
                loanAdapter.notifyItemInserted(loanList.size() - 1);
                recyclerView.scrollToPosition(loanList.size() - 1);
            }
        });

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loanAdapter = new LoanAdapter(loanList, Loan.this);
        recyclerView.setAdapter(loanAdapter);
    }
}