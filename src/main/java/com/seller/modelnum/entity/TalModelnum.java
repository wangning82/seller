/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.modelnum.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 款号Entity
 * @author wn
 * @version 2017-12-28
 */
public class TalModelnum extends DataEntity<TalModelnum> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户id
	private String modelnum;		// 款号
	
	public TalModelnum() {
		super();
	}

	public TalModelnum(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=100, message="款号长度必须介于 0 和 100 之间")
	public String getModelnum() {
		return modelnum;
	}

	public void setModelnum(String modelnum) {
		this.modelnum = modelnum;
	}
	
}