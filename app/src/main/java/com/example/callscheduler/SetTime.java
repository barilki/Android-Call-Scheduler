/* Assignment: 1
Campus: SCE, Ashdod.
Author: Bar Ilan Kimbarovski, ID: 205618457
Author2: Shay Manasherov, ID: 311332597
*/
package com.example.callscheduler;

import android.app.TimePickerDialog;
import android.widget.EditText;
import android.widget.TimePicker;

//Save time inside time picker object

/**
 * The class contains a function that display information about the time.
 */
public class SetTime implements TimePickerDialog.OnTimeSetListener {
    EditText text;

    SetTime(EditText txt) {
        this.text = txt;
    }

    @Override
    public void onTimeSet(TimePicker view,
                          int hourOfDay, int minute) {
        String str = String.format("%02d:%02d", hourOfDay, minute);
        text.setText(str);
    }
}