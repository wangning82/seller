/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.addinventory.entity;

import com.seller.productinven.entity.TalProduct;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

/**
 * 入库Entity
 * @author wn
 * @version 2017-12-29
 */
public class TalProductIn extends DataEntity<TalProductIn> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户
	private TalProduct product;		// 产品
	private String addinventory;		// 库存增加
	
	public TalProductIn() {
		super();
	}

	public TalProductIn(String id){
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

	@Length(min=0, max=30, message="库存增加长度必须介于 0 和 30 之间")
	public String getAddinventory() {
		return addinventory;
	}

	public void setAddinventory(String addinventory) {
		this.addinventory = addinventory;
	}
	
}