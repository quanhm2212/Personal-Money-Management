package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Wallet extends AppCompatActivity {
    AccountDB myDB;
    Pointer Acc;
    EditText walletAmount;
    TextView viewWalletAmount;
    Button btnInsertWallet, btnUpdateWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        myDB = new AccountDB(this);

        Acc = new Pointer();
        
        walletAmount = findViewById(R.id.walletAmount);

        viewWalletAmount = findViewById(R.id.viewWalletAmount);
        
        btnInsertWallet = findViewById(R.id.btnInsertWallet);
        btnUpdateWallet = findViewById(R.id.btnUpdateWallet);

        viewWalletAmount.setText(myDB.totalWallet(Acc.userID).toString());
        
        btnInsertWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer amount = Integer.parseInt(walletAmount.getText().toString());
                
                Boolean result = myDB.insertWallet(myDB.checkWalletID(Acc.userID), Acc.userID, amount);
                if (result == true){
                    Toast.makeText(Wallet.this, "Success", Toast.LENGTH_SHORT).show();
                    viewWalletAmount.setText(myDB.totalWallet(Acc.userID).toString());
                }
                else{
                    Toast.makeText(Wallet.this, "Already inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdateWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer amount = Integer.parseInt(walletAmount.getText().toString());

                Boolean result = myDB.updateWallet(Acc.userID, amount);
                if (result == true){
                    Toast.makeText(Wallet.this, "Success", Toast.LENGTH_SHORT).show();
                    viewWalletAmount.setText(myDB.totalWallet(Acc.userID).toString());
                }
                else{
                    Toast.makeText(Wallet.this, "No data, please insert wallet amount", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}