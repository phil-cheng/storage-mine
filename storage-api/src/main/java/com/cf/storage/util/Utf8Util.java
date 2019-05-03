package com.cf.storage.util;

import org.apache.commons.lang3.StringUtils;

public class Utf8Util {

    /** 
    *<p> 方法名     :gbEncoding<p>
    *<p> 方法描述:转换成16进制整型字符串 <p>
    *<p> 逻辑描述: <p>
    * @param gbString
    * @return 
    */ 
    public static String gbEncoding(final String gbString) {   //gbString = "测试"  
        if(StringUtils.isEmpty(gbString)||gbString.indexOf("\\u")==0){
            return gbString;
        }
        char[] utfBytes = gbString.toCharArray();   //utfBytes = [测, 试]  
        String unicodeBytes = "";     
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {     
            String hexB = Integer.toHexString(utfBytes[byteIndex]);   //转换为16进制整型字符串  
              if (hexB.length() <= 2) {     
                  hexB = "00" + hexB;     
             }     
             unicodeBytes = unicodeBytes + "\\u" + hexB;    
        }     
        return unicodeBytes;     
    }  
    
    /** 
    *<p> 方法名     :decodeUnicode<p>
    *<p> 方法描述: 解码<p>
    *<p> 逻辑描述: <p>
    * @param dataStr
    * @return 
    */ 
    public static String decodeUnicode(final String dataStr) {
        if(dataStr.indexOf("\\u")!=-1){
            int start = 0;     
            int end = 0;     
            final StringBuffer buffer = new StringBuffer();     
            while (start > -1) {     
                end = dataStr.indexOf("\\u", start + 2);     
                String charStr = "";     
                if (end == -1) {     
                    charStr = dataStr.substring(start + 2, dataStr.length());     
                } else {     
                    charStr = dataStr.substring(start + 2, end);     
                }     
                char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。     
                buffer.append(new Character(letter).toString());     
                start = end;     
            }     
                return buffer.toString();  
            }else{
                return dataStr;
            }
     }
    public static void main(String[] args) {
        String s = "15868581672";
        System.out.println(gbEncoding(s));
//        System.out.println(gbEncoding(gbEncoding(s)));
        //System.out.println(decodeUnicode(s));
    }
}
