package com.cf.storage.util;

import java.util.UUID;

public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static String getRadom(int num){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<num;i++){
			int rand =(int)(Math.random()*10);
			sb.append(rand+"");
		}
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		System.out.println(get32UUID());
		System.out.println(getRadom(8));
	}
	
}
