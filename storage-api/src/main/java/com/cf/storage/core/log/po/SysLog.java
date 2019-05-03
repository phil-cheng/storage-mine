package com.cf.storage.core.log.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`sys_log`")
public class SysLog {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    /**
     * 请求参数
     */
    @Column(name = "`req_params`")
    private String reqParams;

    /**
     * 请求的IP地址
     */
    @Column(name = "`ip_address`")
    private String ipAddress;

    /**
     * 方法开始时间
     */
    @Column(name = "`begin_time`")
    private Date beginTime;

    /**
     * 方法结束时间
     */
    @Column(name = "`end_time`")
    private Date endTime;

    /**
     * 操作类型
     */
    @Column(name = "`opt`")
    private String opt;

    /**
     * 方法名称
     */
    @Column(name = "`method_name`")
    private String methodName;

    /**
     * 请求的URL
     */
    @Column(name = "`req_uri`")
    private String reqUri;

    /**
     * 方法所在的类及方法，全称
     */
    @Column(name = "`full_name`")
    private String fullName;

    /**
     * 方法描述
     */
    @Column(name = "`description`")
    private String description;

    /**
     * 序列号
     */
    @Column(name = "`sequence_no`")
    private String sequenceNo;

    /**
     * 所在模块
     */
    @Column(name = "`module_name`")
    private String moduleName;

    /**
     * 返回值
     */
    @Column(name = "`ret_values`")
    private String retValues;

    /**
     * 执行时间单位（毫秒）
     */
    @Column(name = "`exec_time`")
    private Long execTime;
    
    /**
     * 操作人id
     */
    @Column(name = "`opt_user_id`")
    private String optUserId;
    
    
    /**
     * 操作人姓名
     */
    @Column(name = "`opt_user_name`")
    private String optUserName;
    

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取请求参数
     *
     * @return req_params - 请求参数
     */
    public String getReqParams() {
        return reqParams;
    }

    /**
     * 设置请求参数
     *
     * @param reqParams 请求参数
     */
    public void setReqParams(String reqParams) {
        this.reqParams = reqParams;
    }

    /**
     * 获取请求的IP地址
     *
     * @return ip_address - 请求的IP地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置请求的IP地址
     *
     * @param ipAddress 请求的IP地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 获取方法开始时间
     *
     * @return begin_time - 方法开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置方法开始时间
     *
     * @param beginTime 方法开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取方法结束时间
     *
     * @return end_time - 方法结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置方法结束时间
     *
     * @param endTime 方法结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public SysLog() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SysLog(String reqParams, String ipAddress, Date beginTime, Date endTime, String opt, String methodName,
            String reqUri, String fullName, String description, String sequenceNo, String moduleName, String retValues,
            Long execTime, String optUserId, String  optUserName) {
        super();
        this.reqParams = reqParams;
        this.ipAddress = ipAddress;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.opt = opt;
        this.methodName = methodName;
        this.reqUri = reqUri;
        this.fullName = fullName;
        this.description = description;
        this.sequenceNo = sequenceNo;
        this.moduleName = moduleName;
        this.retValues = retValues;
        this.execTime = execTime;
        this.optUserId = optUserId;
        this.optUserName = optUserName;
    }

    /**
     * 获取操作类型
     *
     * @return opt - 操作类型
     */
    public String getOpt() {
        return opt;
    }

    /**
     * 设置操作类型
     *
     * @param opt 操作类型
     */
    public void setOpt(String opt) {
        this.opt = opt;
    }

    /**
     * 获取方法名称
     *
     * @return method_name - 方法名称
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 设置方法名称
     *
     * @param methodName 方法名称
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 获取请求的URL
     *
     * @return req_uri - 请求的URL
     */
    public String getReqUri() {
        return reqUri;
    }

    /**
     * 设置请求的URL
     *
     * @param reqUri 请求的URL
     */
    public void setReqUri(String reqUri) {
        this.reqUri = reqUri;
    }

    /**
     * 获取方法所在的类及方法，全称
     *
     * @return full_name - 方法所在的类及方法，全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置方法所在的类及方法，全称
     *
     * @param fullName 方法所在的类及方法，全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取方法描述
     *
     * @return description - 方法描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置方法描述
     *
     * @param description 方法描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取序列号
     *
     * @return sequence_no - 序列号
     */
    public String getSequenceNo() {
        return sequenceNo;
    }

    /**
     * 设置序列号
     *
     * @param sequenceNo 序列号
     */
    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    /**
     * 获取所在模块
     *
     * @return module_name - 所在模块
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * 设置所在模块
     *
     * @param moduleName 所在模块
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * 获取返回值
     *
     * @return ret_values - 返回值
     */
    public String getRetValues() {
        return retValues;
    }

    /**
     * 设置返回值
     *
     * @param retValues 返回值
     */
    public void setRetValues(String retValues) {
        this.retValues = retValues;
    }

    /**
     * 获取执行时间单位（毫秒）
     *
     * @return exec_time - 执行时间单位（毫秒）
     */
    public Long getExecTime() {
        return execTime;
    }

    /**
     * 设置执行时间单位（毫秒）
     *
     * @param execTime 执行时间单位（毫秒）
     */
    public void setExecTime(Long execTime) {
        this.execTime = execTime;
    }

	public String getOptUserId() {
		return optUserId;
	}

	public void setOptUserId(String optUserId) {
		this.optUserId = optUserId;
	}

	public String getOptUserName() {
		return optUserName;
	}

	public void setOptUserName(String optUserName) {
		this.optUserName = optUserName;
	}
    
}