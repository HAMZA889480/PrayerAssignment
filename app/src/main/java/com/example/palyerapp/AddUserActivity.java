package com.example.palyerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.users;
import data.MyDbHandler;

public class AddUserActivity extends AppCompatActivity {

    Button add_btn;
    EditText new_name,new_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        new_name=(EditText) findViewById(R.id.login);
        new_password=(EditText) findViewById(R.id.password);
        add_btn=(Button) findViewById(R.id.add_usr);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUser=new_name.getText().toString();
                String newPassword=new_password.getText().toString();

                MyDbHandler db=new MyDbHandler(AddUserActivity.this);
                if (newUser.equals("") || newPassword.equals("")) {
                    Toast.makeText(AddUserActivity.this, "Provide Details", Toast.LENGTH_SHORT).show();
                }
                else if(db.verifyLogin(newUser,newPassword))
                    Toast.makeText(AddUserActivity.this, "User Name exists", Toast.LENGTH_SHORT).show();
                else
                {
                    //Creating user object
                    users user=new users(newUser,newPassword);

                    //Adding user to data base
                    db.addUser(user);
                    Toast.makeText(AddUserActivity.this, "User Added!!!", Toast.LENGTH_SHORT).show();
                }












            }
        });

    }
}