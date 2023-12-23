package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AccountDB myDB;

    EditText name, sex, dob;

    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new AccountDB(this);

        name = findViewById(R.id.name);
        sex = findViewById(R.id.sex);
        dob = findViewById(R.id.dob);

        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Sex = sex.getText().toString();
                String Dob = dob.getText().toString();

                if (name.equals("") || name.equals("") || sex.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all info", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean result = myDB.insertUser(Name, Sex, Dob);
                    if (result == false){
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}