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
    TextView viewName, viewSex, viewDob, viewPhone;
    Button btnEdit;
    List<UserList> user = new ArrayList<UserList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        myDB = new AccountDB(this);

        viewName = findViewById(R.id.viewName);
        viewSex = findViewById(R.id.viewSex);
        viewDob = findViewById(R.id.viewDob);
        viewPhone = findViewById(R.id.viewPhone);

        btnEdit = findViewById(R.id.btnEdit);

        user = myDB.getUserInfo(Acc.accountID);
        if (user.isEmpty()){
            viewName.setText("No info");
            viewSex.setText("No info");
            viewDob.setText("No info");
            viewPhone.setText("No info");
        }
        else {
            viewName.setText(user.get(0).getName());
            viewSex.setText(user.get(0).getSex());
            viewDob.setText(user.get(0).getDob());
            viewPhone.setText(user.get(0).getPhone());
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