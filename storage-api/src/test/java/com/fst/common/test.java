package com.fst.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Pattern;

public class test {
	
	public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
    
	
	public static void main(String[] args) {
	    File file = new File("D:/test_replace.txt");
	    String name = txt2String(file);
//	    System.out.println(name);
		String pat = "<!--二维码生成专用 -->[\\d\\D]*?<!--二维码生成专用 -->";
		Pattern scriptPattern = Pattern.compile(pat);  
        String value = scriptPattern.matcher(name).replaceAll("");  
        System.out.println(value);
	}

}
