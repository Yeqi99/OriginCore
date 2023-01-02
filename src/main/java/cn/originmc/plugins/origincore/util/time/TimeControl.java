package cn.originmc.plugins.origincore.util.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeControl {
    public static String getStringTimeNow(String format){
        Calendar calendardate= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat(format);
        return dateFormat.format(calendardate.getTime());
    }
    public static boolean atNow(String format,String time){
        if(getStringTimeNow(format).equals(time)){
            return true;
        }
        return false;
    }
    public static Calendar getCalendar(String str,String format){
        SimpleDateFormat sdf= new SimpleDateFormat(format);
        Date date;
        try {
            date =sdf.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    public static String calendarToStr(Calendar calendar,String format){
        SimpleDateFormat sdf= new SimpleDateFormat(format);
        return sdf.format(calendar.getTime());
    }
    public static boolean inSection(String start,String end,String now,String format){
        Calendar a=getCalendar(start,format);
        Calendar b=getCalendar(end,format);
        Calendar c=getCalendar(now,format);
        return a.before(c) & b.after(c);
    }
    public static boolean delay(long time){
        try {
            Thread.sleep(time);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
