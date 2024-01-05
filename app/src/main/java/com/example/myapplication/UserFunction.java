package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserFunction extends AppCompatActivity {

    AccountDB myDB;
    EditText name, sex, dob, phone;
    Button add, getID, update, delete, btnBackUserInfo;
    Pointer Acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_function);

        Acc = new Pointer();

        myDB = new AccountDB(this);

        name = findViewById(R.id.name);
        sex = findViewById(R.id.sex);
        dob = findViewById(R.id.dob);
        phone = findViewById(R.id.phone);

        add = findViewById(R.id.add);
        getID = findViewById(R.id.getID);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        btnBackUserInfo = findViewById(R.id.btnBackUserInfo);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Sex = sex.getText().toString();
                String Dob = dob.getText().toString();
                String Phone = phone.getText().toString();

                Boolean result = myDB.insertUser(myDB.checkID("Users", Acc.accountID), Acc.accountID, Name, Sex, Dob, Phone);
                if (result == false){
                    Toast.makeText(UserFunction.this, "Failed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UserFunction.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                    startActivity(intent);
                }
            }
        });
        
        getID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserFunction.this, Acc.accountID.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Sex = sex.getText().toString();
                String Dob = dob.getText().toString();
                String Phone = phone.getText().toString();
                
                Boolean result = myDB.updateUser(myDB.checkID("Users", Acc.accountID), Acc.accountID, Name, Sex, Dob, Phone);
                if (result == false){
                    Toast.makeText(UserFunction.this, "Failed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UserFunction.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                    startActivity(intent);
                }
            }
        });
        
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = myDB.deleteUser(Acc.accountID);
                if (result == true){
                    Toast.makeText(UserFunction.this, "Data deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(UserFunction.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBackUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                startActivity(intent);
            }
        });
    }
}