/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.addinventory.service;

import com.seller.addinventory.dao.TalProductInDao;
import com.seller.addinventory.entity.TalProductIn;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 入库Service
 * @author wn
 * @version 2017-12-29
 */
@Service
@Transactional(readOnly = true)
public class TalProductInService extends CrudService<TalProductInDao, TalProductIn> {


	public TalProductIn get(String id) {
		return super.get(id);
	}
	
	public List<TalProductIn> findList(TalProductIn talProductIn) {
		return super.findList(talProductIn);
	}
	
	public Page<TalProductIn> findPage(Page<TalProductIn> page, TalProductIn talProductIn) {
		return super.findPage(page, talProductIn);
	}
	
	@Transactional(readOnly = false)
	public void save(TalProductIn talProductIn) {
		super.save(talProductIn);
	}
	
	@Transactional(readOnly = false)
	public void delete(TalProductIn talProductIn) {
		super.delete(talProductIn);
	}
}