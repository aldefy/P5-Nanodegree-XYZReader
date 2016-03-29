package com.example.xyzreader.utils;

import android.text.format.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatterBuilder;


/**
 * Created by aditlal on 26/03/16.
 */
public class Utils {
    public final static String DATA_MODEL ="dataModel";
    public final static String IMAGE_URL ="imageURL";
    public final static String LIST_SAVE_INSTANCE ="save_instance_list";
    public final static String MODEL_SAVE_INSTANCE ="save_instance_model";
    public final static String PHOTO_URL_SAVE_INSTANCE ="save_instance_photo_url";




    public static DateTime parseDateTimeFromString(String input) {
        //Pattern for date  =  2013-06-20T00:00:00.000Z
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        return DateTime.parse(input, DateTimeFormat.forPattern(pattern));
    }

    public static String toRelativeTime(final DateTime dateTime) {
        long nowLngTime = System.currentTimeMillis();
        DateTime now = new DateTime();
        Logger.d("AGO", now.toString());
        long difference = Math.abs(dateTime.getMillis() - now.getMillis());
        Logger.d("AGO", "Difference is " + difference);

        Period period = new Period(dateTime, now);
        PeriodFormatterBuilder formatterBuilder = new PeriodFormatterBuilder();
        if (difference > DateUtils.YEAR_IN_MILLIS) {
            formatterBuilder.appendYears().appendSuffix(" year");
        } else if (difference > DateUtils.DAY_IN_MILLIS * 30) {
            formatterBuilder.appendMonths().appendSuffix(" month");
        } else if (difference > DateUtils.WEEK_IN_MILLIS) {
            formatterBuilder.appendWeeks().appendSuffix(" week");
        } else if (difference > DateUtils.DAY_IN_MILLIS) {
            formatterBuilder.appendDays().appendSuffix(" day");
        } else if (difference > DateUtils.HOUR_IN_MILLIS) {
            formatterBuilder.appendHours().appendSuffix(" hour");
        } else if (difference > DateUtils.MINUTE_IN_MILLIS) {
            formatterBuilder.appendMinutes().appendSuffix(" minute");
        } else if (difference > DateUtils.SECOND_IN_MILLIS) {
            formatterBuilder.appendSeconds().appendSuffix(" sec");
        }
        String ends = formatterBuilder.printZeroNever().toFormatter().print(period);
        String plural = ends.startsWith("1 ") ? "" : "s";
        ends = ends + plural + " " + "ago";
        Logger.d("Elapsed", ends);
        Logger.d("Elapsed", PeriodFormat.getDefault().print(period));
        return ends;
    }
}
