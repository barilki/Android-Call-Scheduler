/* Assignment: 1
Campus: SCE, Ashdod.
Author: Bar Ilan Kimbarovski, ID: 205618457
Author2: Shay Manasherov, ID: 311332597
*/
package com.example.callscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    EditText etName, etTimeToCall;
    TextView user1, user2, user3;
    String name, phone, time;
    TextView logo;
    Button addBtn;
    String startTime1;
    long difference = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addBtn = findViewById(R.id.button_id);
        logo = findViewById(R.id.logo);
        logo.setShadowLayer(15, 0, 0, Color.LTGRAY);

        startTime1 = SharedPref.getStr(getApplicationContext(), "contact", "startTime1");

        user1 = findViewById(R.id.user1);
        user2 = findViewById(R.id.user2);
        user3 = findViewById(R.id.user3);

        //User 1
        etName = findViewById(R.id.name1);
        etTimeToCall = findViewById(R.id.timeToCall1);

        //Show the users on the screen from the first activity
        user1.setText(getIntent().getStringExtra("User1"));
        user2.setText(getIntent().getStringExtra("User2"));
        user3.setText(getIntent().getStringExtra("User3"));


        addBtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          //Save user 1
                                          SharedPref.saveString(getApplicationContext(), "contact", "nameCall", etName.getText().toString());
                                          SharedPref.saveString(getApplicationContext(), "contact", "timeToCall", etTimeToCall.getText().toString());

                                          name = SharedPref.getStr(MainActivity2.this, "contact", "nameCall");
                                          time = SharedPref.getStr(MainActivity2.this, "contact", "timeToCall");

                                          if (v == addBtn) {


                                              if (!Validation.nameValid(name)) {
                                                  Toast.makeText(getApplicationContext(), "name: " + name + " is not valid", Toast.LENGTH_SHORT).show();
                                                  return;
                                              }


                                              try {
                                                  if (isValid("name1", "startTime1", "endTime1")) {
                                                      Log.d("Test", "isValid function");
                                                      phone = SharedPref.getStr(MainActivity2.this, "contact", "phone1");
                                                      onTimeSet();
                                                      addBtn.setBackgroundColor(0xff007fff);
                                                      return;
                                                  }
                                                  if (isValid("name2", "startTime2", "endTime2")) {
                                                      phone = SharedPref.getStr(MainActivity2.this, "contact", "phone2");
                                                      onTimeSet();
                                                      addBtn.setBackgroundColor(0xff007fff);
                                                      return;
                                                  }
                                                  if (isValid("name3", "startTime3", "endTime3")) {
                                                      phone = SharedPref.getStr(MainActivity2.this, "contact", "phone3");
                                                      onTimeSet();
                                                      addBtn.setBackgroundColor(0xff007fff);
                                                      return;
                                                  }


                                              } catch (ParseException e) {
                                                  e.printStackTrace();
                                              }

                                          }


                                      }

                                  }

        );


        //Set Picker time on edit text, time to call 1
        etTimeToCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == etTimeToCall) {
                    TimePicker.createTimePicker(MainActivity2.this, etTimeToCall);
                }

            }
        });


    }

    //Check if the time is between
    public boolean checkTime(String start, String end, String current) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date_from = formatter.parse(start);
        Date date_to = formatter.parse(end);
        Date dateNow = formatter.parse(current);
        if (date_from.before(dateNow) && date_to.after(dateNow)) {
            //return true if the time is between
            return true;
        }
        if (date_to.equals(dateNow) || date_from.equals(dateNow)) {
            //return true if the time is equal to the user time
            return true;
        }
        return false;

    }

    public boolean isValid(String nameV, String startTimeV, String endTimeV) throws ParseException {

        String nameS = SharedPref.getStr(MainActivity2.this, "contact", nameV);
        String startTimeS = SharedPref.getStr(MainActivity2.this, "contact", startTimeV);
        String endTimeS = SharedPref.getStr(MainActivity2.this, "contact", endTimeV);
        Log.d("Check time function", "" + checkTime(startTimeS, endTimeS, time));
        Log.d("Check name EQUAL", "" + name.equals(nameS));

        if (nameS.equals(name) && checkTime(startTimeS, endTimeS, time)) {
            return true;
        }
        else {
            addBtn.setBackgroundColor(Color.RED);
            return false;
        }

    }

    //using intent for make a call phone
    public void makeCall(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    //Set timer for call phone
    public void onTimeSet() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            //get current time in millisecond
            final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            String timeD = dateFormat.format(new Date());
            Date date1 = sdf.parse(timeD);
            long deviceTime = date1.getTime();

            //get user time in millisecond
            Date date2 = sdf.parse(time);
            long userTime = date2.getTime();
            Log.d("User:", "" + userTime);

            difference = userTime - deviceTime;
            if (difference < 0) {
                difference = userTime;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                makeCall(phone);
            }
        }, difference);

        if (difference >= 300000) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Calling: " + name + " In 5 minutes", Toast.LENGTH_SHORT).show();
                }
            }, difference - 300000);
        }
    }

}