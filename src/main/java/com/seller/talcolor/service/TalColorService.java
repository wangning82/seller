/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.talcolor.service;

import com.seller.talcolor.dao.TalColorDao;
import com.seller.talcolor.entity.TalColor;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 颜色管理Service
 * @author wn
 * @version 2017-12-28
 */
@Service
@Transactional(readOnly = true)
public class TalColorService extends CrudService<TalColorDao, TalColor> {

	public TalColor get(String id) {
		return super.get(id);
	}
	
	public List<TalColor> findList(TalColor talColor) {
		return super.findList(talColor);
	}

	
	public Page<TalColor> findPage(Page<TalColor> page, TalColor talColor) {

		return super.findPage(page, talColor);
	}
	
	@Transactional(readOnly = false)
	public void save(TalColor talColor) {
		super.save(talColor);
	}
	
	@Transactional(readOnly = false)
	public void delete(TalColor talColor) {
		super.delete(talColor);
	}
	
}