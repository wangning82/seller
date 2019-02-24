/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.material.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.seller.material.entity.TalMaterial;
import com.seller.material.dao.TalMaterialDao;

/**
 * 材料管理Service
 * @author wn
 * @version 2017-12-28
 */
@Service
@Transactional(readOnly = true)
public class TalMaterialService extends CrudService<TalMaterialDao, TalMaterial> {

	public TalMaterial get(String id) {
		return super.get(id);
	}
	
	public List<TalMaterial> findList(TalMaterial talMaterial) {
		return super.findList(talMaterial);
	}
	
	public Page<TalMaterial> findPage(Page<TalMaterial> page, TalMaterial talMaterial) {
		return super.findPage(page, talMaterial);
	}
	
	@Transactional(readOnly = false)
	public void save(TalMaterial talMaterial) {
		super.save(talMaterial);
	}
	
	@Transactional(readOnly = false)
	public void delete(TalMaterial talMaterial) {
		super.delete(talMaterial);
	}
	
}