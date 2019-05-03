package com.cf.storage.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 
 * <p>类名称     ：com.fst.util.DateUtil</p>
 * <p>描述          ：时间日期处理工具
 * 时间日期处理工具
 * String -> Date
 * Date -> String
 * 以及生成含有日期的字符串 可以当作保存id用等等。
 * 等格式化处理 
 * </p>
 * <p>创建人     ：JetGuo</p>
 * <p>创建日期：2017年10月30日</p>
 * <p>修改人     ：</p>
 * <p>修改描述：</p>
 */
public class DateUtil {
    private static final String FORMAT_0 = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_1 = "yyyy-MM-dd";
    private static final String FORMAT_2 = "HH:mm:ss";
    private static final String FORMAT_3 = "yyyyMMddHHmmss";

    /** 
    *<p> 方法名     :getOneToLastDay<p>
    *<p> 方法描述: 获取本月第一天到最后一天<p>
    *<p> 逻辑描述: <p>
    * @return 
    */ 
    public static Map<String,Date> getOneToLastDay() {
        Map<String,Date> ret = new HashMap<>();
        Calendar cale = Calendar.getInstance();  
        Date firstDay, lastDay;  
        // 获取前月的第一天  
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 0);  
        cale.set(Calendar.DAY_OF_MONTH, 1);  
        firstDay = cale.getTime();  
        // 获取前月的最后一天  
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 1);  
        cale.set(Calendar.DAY_OF_MONTH, 0);  
        lastDay = cale.getTime();  
        ret.put("firstDay", firstDay);
        ret.put("lastDay", lastDay);
        return ret;
    }
    
    //获得含有时间的id 字符串
    public static String getIdByTime(){
        Date now = new Date();
        SimpleDateFormat simple = new SimpleDateFormat(FORMAT_3);
        return simple.format(now);
    }
    //如果参数长度不为为0,则取第一个参数进行格式化，否则取当前日期时间，精确到秒 如:2010-4-15 9:36:38
    public static String toFull(Date... date) {
        SimpleDateFormat simple = new SimpleDateFormat(FORMAT_0);
        if (date!=null && date.length > 0) {
            return simple.format(date[0]);
        }
        return simple.format(new Date());
    }
    /**
     * 如果参数长度不为为0,则取第一个参数进行格式化，<br>
     * 否则取当前日期 如:2010-4-15
     * 
     * @param Date
     *            ... 可变参数
     * @return java.lang.String
     * **/
    public static String toDate(Date... date) {
        SimpleDateFormat simple = new SimpleDateFormat(FORMAT_1);
        if (date.length > 0) {
            return simple.format(date[0]);
        }
        return simple.format(new Date());
    }

    /**
     * 如果参数长度不为为0,则取第一个参数进行格式化，<br>
     * 否则取当前日期时间，精确到秒<br>
     * 如:9:36:38
     * 
     * @param Date
     *            ... 可变参数
     * @return java.lang.String
     * **/
    public static String toTime(Date... date) {
        SimpleDateFormat simple = new SimpleDateFormat(FORMAT_2);
        if (date.length > 0) {
            return simple.format(date[0]);
        }
        return simple.format(new Date());
    }

    /**
     * 根据字符串格式去转换相应格式的日期和时间
     * 
     * @param java
     *            .lang.String 必要参数
     * @return java.util.Date
     * @exception java.text.ParseException
     *                如果参数格式不正确会抛出此异常
     * **/
    public static Date reverse2Date(String date) {
        SimpleDateFormat simple = null;
        switch (date.trim().length()) {
        case 19:// 日期+时间
            simple = new SimpleDateFormat(FORMAT_0);
            break;
        case 10:// 仅日期
            simple = new SimpleDateFormat(FORMAT_1);
            break;
        case 8:// 仅时间
            simple = new SimpleDateFormat(FORMAT_2);
            break;
        default:
            break;
        }
        try {
            return simple.parse(date.trim());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将带有时、分、秒格式的日期转化为00:00:00<br>
     * 方便日期推算,格式化后的是yyyy-MM-dd 00:00:00
     * 
     * @param java
     *            .util.Date... date的长度可以为0,即不用给参数
     * @return java.util.Date
     * **/
    public static Date startOfADay(Date... date) {
        SimpleDateFormat simple = new SimpleDateFormat(FORMAT_1);
        Date date_ = date.length == 0 ? new Date() : date[0];// 如果date为null则取当前时间
        String d = simple.format(date_);
        try {
            return simple.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 推算一个月内向前或向后偏移多少天,当然推算年也可以使用
     * 
     * @param date
     *            要被偏移的日期,<br>
     *            amout 偏移量<br>
     *            b 是否先将date格式化为yyyy-MM-dd 00:00:00 即: 是否严格按整天计算
     * @return java.util.Date
     * **/
    public static Date addDayOfMonth(Date date, Integer amount, Boolean b) {
        date = date == null ? new Date() : date;// 如果date为null则取当前日期
        if (b) {
            date = startOfADay(date);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, amount);
        return cal.getTime();
    }

    /**
     * 推算一个小时内向前或向后偏移多少分钟,除了秒、毫秒不可以以外,其他都可以
     * 
     * @param date
     *            要被偏移的日期,<br>
     *            amout 偏移量<br>
     *            b 是否先将date格式化为yyyy-MM-dd HH:mm:00 即: 是否严格按整分钟计算
     * @return java.util.Date
     * **/
    public static Date addMinuteOfHour(Date date, Integer amount, Boolean b) {
        date = date == null ? new Date() : date;// 如果date为null则取当前日期
        if (b) {
            SimpleDateFormat simple = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:00");
            try {
                date = simple.parse(simple.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, amount);
        return cal.getTime();
    }
    /**
     * 在当前时间的基础上加或减去几月
     *
     * @param month
     * @return
     */
    public  static Date month(int month) {
        java.util.Calendar Cal = java.util.Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(Calendar.MONTH, month);
        return Cal.getTime();
    }

    /**
     * 在指定的时间上加或减去几月
     *
     * @param date
     * @param month
     * @return
     */
    public  static Date month(Date date, int month) {
        java.util.Calendar Cal = java.util.Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(java.util.Calendar.MONTH, month);
        return Cal.getTime();
    }

    /** 
    *<p> 方法名     :getDay<p>
    *<p> 方法描述: 在当前时间上加上或减去几天<p>
    *<p> 逻辑描述: <p>
    * @param day
    * @return 
    */ 
    public  static Date getDay(int day) {
        java.util.Calendar Cal = java.util.Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(Calendar.DAY_OF_MONTH, day);
        return Cal.getTime();
    }

    /** 
    *<p> 方法名     :getDay<p>
    *<p> 方法描述: 在某一时间加上几天<p>
    *<p> 逻辑描述: <p>
    * @param date
    * @param day
    * @return 
    */ 
    public  static Date getDay(Date date, int day) {
        java.util.Calendar Cal = java.util.Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(Calendar.DAY_OF_MONTH, day);
        return Cal.getTime();
    }
    /** 
    *<p> 方法名     :getDelay<p>
    *<p> 方法描述: 获取当前时间到某一时刻的时间差<p>
    *<p> 逻辑描述: <p>
    * @param targetTime
    * @return 
    */ 
    public static long getDelay(String targetTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date targetDate = null;
        try {
            targetDate = sdf.parse(targetTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetDate.getTime()-new Date().getTime();
    }

    /** 
    *<p> 方法名     :formatDuring<p>
    *<p> 方法描述:毫秒转换为时间 <p>
    *<p> 逻辑描述: <p>
    * @param mss
    * @return 
    */ 
    public static Map<String,Object> formatDuring(long mss) {
        Map<String,Object> ret = new HashMap<>();
        long days = mss / (1000 * 60 * 60 * 24);
        ret.put("days", days);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        ret.put("hours", hours);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        ret.put("minutes", minutes);
        long seconds = (mss % (1000 * 60)) / 1000;
        ret.put("seconds", seconds);
        return ret;
    }
    
    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return nowTimeStamp
     */
    public static String getNowTimeStamp() {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time / 1000);
        return nowTimeStamp;
    }
    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String DateToTimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    
    /**
     * 获取今天
     */
    public static String getToday(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(new Date());
    }
    
    
    /**
     * 获取现在的时间
     */
    public static String getNow(){
    	SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_3);
        return sdf.format(new Date());
    }
    
    
    
    public static void main(String[] args) {
        String toFull = toFull(getDay(-7));
        String time = DateToTimeStamp(toFull,FORMAT_0);
        System.out.println(time);
    }
}
