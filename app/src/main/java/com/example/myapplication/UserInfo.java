package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserInfo extends AppCompatActivity {
    AccountDB myDB;
    Pointer Acc;
    TextView viewEmail, viewName, viewPhone, viewWalletID;
    Button btnEdit;
    List<UserList> user = new ArrayList<UserList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        myDB = new AccountDB(this);

        Acc = new Pointer();

        viewEmail = findViewById(R.id.viewEmail);
        viewName = findViewById(R.id.viewName);
        viewPhone = findViewById(R.id.viewPhone);
        viewWalletID = findViewById(R.id.viewWalletID);

        btnEdit = findViewById(R.id.btnEdit);

        user = myDB.getUserInfo(Acc.email);
        if (user.isEmpty()){
            viewEmail.setText("No info");
            viewName.setText("No info");
            viewPhone.setText("No info");
            viewWalletID.setText("No info");
        }
        else {
            viewEmail.setText(user.get(0).getEmail());
            viewName.setText(user.get(0).getName());
            viewPhone.setText(user.get(0).getPhone());
            viewWalletID.setText(user.get(0).getWalletID().toString());
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserFunction.class);
                startActivity(intent);
            }
        });
    }
}