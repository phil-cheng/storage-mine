package com.cf.storage.core.out.contorller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.storage.common.AjaxResponse;
import com.cf.storage.common.BaseController;
import com.cf.storage.common.ErrorCode;
import com.cf.storage.common.FstException;
import com.cf.storage.common.annotation.LogAnnotation;
import com.cf.storage.core.user.po.UserView;
import com.cf.storage.core.user.service.UserService;
import com.cf.storage.util.LoginUserUtil;
import com.cf.storage.util.RandomUtil;


@Controller
@RequestMapping("/core/out")
public class LoginController extends BaseController {

	  @Resource
	  private UserService userService;
	  
	  // 注册
	  @RequestMapping(value = "register", method = RequestMethod.POST)
	  @ResponseBody
	  @LogAnnotation(description = "用户注册")
	  public AjaxResponse register(@RequestBody Map<String,Object> parmMap) throws Exception {
		 String name = ObjectUtils.toString(parmMap.get("name"));
		 String email = ObjectUtils.toString(parmMap.get("email"));
		 String pwd = ObjectUtils.toString(parmMap.get("password"));
		 // 非空验证
	  	 if("".equals(name)){
	  		 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
	  	 }
	  	 if("".equals(email)){
	  		throw new FstException(ErrorCode.PARAMS_ERROE_200000);
	  	 }
	  	 if("".equals(pwd)){
	  		throw new FstException(ErrorCode.PARAMS_ERROE_200000);
	  	 }
	  	 // 验证用户是否存在
	  	 boolean existUser = userService.existUser(email);
	  	 if(existUser){
	  		throw new FstException(ErrorCode.REG_EXIST_EXP,email);
	  	 }
	  	 // 插入数据库
	  	 UserView uv = new UserView();
	  	 String uuid = RandomUtil.get32UUID();
	  	 uv.setId(uuid);
	  	 uv.setEmail(email);
	  	 uv.setName(name);
	  	 uv.setPwd(pwd);
	  	 uv.setCreateTime(new Date());
	  	 Integer a = userService.saveUser(uv);
	  	 if(a == 0){
	  		throw new FstException(ErrorCode.REG_USER_SAVE_FAILED);
	  	 }
	     // 返回成功
	     AjaxResponse ret = new AjaxResponse();
	     Map<String,Object> retMap = new HashMap<String,Object>();
	     retMap.put("tokenId", uuid);
	     ret.setData(retMap);
	     return ret;
	  }
	  
	  
	  @RequestMapping("login")
	  @ResponseBody
	  @LogAnnotation(description = "用户登陆")
	  public AjaxResponse login(@RequestBody Map<String,Object> parmMap, HttpServletRequest request) throws Exception {
		 String email = ObjectUtils.toString(parmMap.get("email"));
		 String pwd = ObjectUtils.toString(parmMap.get("password"));
		  // 非空验证
		 if(email == null || "".equals(email)){
	  		throw new FstException(ErrorCode.PARAMS_ERROE_200000);
	  	 }
	  	 if(pwd == null || "".equals(pwd)){
	  		throw new FstException(ErrorCode.PARAMS_ERROE_200000);
	  	 }
	  	 // 验证用户登陆
	  	 UserView uv = new UserView();
	  	 uv.setEmail(email);
	  	 uv.setPwd(pwd);
	  	 uv = userService.selectUser(uv);
	  	 if(uv == null){
	  		throw new FstException(ErrorCode.LOGIN_USER_LOGIN_ERROR);
	  	 }
	  	 // 初始化session
	  	 HttpSession session = request.getSession();
	  	 session.setAttribute(LoginUserUtil.GET_LOGIN_USERINFO_KEY, uv);
	     // 返回成功
	     AjaxResponse ret = new AjaxResponse();
	     Map<String,Object> retMap = new HashMap<String,Object>();
	     retMap.put("tokenId", uv.getId());
	     ret.setData(retMap);
	     return ret;
	  }
   
}
