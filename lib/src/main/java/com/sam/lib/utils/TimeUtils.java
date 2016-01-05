package com.sam.lib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_HOUR_MINUTE    = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_DATE_2    = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_DATE_DOT    = new SimpleDateFormat("yyyy.MM.dd");
    public static final SimpleDateFormat DATE_FORMAT_DATE_DOT_2    = new SimpleDateFormat("MM.dd");
    public static final SimpleDateFormat DATE_FORMAT_DATE_DOT_3    = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    /**
     * 秒转毫秒
     */
    public static final int SECOUND_TO_MILLS = 1000;
    /**
     * 1天 = 86400000毫秒
     */
    public static final int DAY_TO_MILLS = 86400000;

    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    public static String getTimeFormSecond(long seconds){
        return getTime(seconds * SECOUND_TO_MILLS);
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }


    /**
     * 转换成昨天、今天的格式
     * @param time
     * @return
     */
    private String formatDataYesterday(long time) {
        Date newTime = new Date();
        Date oldTime =  new Date(time);
        // 将下面的 理解成 yyyy-MM-dd 00：00：00 更好理解点
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = format.format(newTime);
        Date today = null;
        try {
            today = format.parse(todayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 昨天 86400000=24*60*60*1000 一天
        if ((today.getTime() - oldTime.getTime()) > 0
                && (today.getTime() - oldTime.getTime()) <= 86400000) {
            return "昨天";
        } else if ((today.getTime() - oldTime.getTime()) <= 0) { // 今天
            return getTime(time, DATE_FORMAT_HOUR_MINUTE);
        } else { // 前天以前
            return getTime(time, DATE_FORMAT_DATE);
        }
    }


    /**
     * 将毫秒转成HHmmss
     */
    public static String timeFormatByMillisecond(long time){
        StringBuffer sb = new StringBuffer();
        long second = time/SECOUND_TO_MILLS;
        long minute = second/60;
        long hour = minute/60;
        if(hour<10){
            sb.append("0");
        }
        sb.append(hour);
        sb.append(":");
        long min = minute % 60;
        if(min<10){
            sb.append("0");
        }
        sb.append(min);
        sb.append(":");
        long sec = second % 60;
        if(sec<10){
            sb.append("0");
        }
        sb.append(sec);
        return sb.toString();
    }

    /**
     * 将毫秒转成 天 00时00分00秒
     */
    public static String timeFormatCnByMillisecond(long time){
        StringBuffer sb = new StringBuffer();
        long second = time/SECOUND_TO_MILLS;
        long minute = second/60;
        long hour = minute/60;
        long day = hour/24;
        if(day > 0){
            sb.append(day);
            sb.append("天");
        }
        long hh = hour%24;
        if(hh<10){
            sb.append("0");
        }
        sb.append(hh);
        sb.append("时");
        long min = minute % 60;
        if(min<10){
            sb.append("0");
        }
        sb.append(min);
        sb.append("分");
        long sec = second % 60;
        if(sec<10){
            sb.append("0");
        }
        sb.append(sec);
        sb.append("秒");
        return sb.toString();
    }

    /**
     * 将毫秒转成 天时分秒
     * for share
     */
    public static String timeFormatCnByMills(long time){
        StringBuffer sb = new StringBuffer();
        long second = time/SECOUND_TO_MILLS;
        long minute = second/60;
        long hour = minute/60;
        long day = hour/24;
        if(day > 0){
            sb.append(day);
            sb.append("天");
        }
        long hh = hour%24;
        if(hh>0){
            sb.append(hh);
            sb.append("时");
        }

        long min = minute % 60;
        if(min>0){
            sb.append(min);
            sb.append("分");
        }

        long sec = second % 60;
        sb.append(sec);
        sb.append("秒");
        return sb.toString();
    }



}
