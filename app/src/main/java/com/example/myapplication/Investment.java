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

public class Investment extends AppCompatActivity {

    List<InvestList> investList = new ArrayList<InvestList>();
    AccountDB myDB;
    Pointer Acc;
    Button btnInsertInvest;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter investAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);

        myDB = new AccountDB(this);

        Acc = new Pointer();

        investList = myDB.getInvestList(Acc.userID);

        btnInsertInvest = findViewById(R.id.btnInsertInvest);

        recyclerView = findViewById(R.id.lv_investList);

        btnInsertInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = myDB.insertInvestment(Acc.userID, 0, "No data", "No data", 0.0);
                if (result == true){
                    Toast.makeText(Investment.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Investment.this, "Failed", Toast.LENGTH_SHORT).show();
                }
                investList.add(new InvestList(myDB.getNewInvestID(), Acc.userID, "No data", 0, "No data", 0.0));
                investAdapter.notifyItemInserted(investList.size() - 1);
                recyclerView.scrollToPosition(investList.size() - 1);
            }
        });

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        investAdapter = new InvestAdapter(investList, Investment.this);
        recyclerView.setAdapter(investAdapter);
    }
}