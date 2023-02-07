package com.example.palyerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import Model.Activity;
import data.MyDbHandler;

public class UserActivity extends AppCompatActivity {

    private String yr,mon,dy;
    EditText disDate,Fajar,Zohar,Asar,Maghrib,Aisha,UP_Fajar,UP_Zohar,UP_Asar,UP_Maghrib,UP_Aisha;
    Button btn_show,btn_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btn_date=(Button) findViewById(R.id.select_date);
        btn_show=(Button) findViewById(R.id.fetch_btn);

        disDate=(EditText) findViewById(R.id.date);
        Fajar=(EditText) findViewById(R.id.fajar);
        Zohar=(EditText) findViewById(R.id.zohar);
        Asar=(EditText) findViewById(R.id.asar);
        Maghrib=(EditText) findViewById(R.id.maghrib);
        Aisha=(EditText) findViewById(R.id.aisha);



        UP_Fajar=(EditText) findViewById(R.id.Up_fajar);
        UP_Zohar=(EditText) findViewById(R.id.Up_zohar);
        UP_Asar=(EditText) findViewById(R.id.Up_asar);
        UP_Maghrib=(EditText) findViewById(R.id.Up_maghrib);
        UP_Aisha=(EditText) findViewById(R.id.Up_aisha);



        final Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DATE);

        Intent intent = getIntent();
        int Usr_ID = intent.getIntExtra("variableName", 0);



        MyDbHandler db=new MyDbHandler(UserActivity.this);


        disDate.setText(dy + " / " + mon + " / " + yr);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UserActivity.this,dateListener,year,month,day).show();
            }
        });


        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity newActivity=db.getActivity(Usr_ID,disDate.getText().toString());

                if(newActivity==null)
                {
                    Fajar.setText("Not Offered");
                    Zohar.setText("Not Offered");
                    Asar.setText("Not Offered");
                    Maghrib.setText("Not Offered");
                    Aisha.setText("Not Offered");
                }else
                {
                    if(newActivity.isFajar()==0)
                        Fajar.setText("Not Offered");
                    else
                        Fajar.setText("Offered");

                    if(newActivity.isZohar()==0)
                        Fajar.setText("Not Offered");
                    else
                        Fajar.setText("Offered");

                    if(newActivity.isAsar()==0)
                        Fajar.setText("Not Offered");
                    else
                        Fajar.setText("Offered");


                    if(newActivity.isMaghrib()==0)
                        Fajar.setText("Not Offered");
                    else
                        Fajar.setText("Offered");


                    if(newActivity.isAisha()==0)
                        Fajar.setText("Not Offered");
                    else
                        Fajar.setText("Offered");

                }


            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            yr=Integer.toString(year);
            mon=Integer.toString(month);
            dy=Integer.toString(dayOfMonth);

            disDate.setText(dy + " / " + mon + " / " + yr);
        }
    };
}