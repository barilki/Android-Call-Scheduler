/* Assignment: 1
Campus: SCE, Ashdod.
Author: Bar Ilan Kimbarovski, ID: 205618457
Author2: Shay Manasherov, ID: 311332597
*/
package com.example.callscheduler;

/**
 * Class contain validation functions
 */
public class Validation {

    //Check if name contain alphabet letters
    public static boolean nameValid(String name){
        return name.matches("[a-zA-Z]+");
    }

    //Check if time format is hh:mm
    public static boolean timeValid(String time) {
        return time.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]");
    }

    //Check if phone length is 10 numbers, phone must start with 05
    public static boolean phoneValid(String phone){
        return (phone.matches("05[0-9]+") && phone.length()==10);
    }


}
