package com.cf.storage.common;

import java.text.MessageFormat;

/** 
 * <p>类名称     ：com.fst.common.FstException</p>
 * <p>描述          ：自定义异常</p>
 * <p>创建人     ：JetGuo</p>
 * <p>创建日期：2017年10月6日</p>
 * <p>修改人     ：</p>
 * <p>修改描述：</p>
 */
public class FstException extends RuntimeException {

    private String errorCode;
    private String errorMsg;
    private String msgDetail;

    public String getMsgDetail() {
        return msgDetail;
    }

    public void setMsgDetail(String msgDetail) {
        this.msgDetail = msgDetail;
    }

    public FstException(ErrorCode code, Object... fillStr) {
        this.errorCode = code.getErrorCode();
        this.errorMsg = MessageFormat.format(code.getErrorMsg(), fillStr);
        this.msgDetail = MessageFormat.format(code.getMsgDetail(), fillStr);
    }

    public FstException(ErrorCode code) {
        this.errorCode = code.getErrorCode();
        this.errorMsg = code.getErrorMsg();
        this.msgDetail = code.getMsgDetail();
    }

    @Override
    public String toString() {
        return "错误码:[ " + this.getErrorCode() + " ] ,错误信息:[ " + this.getErrorMsg() + " ] ," + "解决办法:[ "
                + this.getMsgDetail() + " ]";
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}