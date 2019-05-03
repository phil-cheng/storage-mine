package com.cf.storage.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;  
public class HashUtil {  

    /** 
    * <p>方法名     :encryptMD5 </p> 
    * <p>方法描述: MD5加密</p> 
    * <p>逻辑描述: </p> 
    * @param source
    * @return 
    */ 
    public final static String encryptMD5(String source) {  
        if (source == null) {  
            source = "";  
        }  
        String result = "";  
        try {  
            result = encrypt(source, "MD5");  
        } catch (NoSuchAlgorithmException ex) {  
            // this should never happen  
            throw new RuntimeException(ex);  
        }  
        return result;  
    }  

    /** 
    * <p>方法名     :encryptSHA1 </p> 
    * <p>方法描述:Sha1加密 </p> 
    * <p>逻辑描述: </p> 
    * @param source
    * @return 
    */ 
    public final static String encryptSHA1(String source) {  
        if (source == null) {  
            source = "";  
        }  
        String result = "";  
        try {  
            result = encrypt(source, "SHA-1");  
        } catch (NoSuchAlgorithmException ex) {  
            // this should never happen  
            throw new RuntimeException(ex);  
        }  
        return result;  
    }  
    
    /** 
    * <p>方法名     :encryptSHA256 </p> 
    * <p>方法描述: Sha256加密</p> 
    * <p>逻辑描述: </p> 
    * @param source
    * @return 
    */ 
    public final static String encryptSHA256(String source) {  
        if (source == null) {  
            source = "";  
        }  
        String result = "";  
        try {  
            result = encrypt(source, "SHA-256");  
        } catch (NoSuchAlgorithmException ex) {  
            // this should never happen  
            throw new RuntimeException(ex);  
        }  
        return result;  
    }
 
    private final static String encrypt(String source, String algorithm)  
            throws NoSuchAlgorithmException {  
        byte[] resByteArray = encrypt(source.getBytes(), algorithm);  
        return Hex.encodeHexString(resByteArray);  
    }  

    private final static byte[] encrypt(byte[] source, String algorithm)  
            throws NoSuchAlgorithmException {  
        MessageDigest md = MessageDigest.getInstance(algorithm);  
        md.reset();  
        md.update(source);  
        return md.digest();  
    }  

    public static void main(String[] args) {
        String source = "wlh@123dw";
        System.out.println("原文字：\r\n" + source);
        String encryptedData = HashUtil.encryptSHA256(source);
        System.out.println("加密后：\r\n" + new String(encryptedData));
    }
}  