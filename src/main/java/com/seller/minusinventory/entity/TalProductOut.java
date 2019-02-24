/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.minusinventory.entity;

import com.seller.productinven.entity.TalProduct;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 销售Entity
 * @author wn
 * @version 2017-12-30
 */
public class TalProductOut extends DataEntity<TalProductOut> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户
	private TalProduct product;		// 产品
	private String minusinventory;		// 库存减少
	private String unitprice;		// 单价
	private String talprice;		// 总价
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public TalProductOut() {
		super();
	}

	public TalProductOut(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TalProduct getProduct() {
		return product;
	}

	public void setProduct(TalProduct product) {
		this.product = product;
	}

	@Length(min=0, max=30, message="库存减少长度必须介于 0 和 30 之间")
	public String getMinusinventory() {
		return minusinventory;
	}

	public void setMinusinventory(String minusinventory) {
		this.minusinventory = minusinventory;
	}
	
	@Length(min=0, max=30, message="单价长度必须介于 0 和 30 之间")
	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	
	@Length(min=0, max=30, message="总价长度必须介于 0 和 30 之间")
	public String getTalprice() {
		return talprice;
	}

	public void setTalprice(String talprice) {
		this.talprice = talprice;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}