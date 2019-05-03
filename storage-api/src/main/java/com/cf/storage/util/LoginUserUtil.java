package com.cf.storage.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cf.storage.core.user.po.UserView;


public class LoginUserUtil {
    
	// 用户信息
    public static String GET_LOGIN_USERINFO_KEY = "userq";

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
    
    public static String getBasePath() {
        HttpServletRequest request = LoginUserUtil.getRequest();
        String path = LoginUserUtil.getPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return basePath;
    }
    
    public static String getPath() {
        String path = LoginUserUtil.getRequest().getContextPath();
        return path;
    }

    public static boolean isLogin() {
        if(RequestContextHolder.getRequestAttributes()==null){
            return false; 
        }
        UserView u = getLoginUserInfo();
        if (u == null) {
            return false;
        }
        return true;
    }
    
    // 获取session
    public static HttpSession getSession() {
        HttpServletRequest req = getRequest();
        return req.getSession();
    }
    
    // 从session获取用户信息
    public static UserView getLoginUserInfo() {
        HttpServletRequest req = getRequest();
        return (UserView) req.getSession().getAttribute(LoginUserUtil.GET_LOGIN_USERINFO_KEY);
    }
    
    /**
     * 内部类------------------------------------------------------------------------------------
     *
     */
    public static class LoginUserInfo extends UserView{
       
    }
	
}
