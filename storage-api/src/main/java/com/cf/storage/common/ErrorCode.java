package com.cf.storage.common;

public enum ErrorCode {
    //系统级异常
    SYS_UNKNOWN_EXCEPTION("000001","系统异常","未知错误请联系管理员"),
    FILE_INPUT_004001("004001","上传文件格式不正确","上传附件需要是jpg、png、gif、bmg、psd、jpeg格式的图片！"),
    FILE_INPUT_004002("004002","上传文件大小超过限制","上传附件不能大于200kB！"),
    FILE_INPUT_004003("004003","上传失败","请选择上传文件！"),
    FILE_INPUT_004004("004004","删除失败","简历头像不可删除！"),
    FILE_INPUT_004005("004005","导入失败","请重新选择正确格式的文件！"),
    FILE_INPUT_004006("004006","导入失败","上传数据重复！"),
    FILE_INPUT_004007("004003","上传失败","选择的上传文件为空,请重新选择"),
    FILE_INPUT_004008("004008","上传的文件格式有误","请重新选择"),
    FILE_INPUT_004009("004009","上传文件格式不正确","上传附件需要是pdf"),
    FILE_INPUT_004010("004009","上传文件名称重复","请重新选择"),
    

    //权限验证错误信息
    AUTH_NOT_LOGIN("000100","用户没有登录","用户没有登录，请先登录"),
    AUTH_PERMISSION_DENIED("000101","{0}:用户没有访问权限","{0}:用户没有访问权限，请联系管理申请高级权限！"),
    AUTH_URL_INVALID("000102","请求了非标准的url","当前请求URL：{0},<br>标准URL:模块(core)/子模块(menu)/操作(addMenuAjax)/..."),
    AUTH_OPERATION_DENIED("000103","{0}:用户没有操作权限","{0}:用户没有操作权限，请联系管理申请高级权限！<br>请求URL：{1}"),
    
    //登录异常
    LOGIN_INCORRECT_ANSWER("000200","验证码校验错误","验证码校验错误，请输入正确的验证码"),
    LOGIN_USER_NOT_EXIST("000201","用户不存在","当前登录用户不存在，请先关注微信公众号"),
    LOGIN_USER_INFO_INCOMPLETE("000202","用户信息不完整","当联系管理员"),
    LOGIN_USER_LOGIN_ERROR("000203","密码错误或用户不存在",""),
    
    //用户注册
    REG_EXIST_EXP("000300","{0}:用户已经存在","{0}:用户已经存在，请从新输入"),
    REG_USER_SAVE_FAILED("000301","创建用户失败",""),
    
    //下载文件错误
    ATTA_FILE_IS_NOT_EXIST("000400","附件不存在","附件不存在，找不到对应的附件，请重新上传"),
    
    //密码验证错误信息
    PWD_INPUT_PDW_ISNULL("001000","输入密码为空","输入密码为空"),
    PWD_OBJ_IS_NULL("001001","找不到密码实体对象","找不到密码实体对象，请重新注册"),
    PWD_IS_CANCELED("001002","密码已注销","密码已被注销，请重新生成"),
    PWD_IS_LOCKED("001003","密码被锁定","密码已被锁定请联系管理员"),
    PWD_IS_FROZEN("001004","密码已被冻结","密码已被冻结请联系管理员"),
    PWD_IS_EXPIRED("001005","密码已过有效期","请重新生成密码，密码已过有效期"),
    PWD_AUTH_FAILED("001006","密码验证失败","用户名和密码不匹配"),
    OLDPWD_AUTH_FAILED("001007","原密码验证失败","用户名和密码不匹配"),
    
    //商品异常
    GOODS_NOT_EXIST("002000","商品不存在","商品基础信息不存在，请添加基础信息！"),
    ORDER_NOT_EXIST("002001","商品不存在","商品基础信息不存在，请添加基础信息！"),
    GOODS_USER_NOT_EXIST("002002","商品所对应的用户不存在","商品所对应的用户不存在，请联系管理员！"),
	
	//参数异常
    PARAMS_ERROE_200000("200000","参数异常","请检查"),
	
	// api异常控制
	API_TOKEN_ERROR_100001("100001","创建token异常",""),
	REQUEST_ERROR_110002("110002", "不合法的参数", ""),
	AUTH_ERROR_10004("10004", "您还未登陆过", ""),
	AUTH_ERROR_10003("10003", "登陆状态已过期,请重新登陆", ""), //refreshAccessToken 已过期
	AUTH_ERROR_10002("10002", "无效的token", ""), //无效的token
	TOKEN_ERROR_30001("30001", "创建token异常", ""),
	AUTH_ERROR_10001("10001", "无效的数字签名", ""), //sign不匹配
	AUTH_ERROR_10006("10006", "timestamp不能为空", ""),
	AUTH_ERROR_10005("10005", "请求发起时间超过服务器限制", ""),
	AUTH_ERROR_10007("10007", "nonce不能为空", ""), 
	AUTH_ERROR_10008("10008", "重复的请求", "");
	
	
    private String errorCode;
    private String errorMsg;
    private String msgDetail;

    public String getMsgDetail() {
        return msgDetail;
    }

    public void setMsgDetail(String msgDetail) {
        this.msgDetail = msgDetail;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    private ErrorCode(String errorCode, String errorMsg, String msgDetail) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.msgDetail = msgDetail;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
