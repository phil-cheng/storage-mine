package com.cf.storage.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RandomUtil {
    public static String get32UUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
    //生成与当前时间有关系的ID号（用于订单号的生成），20150302+毫秒值
    //System.nanoTime()：得到当前时间的纳秒值[JDK5.0以后]
    public static String genOrdersNum(){
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String str = df.format(now)+System.currentTimeMillis()+getCode();//得到当前时间的毫秒值
        return str;
    }
    
    /** 
    *<p> 方法名     :getCode<p>
    *<p> 方法描述: 6位数字验证码<p>
    *<p> 逻辑描述: <p>
    * @return 
    */ 
    public static int getCode() {  
        return (int)((Math.random()*9+1)*100000);  
    } 
}
