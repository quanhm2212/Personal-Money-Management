package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    Button btnSignIn, btnSignUp;
    EditText email, password;
    AccountDB myDB;
    Pointer Acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);

        Acc = new Pointer();

        myDB = new AccountDB(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString();
                String pass = password.getText().toString();

                if (em.equals("") || pass.equals("")){
                    Toast.makeText(SignIn.this, "Please enter all info", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean result = myDB.checkPassword(em, pass);
                    if (result == true){
                        Acc.email = myDB.getEmail(em, pass);
                        Boolean temp = myDB.checkEmail("Users", em);
                        if (temp == false){
                            myDB.insertUser(Acc.email, "No data", "No data", 0);
                        }
                        Acc.userID = myDB.getUserID(Acc.email);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(SignIn.this, "Invalid info", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }
}