package com.cf.storage.core.user.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`user`")
public class UserView {
    
	@Id
    @Column(name = "`id`")
    private String id;

    /**
     * 用户名
     */
    @Column(name = "`name`")
    private String name;
    
    /**
     * 用户邮箱
     */
    @Column(name = "`email`")
    private String email;
    
    
    /**
     * 0：男；1：女
     */
    @Column(name = "`sex`")
    private String sex;
    

    /**
     * 身份证
     */
    @Column(name = "`id_card`")
    private String idCard;

    
    /**
     * 密码
     */
    @Column(name = "`pwd`")
    private String pwd;
    
    /**
     * 创建日期
     */
    @Column(name = "`create_time`")
    private Date createTime;
    
    
    /**
     * 修改日期
     */
    @Column(name = "`update_time`")
    private Date upateTime;
    


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getIdCard() {
		return idCard;
	}


	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getUpateTime() {
		return upateTime;
	}


	public void setUpateTime(Date upateTime) {
		this.upateTime = upateTime;
	}
	


}