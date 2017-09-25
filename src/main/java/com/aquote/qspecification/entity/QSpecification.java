/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qspecification.entity;

import com.aquote.model.entity.QModel;
import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 产品规格管理Entity
 * @author wn
 * @version 2017-09-15
 */
public class QSpecification extends DataEntity<QSpecification> {
	
	private static final long serialVersionUID = 1L;

	private QModel qModel;		// 父归属的型号
	private String modelId;		// 型号id
	private String name;		// 规格名称
	private String type;		// 规格类型
	private double profitratio;	//利润率
	private double chargeratio;	//加工费
	private String isShow;		// 是否显示
	private String sort;		// 排序
	private String permission;		// 权限标识
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public QSpecification() {
		super();
	}

	public QSpecification(String id){
		super(id);
	}


	public QModel getqModel() {
		return qModel;
	}

	public void setqModel(QModel qModel) {
		this.qModel = qModel;
	}

	@Length(min=1, max=64, message="型号id长度必须介于 1 和 64 之间")
	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	@Length(min=1, max=100, message="规格名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=10, message="规格类型长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	//利润率
	public double getProfitratio() {
		return profitratio;
	}

	public void setProfitratio(double profitratio) {
		this.profitratio = profitratio;
	}

	//加工费
	public double getChargeratio() {
		return chargeratio;
	}

	public void setChargeratio(double chargeratio) {
		this.chargeratio = chargeratio;
	}

	@Length(min=1, max=1, message="是否在规格中显示长度必须介于 1 和 1 之间")
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}


	@Length(min=0, max=200, message="权限标识长度必须介于 0 和 200 之间")
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
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