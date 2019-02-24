/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.productinven.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 库存Entity
 * @author wn
 * @version 2017-12-28
 */
public class TalProduct extends DataEntity<TalProduct> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户id
	private String productname;		// 产品名称
	private String modelnum;		// 款号
	private String colorname;		// 颜色
	private String specification;		// 规格
	private String texture;		// 材料
	private String cost;		// 成本价
	private String tallinventory;		// 总库存
	private String nowinventory;		// 当前库存
	private String price;		// 销售价
	private String picture;		// 图片地址
	private Date beginUpdateDate;		// 开始 更新时间
	private Date endUpdateDate;		// 结束 更新时间
	
	public TalProduct() {
		super();
	}

	public TalProduct(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=100, message="产品名称长度必须介于 0 和 100 之间")
	@ExcelField(title="产品名称", align=2, sort=10)
	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	@Length(min=0, max=100, message="款号长度必须介于 0 和 100 之间")
	@ExcelField(title="款号", align=2, sort=20)
	public String getModelnum() {
		return modelnum;
	}

	public void setModelnum(String modelnum) {
		this.modelnum = modelnum;
	}
	
	@Length(min=0, max=100, message="颜色长度必须介于 0 和 100 之间")
	@ExcelField(title="颜色", align=2, sort=30)
	public String getColorname() {
		return colorname;
	}

	public void setColorname(String colorname) {
		this.colorname = colorname;
	}
	
	@Length(min=0, max=50, message="规格长度必须介于 0 和 50 之间")
	@ExcelField(title="规格", align=2, sort=40)
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	@Length(min=0, max=200, message="材料长度必须介于 0 和 200 之间")
	@ExcelField(title="材料", align=2, sort=50)
	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}
	
	@Length(min=0, max=30, message="成本价长度必须介于 0 和 30 之间")
	@ExcelField(title="成本价", align=2, sort=60)
	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
	@Length(min=0, max=30, message="总库存长度必须介于 0 和 30 之间")
	@ExcelField(title="总库存", align=2, sort=70)
	public String getTallinventory() {
		return tallinventory;
	}

	public void setTallinventory(String tallinventory) {
		this.tallinventory = tallinventory;
	}
	
	@Length(min=0, max=30, message="当前库存长度必须介于 0 和 30 之间")
	@ExcelField(title="当前库存", align=2, sort=70)
	public String getNowinventory() {
		return nowinventory;
	}

	public void setNowinventory(String nowinventory) {
		this.nowinventory = nowinventory;
	}
	
	@Length(min=0, max=30, message="销售价长度必须介于 0 和 30 之间")
	@ExcelField(title="销售价", align=2, sort=80)
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}
		
}