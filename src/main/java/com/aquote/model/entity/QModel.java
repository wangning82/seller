/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 产品型号管理Entity
 * @author wn
 * @version 2017-09-14
 */
public class QModel extends TreeEntity<QModel> {
	
	private static final long serialVersionUID = 1L;
	private QModel parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String name;		// 型号名称
	private Integer sort;		// 排序
	private String isShow;		// 是否在规格中显示
	private String permission;		// 权限标识
	
	public QModel() {
		super();
	}

	public QModel(String id){
		super(id);
	}

	@JsonBackReference
	@NotNull(message="父级编号不能为空")
	public QModel getParent() {
		return parent;
	}

	public void setParent(QModel parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=2000, message="所有父级编号长度必须介于 1 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=100, message="型号名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public Integer getSort() {		return sort;	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=1, max=1, message="是否在规格中显示长度必须介于 1 和 1 之间")
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	@Length(min=0, max=200, message="权限标识长度必须介于 0 和 200 之间")
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}