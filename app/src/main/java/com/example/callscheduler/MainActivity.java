/* Assignment: 1
Campus: SCE, Ashdod.
Author: Bar Ilan Kimbarovski, ID: 205618457
Author2: Shay Manasherov, ID: 311332597
*/
package com.example.callscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText etName1, etPhone1, etStartTime1, etEndTime1;
    EditText etName2, etPhone2, etStartTime2, etEndTime2;
    EditText etName3, etPhone3, etStartTime3, etEndTime3;
    Button addBtn;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.button_id);

        //User 1
        etName1 = findViewById(R.id.name1);
        etPhone1 = findViewById(R.id.phone1);
        etStartTime1 = findViewById(R.id.startTime1);
        etEndTime1 = findViewById(R.id.endTime1);

        //User 2
        etName2 = findViewById(R.id.name2);
        etPhone2 = findViewById(R.id.phone2);
        etStartTime2 = findViewById(R.id.startTime2);
        etEndTime2 = findViewById(R.id.endTime2);

        //User 3
        etName3 = findViewById(R.id.name3);
        etPhone3 = findViewById(R.id.phone3);
        etStartTime3 = findViewById(R.id.startTime3);
        etEndTime3 = findViewById(R.id.endTime3);

        addBtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if (v == addBtn) {

                                              String name1 = etName1.getText().toString(), name2 = etName2.getText().toString(), name3 = etName3.getText().toString();
                                              String phone1 = etPhone1.getText().toString(), phone2 = etPhone2.getText().toString(), phone3 = etPhone3.getText().toString();
                                              String startTime1 = etStartTime1.getText().toString(), startTime2 = etStartTime2.getText().toString(), startTime3 = etStartTime3.getText().toString();
                                              String endTime1 = etEndTime1.getText().toString(), endTime2 = etEndTime2.getText().toString(), endTime3 = etEndTime3.getText().toString();

                                              //Check validation for user 1
                                              if (!Validation.nameValid(name1) || !Validation.phoneValid(phone1) || !Validation.timeValid(startTime1) || !Validation.timeValid(endTime1)) {
                                                  Toast.makeText(getApplicationContext(), "User1: incorrect value, Try again!", Toast.LENGTH_SHORT).show();
                                                  return;
                                              }
                                              //Check validation for user 2
                                              if (!Validation.nameValid(name2) || !Validation.phoneValid(phone2) || !Validation.timeValid(startTime2) || !Validation.timeValid(endTime2)) {
                                                  Toast.makeText(getApplicationContext(), "User2: incorrect value, Try again!", Toast.LENGTH_SHORT).show();
                                                  return;
                                              }
                                              //Check validation for user 3
                                              if (!Validation.nameValid(name3) || !Validation.phoneValid(phone3) || !Validation.timeValid(startTime3) || !Validation.timeValid(endTime3)) {
                                                  Toast.makeText(getApplicationContext(), "User3: incorrect value, Try again!", Toast.LENGTH_SHORT).show();
                                                  return;
                                              }

                                              //Check if data is equal
                                              if (name1.equals(name2) || name1.equals(name3) || name2.equals(name3)) {
                                                  Toast.makeText(getApplicationContext(), "Some of the data is equal, Try again!", Toast.LENGTH_SHORT).show();
                                                  return;
                                              }
                                              if (phone1.equals(phone2) || phone1.equals(phone3) || phone2.equals(phone3)) {
                                                  Toast.makeText(getApplicationContext(), "Some of the data is equal, Try again!", Toast.LENGTH_SHORT).show();
                                                  return;
                                              }

                                              //Save user 1
                                              SharedPref.saveString(getApplicationContext(), "contact", "name1", name1);
                                              SharedPref.saveString(getApplicationContext(), "contact", "phone1", phone1);
                                              SharedPref.saveString(getApplicationContext(), "contact", "startTime1", startTime1);
                                              SharedPref.saveString(getApplicationContext(), "contact", "endTime1", endTime1);


                                              //Save user 2
                                              SharedPref.saveString(getApplicationContext(), "contact", "name2", name2);
                                              SharedPref.saveString(getApplicationContext(), "contact", "phone2", phone2);
                                              SharedPref.saveString(getApplicationContext(), "contact", "startTime2", startTime2);
                                              SharedPref.saveString(getApplicationContext(), "contact", "endTime2", endTime2);

                                              //Save user 3
                                              SharedPref.saveString(getApplicationContext(), "contact", "name3", name3);
                                              SharedPref.saveString(getApplicationContext(), "contact", "phone3", phone3);
                                              SharedPref.saveString(getApplicationContext(), "contact", "startTime3", startTime3);
                                              SharedPref.saveString(getApplicationContext(), "contact", "endTime3", endTime3);

                                              //Navigate to another activity
                                              Intent pCall = new Intent(getBaseContext(), MainActivity2.class);
                                              //passing users to second activity
                                              pCall.putExtra("User1", name1 + " Available from: " + startTime1 + " Until: " + endTime1);
                                              pCall.putExtra("User2", name2 + " Available from: " + startTime2 + " Until: " + endTime2);
                                              pCall.putExtra("User3", name3 + " Available from: " + startTime3 + " Until: " + endTime3);
                                              startActivity(pCall);
                                          }
                                      }


                                  }


        );

        //Set Picker time on edit text, start time 1
        etStartTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == etStartTime1) {
                    TimePicker.createTimePicker(MainActivity.this, etStartTime1);
                }

            }
        });

        //Set Picker time on edit text, start time 2
        etStartTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == etStartTime2) {
                    TimePicker.createTimePicker(MainActivity.this, etStartTime2);
                }

            }
        });

        //Set Picker time on edit text, start time 3
        etStartTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == etStartTime3) {
                    TimePicker.createTimePicker(MainActivity.this, etStartTime3);
                }

            }
        });

        //Set Picker time on edit text, end time 1
        etEndTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == etEndTime1) {
                    TimePicker.createTimePicker(MainActivity.this, etEndTime1);
                }

            }
        });

        //Set Picker time on edit text, end time 2
        etEndTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == etEndTime2) {
                    TimePicker.createTimePicker(MainActivity.this, etEndTime2);
                }

            }
        });

        //Set Picker time on edit text, end time 3
        etEndTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == etEndTime3) {
                    TimePicker.createTimePicker(MainActivity.this, etEndTime3);
                }

            }
        });


    }


}