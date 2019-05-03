package com.cf.storage.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/** 
 * <p>类名称     ：com.fst.util.MD5</p>
 * <p>描述          ：MD5处理</p>
 * <p>创建人     ：Jet</p>
 * <p>创建日期：2016年8月9日</p>
 * <p>修改人     ：</p>
 * <p>修改描述：</p>
 */
public class MD5 {
	
	private static Logger logger = Logger.getLogger(MD5.class);
	
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}

    public static String md5(String str,String charset) {
    	try {
            byte[] btInput = str.getBytes(charset);
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16){
                	sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(md5("31119@qq.com" + "123456"));
//        System.out.println(md5("mj1"));
//    }
    
    /** 
	    * md5加密(ITS) 
	    * @param str 
	    * @param charSet 
	    * @return 
	    */  
	   public synchronized static final String getMD5Str(String str,String charSet) { //md5加密  
	    MessageDigest messageDigest = null;    
	    try {    
	        messageDigest = MessageDigest.getInstance("MD5");    
	        messageDigest.reset();   
	        if(charSet==null){  
	            messageDigest.update(str.getBytes());  
	        }else{  
	            messageDigest.update(str.getBytes(charSet));    
	        }             
	    } catch (NoSuchAlgorithmException e) {    
	    	logger.error("md5 error:"+e.getMessage(),e);  
	    } catch (UnsupportedEncodingException e) {    
	    	logger.error("md5 error:"+e.getMessage(),e);  
	    }    
	      
	    byte[] byteArray = messageDigest.digest();    
	    StringBuffer md5StrBuff = new StringBuffer();    
	    for (int i = 0; i < byteArray.length; i++) {                
	        if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)    
	            md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));    
	        else    
	            md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));    
	    }
	    return md5StrBuff.toString();    
	}  
}
