package q183272;

package com.energy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DailyUsage {

    private Date date;
    private double usage;
    private Calendar c = Calendar.getInstance();

    public DailyUsage(String dateStr, double usage) throws ParseException {

        Date formattedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        this.date = formattedDate;
        this.usage = usage;
    }

    public int getMonth() {

        c.setTime(date);
        return c.get(Calendar.MONTH);
    }

    public String getMonthName() {

        c.setTime(date);
        String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        return month;
    }

    public double getUsage() {
        return usage;
    }

    public int getYear() {
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return year;        
    }
}
