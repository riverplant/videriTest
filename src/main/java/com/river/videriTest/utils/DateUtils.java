package com.river.videriTest.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.expression.ParseException;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.util.StdDateFormat;

public class DateUtils {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    
 


    public static Date addOneDay(Date date) {
        return addDays(date, 1);
    }

    public static Date subOneDay(Date date) {
        return addDays(date, -1);
    }

    public static Date subOneMs(Date date) {
        return new Date(date.getTime() - 1);
    }

    public static Date addSeconds(Date date, int seconds) {
        if (date == null) {
            return null;
        }
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }
    
    public static Date addMinutes(Date date, int minutes) {
        if (date == null) {
            return null;
        }
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }
    
    public static Date addHours(Date date, int heurs) {
        if (date == null) {
            return null;
        }
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, heurs);
        return cal.getTime();
    }

    public static Date addDays(Date date, int days) {
        if (date == null) {
            return null;
        }
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date addMonths(Date date, int months) {
        if (date == null) {
            return null;
        }
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    public static Date addYears(Date date, int years) {
        if (date == null) {
            return null;
        }
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }
    
    public static Date tomorrow() {
        return DateUtils.addOneDay(new Date());
    }

    public static Date yesterday() {
        return DateUtils.subOneDay(new Date());
    }

    public static Date lastMonth() {
        return DateUtils.addMonths(new Date(), -1);
    }

   

    static public boolean nonNullAscending(Date... dates) {
        return nonNullAscending(Arrays.asList(dates));
    }

    static public boolean nonNullAscending(List<Date> dates) {
        if (dates.size() < 2) {
            throw new RuntimeException("This methods needs at least two dates");
        }
        for (int i = 1; i < dates.size(); i++) {
            Date date1 = dates.get(i - 1);
            Date date2 = dates.get(i);
            if (!nonNullAscending(date1, date2)) {
                return false;
            }
        }
        return true;
    }

    static public boolean nonNullAscending(Date lower, Date higher) {
        if (lower == null) {
            return false;
        }
        if (higher == null) {
            return false;
        }
        return lower.before(higher);
    }


    static public boolean nonNullAscendingOrEquals(Date... dates) {
        return nonNullAscendingOrEquals(Arrays.asList(dates));
    }

    static public boolean nonNullAscendingOrEquals(List<Date> dates) {
        if (dates.size() < 2) {
            throw new RuntimeException("This methods needs at least two dates");
        }
        for (int i = 1; i < dates.size(); i++) {
            Date date1 = dates.get(i - 1);
            Date date2 = dates.get(i);
            if (!nonNullAscendingOrEquals(date1, date2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if <tt>lower</tt> is before <tt>higher</tt>.
     * If any or the two arguments is null, returns false.
     */
    static public boolean nonNullAscendingOrEquals(Date lower, Date higher) {
        if (lower == null) {
            return false;
        }
        if (higher == null) {
            return false;
        }
        return lower.before(higher) || lower.getTime() == higher.getTime();
    }

    /**
     * @return true if (all nonNulls elems of dateList are ascending or equal) or (if there is less than two non null dates).
     */
    static public boolean ascendingOrEquals(Date... dateList) {
        return ascendingOrEquals(Arrays.asList(dateList));
    }

    static public boolean ascendingOrEquals(List<Date> dateList) {
        List<Date> dateList2 = new ArrayList<>();
        for (Date date : dateList) {
            if (date != null) {
                dateList2.add(date);
            }
        }

        if (dateList2.size()<2){
            return true;
        }
        return nonNullAscendingOrEquals(dateList2);
    }

    static public Date lowestNotNull(Date d1, Date d2){
        if (d1 == null && d2 == null){
            return null;
        }
        if (d1 == null){
            return d2;
        }
        if (d2 == null){
            return d1;
        }
        if (d1.before(d2)){
            return d1;
        }
        return d2;
    }

    static public String formatYmdKm(Date date){
        return new SimpleDateFormat("yyyy-MM-dd kk:mm").format(date);
    }

    static public String formatYmdHm(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }
    
    static public String formatYmds(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(date);
    }
    
    static public Date parseYmdKm(String date) throws java.text.ParseException{
        try {
            return new SimpleDateFormat("yyyy-MM-dd kk:mm").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    static public Date parseYmd(String date) throws java.text.ParseException{
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    static public String formatYmd(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    
    static public String formatHm(Date date){
        return new SimpleDateFormat("HH:mm").format(date);
    }

    public static Date min(Date date1, Date date2) {
        if (date1 == null) {
            return date1;
        }
        if (date2 == null) {
            return date2;
        }
        if (date1.before(date2)) {
            return date1;
        }
        return date2;
    }

    public static Date max(Date date1, Date date2) {
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        if (date1.after(date2)) {
            return date1;
        }
        return date2;
    }


    
    public static boolean isDateValidWithOneHourGraceTime(final Date date) {
        if (date == null) {
            return true;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, 60);
        return c.getTime().after(new Date());
    }
    
    public static boolean isBetween(Date aDate, Date dateStart, Date dateEnd) {
        if (dateStart == null || dateEnd == null) {
            throw new IllegalArgumentException();
        }
        if (aDate == null) {
            return false;
        }
        return (aDate.after(dateStart) && aDate.before(dateEnd));
    }

    @Deprecated
    /**
     * Use DateTimeHelper.convertToMtlDateString
     */
    public static String formatToISO8601(Date mtlDate) {
        if (mtlDate == null) {
            return "";
        }
        try {
            final StdDateFormat df = new StdDateFormat();
            return df.format(mtlDate);
        } catch (Exception e) {
            return "";
        }
    }
    
    public static Date parseToISO8601(String dateString) throws java.text.ParseException {
        try {
            final StdDateFormat df = new StdDateFormat();
            return df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String formatDateFromSourceToDestination(final String date, final String sourcePatternFormatter, final String destinationPatternFormatter) {
        try {
            SimpleDateFormat sourceDateFormat = new SimpleDateFormat(sourcePatternFormatter);
            SimpleDateFormat destinationDateFormat = new SimpleDateFormat(destinationPatternFormatter); 
            Date d = sourceDateFormat.parse(date);
            return destinationDateFormat.format(d);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Date parseDate(String dateString) throws java.text.ParseException {
        try {
            final DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
           return null;
        }
    }
    
    public static Date parseDatetime(String dateString) throws java.text.ParseException {
        try {
            final DateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
           return null;
        }
    }
    
    public static String formatToDatetime(Date date) {
        return new SimpleDateFormat(DATETIME_FORMAT).format(date);
    }
    
    public static int getMinutesFromDuration(String duration) {
        float minutes = 0;
        duration = StringUtils.replace(duration,",", ".");
        duration = duration.toLowerCase();
        if (duration.contains("w")) {
            String weeks = duration.substring(0, duration.indexOf('w'));
            minutes = Float.parseFloat(weeks) * 7 * 24 * 60;
        }
        
        if (duration.contains("d")) {
            String days;
            if (duration.contains("w")) {
                days = duration.substring(duration.indexOf('w') + 1, duration.indexOf('d'));                 
            } else {
                days = duration.substring(0, duration.indexOf('d'));                                 
            }
            minutes += Float.parseFloat(days) * 24 *60;
        }        
        
        if (duration.contains("h")) {
            String hours;
            if (duration.contains("d")) {
                hours = duration.substring(duration.indexOf('d') + 1, duration.indexOf('h'));                 
            } else if (duration.contains("w")) {
                hours = duration.substring(duration.indexOf('w') + 1, duration.indexOf('h'));              
            } else {
                hours = duration.substring(0, duration.indexOf('h'));              
            }
            minutes += Float.parseFloat(hours) * 60;
        }
        
        if (duration.contains("m")) {
            String m ;
            if (duration.contains("h")) {
                m =  duration.substring(duration.indexOf('h') + 1, duration.indexOf('m'));
            } else if (duration.contains("d")) {
                m =  duration.substring(duration.indexOf('d') + 1, duration.indexOf('m'));
            } else if (duration.contains("w")){
                m =  duration.substring(duration.indexOf('m') + 1, duration.indexOf('m'));
            } else {
                m =  duration.substring(0, duration.indexOf('m'));                
            }
            minutes += Float.parseFloat(m) ;
        }
        return (int)minutes;
    }
   
    public static String getDurationFromMinutes(int totalMinutes) {
        StringBuilder sb = new StringBuilder("0");
        int weeks = totalMinutes / (24*7*60);
        int leftMinutes = totalMinutes % (24*7*60);
        int days = leftMinutes / (24*60);
        leftMinutes = leftMinutes % (24*60);
        int hours = leftMinutes / (60);
        int minutes = leftMinutes % 60;
        if (weeks > 0) {
            sb.append(weeks+"w");
        }
        if (days > 0) {
            sb.append(days+"d");
        }
        if (hours > 0) {
            sb.append(hours+"h");
        }
        if (minutes > 0) {
            sb.append(minutes+"m");
        }
        return sb.toString();
    }
    
 
    
    public static int daysBetween(Date date1, Date date2) {
        Calendar day1 = Calendar.getInstance();
        Calendar day2 = Calendar.getInstance();
        day1.setTime(date1);
        day2.setTime(date2);

        return  Math.abs((int)TimeUnit.DAYS.convert(day1.getTimeInMillis() - day2.getTimeInMillis(), TimeUnit.MILLISECONDS));
    }
    
    public static Date setTime(Date date, int hours, int minutes, int seconds) {
        if (date == null) {
            return null;
        }
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, seconds);
        return cal.getTime();
    }
    
}