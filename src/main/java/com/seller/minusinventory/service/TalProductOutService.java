/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.minusinventory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.seller.minusinventory.entity.TalProductOut;
import com.seller.minusinventory.dao.TalProductOutDao;

/**
 * 销售Service
 * @author wn
 * @version 2017-12-30
 */
@Service
@Transactional(readOnly = true)
public class TalProductOutService extends CrudService<TalProductOutDao, TalProductOut> {

	public TalProductOut get(String id) {
		return super.get(id);
	}
	
	public List<TalProductOut> findList(TalProductOut talProductOut) {
		return super.findList(talProductOut);
	}
	
	public Page<TalProductOut> findPage(Page<TalProductOut> page, TalProductOut talProductOut) {
		return super.findPage(page, talProductOut);
	}
	
	@Transactional(readOnly = false)
	public void save(TalProductOut talProductOut) {
		super.save(talProductOut);
	}
	
	@Transactional(readOnly = false)
	public void delete(TalProductOut talProductOut) {
		super.delete(talProductOut);
	}
	
}