package com.example.inter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText passwordEdittext;
    private EditText emailEditetext;
    private Button continueAndNextButton;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        continueAndNextButton=findViewById(R.id.create_Login_button);
        passwordEdittext=findViewById(R.id.loginpassword);
        emailEditetext=findViewById(R.id.logincreateemail);

        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);

        continueAndNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyFromSQLite();
            }
        });
    }
    private void verifyFromSQLite() {

        if (databaseHelper.checkUser(emailEditetext.getText().toString().trim()
                , passwordEdittext.getText().toString().trim())) {


            Intent accountsIntent = new Intent(LoginActivity.this, MainScreen.class);
            accountsIntent.putExtra("email", emailEditetext.getText().toString().trim());
            startActivity(accountsIntent);


        } else {
            Toast.makeText(this, "Enter valid email and password", Toast.LENGTH_SHORT).show();
        }
    }

}