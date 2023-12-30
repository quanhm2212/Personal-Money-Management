package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UsersInfo extends AppCompatActivity {

    AccountDB myDB;
    EditText name, sex, dob, phone;
    Button add, getID, update, delete;
    CurrentAccount Acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_info);

        Acc = new CurrentAccount();

        myDB = new AccountDB(this);

        name = findViewById(R.id.name);
        sex = findViewById(R.id.sex);
        dob = findViewById(R.id.dob);
        phone = findViewById(R.id.phone);

        add = findViewById(R.id.add);
        getID = findViewById(R.id.getID);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Sex = sex.getText().toString();
                String Dob = dob.getText().toString();
                String Phone = phone.getText().toString();

                Boolean result = myDB.insertUser(myDB.checkID("Users", Acc.id), Acc.id, Name, Sex, Dob, Phone);
                if (result == false){
                    Toast.makeText(UsersInfo.this, "Failed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UsersInfo.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UsersInfo.this, Acc.id.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Sex = sex.getText().toString();
                String Dob = dob.getText().toString();
                String Phone = phone.getText().toString();

                Boolean result = myDB.updateUser(myDB.checkID("Users", Acc.id), Acc.id, Name, Sex, Dob, Phone);
                if (result == false){
                    Toast.makeText(UsersInfo.this, "Failed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UsersInfo.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = myDB.deleteUser(Acc.id);
                if (result == true){
                    Toast.makeText(UsersInfo.this, "Data deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UsersInfo.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}