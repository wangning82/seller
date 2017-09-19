/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qmaterials.entity;

import com.aquote.model.entity.QModel;
import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 原材料Entity
 * @author wn
 * @version 2017-09-18
 */
public class QMaterials extends DataEntity<QMaterials> {
	
	private static final long serialVersionUID = 1L;

	private QModel qModel;        //产品的型号类
	private String modelId;		// 型号id
	private String name;		// 材料名称
	private String sort;		// 排序
	private String quality;		// 材料分类
	private String price;		// 价格
	private String permission;		// 权限标识
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public QMaterials() {
		super();
	}

	public QMaterials(String id){
		super(id);
	}
	//产品类的对象
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
	
	@Length(min=1, max=100, message="材料名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//原材料的品质quality
	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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