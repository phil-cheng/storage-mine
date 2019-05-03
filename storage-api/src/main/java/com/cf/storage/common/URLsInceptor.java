package com.cf.storage.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.cf.storage.util.LoginUserUtil;


@Component
public class URLsInceptor implements HandlerInterceptor{
	private static Logger logger = LogManager.getLogger(URLsInceptor.class);
	private PathMatcher pathMatcher = new AntPathMatcher();
	private UrlPathHelper helper = new UrlPathHelper();
	private List<String> excludePattern;
	private List<String> includePattern;
	private String loginUrl;
	private boolean checkAuthFlag = true;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 获取请求路径
		String path = helper.getLookupPathForRequest(request);
		if(path == null || "".equals(path)){
			throw new FstException(ErrorCode.AUTH_URL_INVALID);
		}
		
		// 输出拦截日志
		if(logger.isInfoEnabled()){
			logger.info("拦截到请求:"+path);
		}
		
		// 白名单验证
		// 白名单-排除拦截(当请求在白名单内，直接放行，不进行登录和权限校验)
		if(this.excludePattern != null){
			for (String pattern : this.excludePattern) {
				if(this.pathMatcher.match(pattern, path)){
					return true;
				}
			}
		}
		
		// 登录校验
		// 判断用户是否有有效登录，无,则跳转到登录页
		if(!LoginUserUtil.isLogin()){
			throw new FstException(ErrorCode.AUTH_NOT_LOGIN);
        }
		
		return true;
	}

	public PathMatcher getPathMatcher() {
		return pathMatcher;
	}

	public void setPathMatcher(PathMatcher pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	public List<String> getExcludePattern() {
		return excludePattern;
	}

	public void setExcludePattern(List<String> excludePattern) {
		this.excludePattern = excludePattern;
	}

	public List<String> getIncludePattern() {
		return includePattern;
	}

	public void setIncludePattern(List<String> includePattern) {
		this.includePattern = includePattern;
	}
	
	public String getLoginUrl() {
	     return loginUrl;
	}
	
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

	public boolean isCheckAuthFlag() {
		return checkAuthFlag;
	}

	public void setCheckAuthFlag(boolean checkAuthFlag) {
		this.checkAuthFlag = checkAuthFlag;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
    

}
