/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.spec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.seller.spec.entity.TalSpec;
import com.seller.spec.dao.TalSpecDao;

/**
 * 规格Service
 * @author wn
 * @version 2017-12-28
 */
@Service
@Transactional(readOnly = true)
public class TalSpecService extends CrudService<TalSpecDao, TalSpec> {

	public TalSpec get(String id) {
		return super.get(id);
	}
	
	public List<TalSpec> findList(TalSpec talSpec) {
		return super.findList(talSpec);
	}
	
	public Page<TalSpec> findPage(Page<TalSpec> page, TalSpec talSpec) {
		return super.findPage(page, talSpec);
	}
	
	@Transactional(readOnly = false)
	public void save(TalSpec talSpec) {
		super.save(talSpec);
	}
	
	@Transactional(readOnly = false)
	public void delete(TalSpec talSpec) {
		super.delete(talSpec);
	}
	
}