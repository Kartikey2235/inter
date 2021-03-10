package com.example.inter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText passwordEdittext;
    private EditText emailEditetext;
    private EditText confirmPasswordEdittext;
    private Button continueAndNextButton;
    private CardView gotosignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotosignin=findViewById(R.id.goToSignIn);

        gotosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        continueAndNextButton=findViewById(R.id.create_signUp_button);
        passwordEdittext=findViewById(R.id.createpassword);
        emailEditetext=findViewById(R.id.createemail);
        confirmPasswordEdittext=findViewById(R.id.createConfirmpassword);

        continueAndNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordEdittext.length()!=0&&emailEditetext.length()!=0) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("email", emailEditetext.getText().toString());
                    if (passwordEdittext.getText().toString().equals(confirmPasswordEdittext.getText().toString())) {
                        intent.putExtra("password", passwordEdittext.getText().toString());
                    } else {
                        Toast.makeText(MainActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Enter Email And Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}