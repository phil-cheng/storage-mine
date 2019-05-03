package com.cf.storage.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 
 *<p>类名称     ：com.fst.util.SpringTool<p>
 *<p> 描述          ：通过该类即可在普通工具类里获取spring管理的bean <p>
 *<p> 创建人     ：Janeway<p>
 *<p> 创建日期：2018年1月7日<p>
 *<p> 修改人     ：<p>
 *<p> 修改描述：<p>
 */
public final class SpringTool implements ApplicationContextAware {  
    private static ApplicationContext applicationContext = null;  
  
    @Override  
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {  
        if (SpringTool.applicationContext == null) {  
            SpringTool.applicationContext = applicationContext;  
//            System.out.println("========ApplicationContext配置成功,在普通类可以通过调用ToolSpring.getAppContext()获取applicationContext对象,applicationContext=" + applicationContext + "========");  
        }  
    }  
  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    public static Object getBean(String name) {  
        return getApplicationContext().getBean(name);  
    }

}  