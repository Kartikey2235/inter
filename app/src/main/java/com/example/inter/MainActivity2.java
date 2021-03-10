package com.example.inter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity {

    private EditText firstnameEdittext;
    private EditText lastnameEdittext;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    String email;
    String password;

    private Button continueAndNextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        inputValidation = new InputValidation(this);
        databaseHelper = new DatabaseHelper(this);
        user = new User();
        continueAndNextButton=findViewById(R.id.create_signUp_buttonsecond);
        firstnameEdittext=findViewById(R.id.createFirstName);
        lastnameEdittext=findViewById(R.id.createLastName);

        email=getIntent().getStringExtra("email");
        password=getIntent().getStringExtra("password");


        continueAndNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstnameEdittext.length()!=0&&lastnameEdittext.length()!=0) {
                    postDataToSQLite();
                    Intent intent = new Intent(MainActivity2.this, MainScreen.class);
                    intent.putExtra("Name", firstnameEdittext.getText().toString() + " "+lastnameEdittext.getText().toString());
                    intent.putExtra("email", email);
                    intent.putExtra("email", password);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity2.this, "Enter the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void postDataToSQLite() {

        if (!databaseHelper.checkUser(email)) {

            user.setName(firstnameEdittext.getText().toString()+lastnameEdittext.getText().toString());
            user.setEmail(email);
            user.setPassword(password);

            databaseHelper.addUser(user);

        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}