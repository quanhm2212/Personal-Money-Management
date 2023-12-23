package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    Button register, reSignIn;
    EditText emailSignUp, passwordSignUp, Confirm;
    AccountDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailSignUp = (EditText) findViewById(R.id.EmailSignUp);
        passwordSignUp = (EditText) findViewById(R.id.PasswordSignUp);
        Confirm = (EditText) findViewById(R.id.Confirm);

        register = (Button) findViewById(R.id.btnCreateAcc);
        reSignIn = (Button) findViewById(R.id.btnReSignIn);

        myDB = new AccountDB(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailSignUp.getText().toString();
                String password = passwordSignUp.getText().toString();
                String confirm = Confirm.getText().toString();

                if (email.equals("") || password.equals("") || confirm.equals("")){
                    Toast.makeText(SignUp.this, "Please enter all info", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (password.equals(confirm)){
                        Boolean checkEmailResult = myDB.checkEmail(email);
                        if(checkEmailResult == false){
                            Boolean result = myDB.insertData(email, password);
                            if (result == true){
                                Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignUp.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignUp.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        reSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
            }
        });
    }
}