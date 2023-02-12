package com.example.palyerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import Model.Activity;
import data.MyDbHandler;

public class UserActivity extends AppCompatActivity {

    private String yr,mon,dy;
    EditText disDate,Fajar,Zohar,Asar,Maghrib,Aisha,UP_Fajar,UP_Zohar,UP_Asar,UP_Maghrib,UP_Aisha;
    Button btn_show,btn_date,btn_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btn_date=(Button) findViewById(R.id.select_date);
        btn_show=(Button) findViewById(R.id.fetch_btn);
        btn_update=(Button) findViewById(R.id.UpdateBtn);
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
                    if(newActivity.isFajar()==-99)
                        Fajar.setText("Not Offered");
                    else
                        Fajar.setText("Offered");

                    if(newActivity.isZohar()==-99)
                        Fajar.setText("Not Offered");
                    else
                        Fajar.setText("Offered");

                    if(newActivity.isAsar()==-99)
                        Fajar.setText("Not Offered");
                    else
                        Fajar.setText("Offered");


                    if(newActivity.isMaghrib()==-99)
                        Fajar.setText("Not Offered");
                    else
                        Fajar.setText("Offered");


                    if(newActivity.isAisha()==-99)
                        Fajar.setText("Not Offered");
                    else
                        Fajar.setText("Offered");

                }


            }
        });




        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String up_fajar,up_zohar, up_asar, up_maghrib, up_aisha;
                up_fajar=UP_Fajar.getText().toString();
                up_zohar=UP_Zohar.getText().toString();
                up_asar=UP_Asar.getText().toString();
                up_maghrib=UP_Maghrib.getText().toString();
                up_aisha=UP_Aisha.getText().toString();


                //All update fields are empty
                if(up_fajar.equals("") && up_zohar.toString().equals("") && up_asar.equals("")
                && up_maghrib.equals("") && up_aisha.equals(""))
                {
                    Toast.makeText(UserActivity.this, "Provide Updates!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Setting with default values
                    int is_up_fajar=-99;
                    int is_up_zohar=-99;
                    int is_up_asar=-99;
                    int is_up_maghrib=-99;
                    int is_up_aisha=-99;

                    if(!(up_fajar.equals("")))
                    {
                        if(up_fajar.equals("Done") ||up_fajar.equals("done")) {
                            is_up_fajar = 1;
                            Fajar.setText("Offered");
                        }
                    }

                    if(!(up_zohar.equals("")))
                    {
                        if(up_zohar.equals("Done") ||up_zohar.equals("done")) {
                            is_up_zohar = 1;
                            Zohar.setText("Offered");
                        }
                    }
                    if(!(up_asar.equals("")))
                    {
                        if(up_asar.equals("Done") ||up_asar.equals("done")) {
                            is_up_asar = 1;
                            Asar.setText("Offered");
                        }
                    }
                    if(!(up_maghrib.equals("")))
                    {
                        if(up_maghrib.equals("Done") ||up_maghrib.equals("done")) {
                            is_up_maghrib = 1;
                            Maghrib.setText("Offered");
                        }
                    }
                    if(!(up_aisha.equals("")))
                    {
                        if(up_aisha.equals("Done") ||up_aisha.equals("done")) {
                            is_up_aisha = 1;
                            Aisha.setText("Offered");
                        }
                    }

                    //Now create activity object
                    Activity up_activity=new Activity(disDate.getText().toString(),
                            is_up_fajar,is_up_zohar,is_up_asar,is_up_maghrib,is_up_aisha,
                            Usr_ID);

                    db.updateActivity(up_activity,Usr_ID,disDate.getText().toString());

                    Toast.makeText(UserActivity.this, "Record Updated!!!", Toast.LENGTH_SHORT).show();
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