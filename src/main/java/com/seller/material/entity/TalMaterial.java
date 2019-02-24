/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.material.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料管理Entity
 * @author wn
 * @version 2017-12-28
 */
public class TalMaterial extends DataEntity<TalMaterial> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户id
	private String material;		// 材料
	
	public TalMaterial() {
		super();
	}

	public TalMaterial(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=100, message="材料长度必须介于 0 和 100 之间")
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
}