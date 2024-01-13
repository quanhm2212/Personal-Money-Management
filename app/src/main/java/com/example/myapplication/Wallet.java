package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Wallet extends AppCompatActivity {
    AccountDB myDB;
    Pointer Acc;
    EditText walletAmount;
    Button btnInsertWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        myDB = new AccountDB(this);

        Acc = new Pointer();
        
        walletAmount = findViewById(R.id.walletAmount);
        
        btnInsertWallet = findViewById(R.id.btnInsertWallet);
        
        btnInsertWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer amount = Integer.parseInt(walletAmount.getText().toString());
                
                Boolean result = myDB.insertWallet(myDB.checkWalletID(Acc.userID), Acc.userID, amount);
                if (result == true){
                    Toast.makeText(Wallet.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Wallet.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}