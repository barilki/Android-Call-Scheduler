/* Assignment: 1
Campus: SCE, Ashdod.
Author: Bar Ilan Kimbarovski, ID: 205618457
Author2: Shay Manasherov, ID: 311332597
*/
package com.example.callscheduler;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Class that create a Time picker object
 */
public class TimePicker {

    public static void createTimePicker(Context c, EditText text) {
        Calendar systemCalendar = Calendar.getInstance();
        int hour = systemCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = systemCalendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(c, new SetTime(text), hour, minute, true);
        timePickerDialog.show();
    }


}

