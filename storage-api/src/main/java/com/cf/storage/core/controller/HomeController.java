package com.cf.storage.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.cf.storage.common.AjaxResponse;
import com.cf.storage.common.BaseController;
import com.cf.storage.common.annotation.LogAnnotation;
import com.cf.storage.util.LoginUserUtil;


@Controller
@RequestMapping("/home")
public class HomeController extends BaseController{
    
    /**
     * 退出方法，要先做session过期校验
     * @param session
     * @param sessionStatus
     * @return
     */
    @RequestMapping("logout")
    @ResponseBody
	@LogAnnotation(description = "退出登陆")
    public AjaxResponse logout(HttpSession session,SessionStatus sessionStatus) {
        session.removeAttribute(LoginUserUtil.GET_LOGIN_USERINFO_KEY);
        sessionStatus.setComplete();
        session.invalidate();
        AjaxResponse ret = new AjaxResponse();
	    Map<String,Object> retMap = new HashMap<String,Object>();
	    retMap.put("result", "1");
	    ret.setData(retMap);
	    return ret;
    }
    
}
