package com.cf.storage.common;

/** 
 * <p>类名称     ：com.fst.common.AjaxResponse</p>
 * <p>描述          ：ajax统一返回值</p>
 * <p>创建人     ：JetGuo</p>
 * <p>创建日期：2017年10月16日</p>
 * <p>修改人     ：</p>
 * <p>修改描述：</p>
 */
public class AjaxResponse {

    private String code = "000000";
    private String msg = "OK";
    private String msgExt;
    private Object data ;
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String getMsgExt() {
        return msgExt;
    }
    
    public void setMsgExt(String msgExt) {
        this.msgExt = msgExt;
    }

    
    public Object getData() {
        return data;
    }

    
    public void setData(Object data) {
        this.data = data;
    }
    

}
