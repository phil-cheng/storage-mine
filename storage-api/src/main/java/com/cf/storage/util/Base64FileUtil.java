package com.cf.storage.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Base64FileUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
    *
    * @param path
    * @return String
    * @description 将文件转base64字符串
    * File转成编码成BASE64
    */
   public static  String fileToBase64(String path) {
       String base64 = null;
       InputStream in = null;
       try {
           File file = new File(path);
           in = new FileInputStream(file);
           byte[] bytes=new byte[(int)file.length()];
           in.read(bytes);
           base64 = Base64.getEncoder().encodeToString(bytes);
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           if (in != null) {
               try {
                   in.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
       return base64;
   }
   
   //BASE64解码成File文件
   public static void base64ToFile(String destPath,String base64, String fileName) {
       File file = null;
       //创建文件目录
       String filePath=destPath;
       File  dir=new File(filePath);
       if (!dir.exists() && !dir.isDirectory()) {
           dir.mkdirs();
       }
       BufferedOutputStream bos = null;
       java.io.FileOutputStream fos = null;
       try {
           byte[] bytes = Base64.getDecoder().decode(base64);
           file=new File(filePath+"/"+fileName);
           fos = new java.io.FileOutputStream(file);
           bos = new BufferedOutputStream(fos);
           bos.write(bytes);
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           if (bos != null) {
               try {
                   bos.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           if (fos != null) {
               try {
                   fos.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
   }

}
