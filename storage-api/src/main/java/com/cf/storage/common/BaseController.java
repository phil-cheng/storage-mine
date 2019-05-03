package com.cf.storage.common;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

public class BaseController {

	protected Logger logger = LogManager.getLogger();

	 @SuppressWarnings({ "rawtypes", "static-access" })
	 public Map<String, Object> getParam() {
	        HttpServletRequest request = this.getRequest();
	        Map<String, Object> param = new HashMap<String, Object>();
	        Enumeration enu=request.getParameterNames();  
	        while(enu.hasMoreElements()){  
	        String paraName=(String)enu.nextElement();  
	        param.put(paraName, request.getParameter(paraName));
	        } 
	        return param;
	    }
	 
	 @SuppressWarnings({ "rawtypes", "static-access" })
	 public Map<String, String> getParamString() {
	        HttpServletRequest request = this.getRequest();
	        Map<String, String> param = new HashMap<String, String>();
	        Enumeration enu=request.getParameterNames();  
	        while(enu.hasMoreElements()){  
		        String paraName=(String)enu.nextElement();  
		        param.put(paraName, ObjectUtils.toString(request.getParameter(paraName)));
	        } 
	        return param;
	    }
	 
	 
	/** 基于@ExceptionHandler异常处理 */
	@ExceptionHandler
	public String exp(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		request.setAttribute("ex", ex);
//		boolean isAjax = isAjax(request);
		if (ex instanceof FstException) {// 自定义异常
			FstException fstEx = (FstException) ex;
			logger.debug(ex, ex);
//			if (isAjax) {
				AjaxResponse ajax = new AjaxResponse();
				ajax.setCode(fstEx.getErrorCode());
				ajax.setMsg(fstEx.getErrorMsg());
				ajax.setMsgExt(fstEx.getMsgDetail());
				String json = JSONObject.toJSONString(ajax);
				response.setContentType("application/json;charset=utf-8");
				try {
					response.getWriter().print(json);
				} catch (IOException e) {
					logger.error(e, e);
				}
//			} else {
//				return "error/error-business";
//			}
		} else { // 系统异常
			logger.error(ex, ex);
//			if (isAjax) {
				AjaxResponse ajax = new AjaxResponse();
				ajax.setCode(ErrorCode.SYS_UNKNOWN_EXCEPTION.getErrorCode());
				ajax.setMsg(ErrorCode.SYS_UNKNOWN_EXCEPTION.getErrorMsg());
				ajax.setMsgExt(ErrorCode.SYS_UNKNOWN_EXCEPTION.getMsgDetail());
				String json = JSONObject.toJSONString(ajax);
				response.setContentType("text/html;charset=utf-8");
				try {
					response.getWriter().print(json);
				} catch (IOException e) {
					logger.error(e, e);
				}
//			} else {
//				return "error/error";
//			}
		}
		return null;
	}

	public static boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static HttpSession getSession() {
		HttpServletRequest req = getRequest();
		return req.getSession();
	}
}
