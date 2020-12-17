/* Assignment: 1
Campus: SCE, Ashdod.
Author: Bar Ilan Kimbarovski, ID: 205618457
Author2: Shay Manasherov, ID: 311332597
*/
package com.example.callscheduler;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 *Class contain functions to Create shared preference object
 */

public class SharedPref {

    private SharedPref setting;

    //Specifying preference file and Editing preference
    public static void saveString(Context c,String prefName, String key, String value) {
        SharedPreferences settings = c.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //Accessing stored data from Shared preference
    public static String getStr(Context c,String prefName, String key) {
        SharedPreferences settings = c.getSharedPreferences(prefName, 0);
        return settings.getString(key,"null");
    }

}