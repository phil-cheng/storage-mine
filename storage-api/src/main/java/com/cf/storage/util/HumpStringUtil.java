package com.cf.storage.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/** 
 * <p>类名称     ：com.fst.util.HumpStringUtil</p>
 * <p>描述          ：驼峰命名处理类</p>
 * <p>创建人     ：JetGuo</p>
 * <p>创建日期：2017年10月26日</p>
 * <p>修改人     ：</p>
 * <p>修改描述：</p>
 */
public class HumpStringUtil {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /** 
    * <p>方法名     :lineToHump </p> 
    * <p>方法描述: 下划线转驼峰命名</p> 
    * <p>逻辑描述: </p> 
    * @param str
    * @return 
    */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /** 
    * <p>方法名     :humpToLine </p> 
    * <p>方法描述:驼峰转下划线(简单写法，效率低于{@link #humpToUnderLine(String)}) </p> 
    * <p>逻辑描述: </p> 
    * @param str
    * @return 
    */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /** 
    * <p>方法名     :humpToUnderLine </p> 
    * <p>方法描述:驼峰转下划线,效率比{@link #humpToLine(String)}高 </p> 
    * <p>逻辑描述: </p> 
    * @param str
    * @return 
    */
    public static String humpToUnderLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /** 
    * <p>方法名     :getFirstWord4hump </p> 
    * <p>方法描述: 获取驼峰命名法的首单词</p> 
    * <p>逻辑描述: </p> 
    * @param words
    * @return 
    */
    public static String getFirstWord4hump(String words) {
        if (words == null || "".equals(words.trim())) {
            return "";
        }
        int len = words.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = words.charAt(i);
            if (Character.isUpperCase(c)) {
                break;
            }
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static String listToString(List<String> list,String symbol){
        Collections.sort(list);
        if(StringUtils.isEmpty(symbol)){
            symbol = "";
        }
        StringBuilder sb = new StringBuilder("");
        for(String s : list){
            sb.append(s).append(symbol);
        }
        return sb.toString();
    }
    public static String arrayToString(String[] array,String symbol){
        Arrays.sort(array);
        if(StringUtils.isEmpty(symbol)){
            symbol = "";
        }
        StringBuilder sb = new StringBuilder("");
        for(String s : array){
            sb.append(s).append(symbol);
        }
        return sb.toString();
    }
   
    public static void main(String[] args) {
        String lineToHump = lineToHump("desc");
        System.out.println(lineToHump);// fParentNoLeader
        System.out.println(humpToLine(lineToHump));// f_parent_no_leader
        System.out.println(humpToUnderLine(lineToHump));// f_parent_no_leader
    }
}
