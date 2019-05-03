package com.cf.storage.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	public static String requestPath = "";
	
	
	public static  String getRequestPath(HttpServletRequest request){
		if(requestPath == null || "".equals(requestPath)){
			requestPath = request.getScheme() + "://" + request.getServerName() + ":" 
			+ request.getServerPort() +  request.getContextPath() + "/";
		}
		return requestPath;
	}
}
