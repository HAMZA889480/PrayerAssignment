package com.example.palyerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.MyDbHandler;


public class MainActivity extends AppCompatActivity {

    EditText user_name, Password;
    Button add_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_student = (Button) findViewById(R.id.buttonLogin);
        user_name = (EditText) findViewById(R.id.login);
        Password = (EditText) findViewById(R.id.password);


        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String User = user_name.getText().toString();
                String password = Password.getText().toString();

                if (User.equals("") || password.equals("")) {
                    Toast.makeText(MainActivity.this, "Provide Details", Toast.LENGTH_SHORT).show();
                } else if ((User.equals("admin") || password.equals("admin"))) {

                    Intent adminIntent=new Intent(getApplicationContext(),AdminActivity.class);
                    startActivity(adminIntent);
                    Toast.makeText(MainActivity.this, "Admin Login", Toast.LENGTH_SHORT).show();
                } else {

                    MyDbHandler db=new MyDbHandler(MainActivity.this);

                    if(db.verifyLogin(User,password))
                    {
                        int login_id=db.getUserId(User,password);
                        Intent userIntent=new Intent(getApplicationContext(),UserActivity.class);

                        //Passing login user id to User Activity
                        userIntent.putExtra("user_id",login_id);
                        startActivity(userIntent);
                        Toast.makeText(MainActivity.this, "User Login", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                    }



                }

            }


        });


    }
}