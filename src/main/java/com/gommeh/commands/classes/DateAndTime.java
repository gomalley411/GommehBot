/*
 * Copyright (c)  2021 George O'Malley (also known as "Gommeh" or "gomalley411") <gomalley411@gmail.com>.
 * All rights reserved except where prohibited by law. You, the user, are free to edit,
 * manipulate or change any part of the code herein, as long as credit is given and
 * this copyright statement is left intact at the top of every file in the code.
 * If you violate this copyright, that is, use my code without permission, I, George
 * O'Malley, reserve the right to take appropriate action.
 *
 */

package com.gommeh.commands.classes;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class DateAndTime {
    private final LocalDateTime ldt;
    private final String datestr;

    public DateAndTime() {
        ldt = LocalDateTime.now();
        boolean ampm = false; // false if am, true if pm
        int hour = ldt.getHour();
        String ampmstr = "AM";
        if (hour >= 12) {
            hour -= 12;
            ampm = true;
            ampmstr = "PM";
        }
        StringBuilder month = new StringBuilder(ldt.getMonth().name().toLowerCase());
        char monthc = Character.toUpperCase(month.charAt(0));
        month.setCharAt(0, monthc);
        DecimalFormat df = new DecimalFormat("00");
        datestr = month + " " + ldt.getDayOfMonth() + ", " + ldt.getYear() +
                " at " + hour + ":" + df.format(ldt.getMinute()) + ampmstr;
    }

    public String getDateStr() {return datestr;}
}
