package com.cf.storage.core.items.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`items`")
public class Items {
    
	@Id
    @Column(name = "`id`")
    private String id;

    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;
    
    /**
     * path结构树路径
     */
    @Column(name = "`path`")
    private String path;
    
    
    /**
     * 父id
     */
    @Column(name = "`pid`")
    private String pid;
    

    /**
     * 收藏夹还是物品
     */
    @Column(name = "`type`")
    private String type;

    
    /**
     * 标签
     */
    @Column(name = "`title`")
    private String title;
    
    
    /**
     * 说明
     */
    @Column(name = "`note`")
    private String note;
    
    
    /**
     * 数量
     */
    @Column(name = "`num`")
    private Integer num;
    
    
    /**
     * 单价
     */
    @Column(name = "`price`")
    private Double price;
    
    
    /**
     * 生产日期
     */
    @Column(name = "`produce_date`")
    private Date produceDate;
    
    
    /**
     * 过期日期
     */
    @Column(name = "`exprie_date`")
    private Date exprieDate;
    
    
    /**
     * 链接
     */
    @Column(name = "`link`")
    private String link;
    
    
    /**
     * 总价
     */
    @Column(name = "`total_price`")
    private String totalPrice;
    
    
    /**
     * 图片指纹
     */
    @Column(name = "`img_finger`")
    private String imgFinger;
    
    
    /**
     * 图片内容
     */
    @Column(name = "`profile_pic`")
    private String profilePic;
   
    
    /**
     * 用户信息
     */
    @Column(name = "`user_id`")
    private String userId;
    
    
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


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Date getProduceDate() {
		return produceDate;
	}


	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}


	public Date getExprieDate() {
		return exprieDate;
	}


	public void setExprieDate(Date exprieDate) {
		this.exprieDate = exprieDate;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public String getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getImgFinger() {
		return imgFinger;
	}


	public void setImgFinger(String imgFinger) {
		this.imgFinger = imgFinger;
	}


	public String getProfilePic() {
		return profilePic;
	}


	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	


}