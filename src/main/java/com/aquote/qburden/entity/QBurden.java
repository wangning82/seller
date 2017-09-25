/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qburden.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 产品规格材料Entity
 * @author wn
 * @version 2017-09-20
 */
public class QBurden extends DataEntity<QBurden> {
	
	private static final long serialVersionUID = 1L;

	private String specificationId;		// 规格id
	private String materialsId;		// 材料id
	private String materialsName;		// 材料名称
	private String materialsQuality;		// 材料品质
	private String materialsPrice;		// 材料价钱
	private String materialsUsenum;		// 材料的用量
	
	public QBurden() {
		super();
	}

	public QBurden(String id){
		super(id);
	}

	@Length(min=1, max=64, message="规格id长度必须介于 1 和 64 之间")
	public String getSpecificationId() {
		return specificationId;
	}

	public void setSpecificationId(String specificationId) {
		this.specificationId = specificationId;
	}
	
	@Length(min=1, max=64, message="材料id长度必须介于 1 和 64 之间")
	public String getMaterialsId() {
		return materialsId;
	}

	public void setMaterialsId(String materialsId) {
		this.materialsId = materialsId;
	}
	
	@Length(min=1, max=300, message="材料名称长度必须介于 1 和 300 之间")
	public String getMaterialsName() {
		return materialsName;
	}

	public void setMaterialsName(String materialsName) {
		this.materialsName = materialsName;
	}
	
	@Length(min=1, max=200, message="材料品质长度必须介于 1 和 200 之间")
	public String getMaterialsQuality() {
		return materialsQuality;
	}

	public void setMaterialsQuality(String materialsQuality) {
		this.materialsQuality = materialsQuality;
	}
	
	public String getMaterialsPrice() {
		return materialsPrice;
	}

	public void setMaterialsPrice(String materialsPrice) {
		this.materialsPrice = materialsPrice;
	}
	
	public String getMaterialsUsenum() {
		return materialsUsenum;
	}

	public void setMaterialsUsenum(String materialsUsenum) {
		this.materialsUsenum = materialsUsenum;
	}
	
}