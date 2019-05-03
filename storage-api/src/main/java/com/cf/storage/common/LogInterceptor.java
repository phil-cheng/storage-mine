package com.cf.storage.common;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.cf.storage.common.annotation.LogAnnotation;
import com.cf.storage.common.pool.thread.ThreadPoolMgt;
import com.cf.storage.core.log.po.SysLog;
import com.cf.storage.core.log.service.LogService;
import com.cf.storage.core.log.task.LogTask;
import com.cf.storage.util.HumpStringUtil;
import com.cf.storage.util.LoginUserUtil;
import com.cf.storage.util.LoginUserUtil.LoginUserInfo;
import com.cf.storage.util.NetworkUtil;
import com.cf.storage.util.RandomUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


/** 
 * <p>类名称     ：com.fst.common.LogInterceptor</p>
 * <p>描述          ：controller全局日志记录</p>
 * <p>创建人     ：JetGuo</p>
 * <p>创建日期：2017年10月11日</p>
 * <p>修改人     ：</p>
 * <p>修改描述：</p>
 */
public class LogInterceptor implements HandlerInterceptor{
    
    private static FstLogger logger = FstLogger.getLogger(LogInterceptor.class);
    
    @Resource
    private LogService logService;

    private Date beginTime = null;
    private ModelAndView mav = null;
    private Object returnValue = null; 

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        this.beginTime = new Date();
        //logger.info("Method begin time :"+beginTime.getTime());
        return true;
    }

  
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        this.mav = modelAndView;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
            if (handler instanceof HandlerMethod) {
                HandlerMethod methodHandler = (HandlerMethod) handler;
                Method m = methodHandler.getMethod();
                LogAnnotation log = m.getAnnotation(LogAnnotation.class);
                
                SysLog sysLog = null;
				try {
					sysLog = assembleLog(m, request, returnValue, this.mav, beginTime, log);
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
				
				// 日志信息持久化到数据库或日志文件
				if(sysLog != null){
	                if (log != null) { // 拦截到的方法,如果使用了LogAnnotation注解，则存储日志到数据库
		               ThreadPoolMgt.addTask(new LogTask(sysLog, logService));
	                }
	                // 写入日志文件
	    		    logger.info("操作日志："+JSONObject.toJSONString(sysLog));
				}
            }
    }
    
    
    @SuppressWarnings("rawtypes")
    public static SysLog assembleLog(Method m,HttpServletRequest request,Object returnValue,ModelAndView mav,Date beginTime,LogAnnotation log) throws IOException{
        // 方法描述
    	String description = "";
    	if(log != null){
    		description = log.description();
    	}
        // 方法名称
        String methodName = m.getName();
        // 驼峰命名法的首单词
        String opt = HumpStringUtil.getFirstWord4hump(methodName);
        // 方法全路径
        String fullName = m.toGenericString();
        // 方法模块名称
        String moduleName = "";
        if(log != null){
        	moduleName = StringUtils.isEmpty(log.moduleName()) ? getModuleName4parseFullName(fullName) : log.moduleName();
        }else{
        	moduleName = getModuleName4parseFullName(fullName);
        }
        
        // 请求地址
        String reqUri = request.getRequestURI();
        // ip地址
        String ipAddress = NetworkUtil.getIpAddress(request);
        // 请求参数
        Map parameters = request.getParameterMap();
        String reqParams = "";
        if (parameters != null) {
        	ObjectMapper mapper = new ObjectMapper();
            reqParams = mapper.writeValueAsString(parameters);
        }
        // 执行返回结果(只支持返回了ModeAndView的方法)
        String retValues = "";
//    	if(log != null && mav != null){// 只有数据存到数据库时，才存入返回结果
//    		retValues = JSONObject.toJSONString(mav.getModel());
//    	}
        // 结束时间
        Date endTime =  new Date();
        
        // user信息
        String optUserId = "";
        String optUserName = "";
        try{
        	LoginUserInfo user = (LoginUserInfo)request.getSession().getAttribute(LoginUserUtil.GET_LOGIN_USERINFO_KEY);
            if(user != null){
            	optUserId = user.getId();
            	optUserName = user.getName();
            }
        }catch(Exception e){
        	// 退出时会先销毁session，此处再获取session会报异常
        	logger.info(e.getMessage());
        }
        
        SysLog sysLog = new SysLog(
    		reqParams, ipAddress, beginTime,endTime, opt, methodName, reqUri,
            fullName, description, RandomUtil.get32UUID(), moduleName,
            retValues,endTime.getTime()-beginTime.getTime(),optUserId,optUserName
        );
        
        return sysLog;
    }
    
    
    
    /** 
    * <p>方法名     :getModuleName4parseFullName </p> 
    * <p>方法描述: 通过解析全名来得到模块名称，模块名称为包名的第二个包名</p> 
    * <p>逻辑描述: </p> 
    * @param words
    * @return 
    */ 
    public static String getModuleName4parseFullName(String fullName) {
        String[] all = fullName.split(" ");
        String[] packageArr = all[2].split("\\.");
        return packageArr[2];
    }
    
  
    public void afterReturning(Object returnValue) throws Throwable {
        this.returnValue = returnValue;
    }
}
