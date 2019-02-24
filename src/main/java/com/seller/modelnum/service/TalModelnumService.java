/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.modelnum.service;

import com.seller.modelnum.dao.TalModelnumDao;
import com.seller.modelnum.entity.TalModelnum;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 款号Service
 * @author wn
 * @version 2017-12-28
 */
@Service
@Transactional(readOnly = true)
public class TalModelnumService extends CrudService<TalModelnumDao, TalModelnum> {

	public TalModelnum get(String id) {
		return super.get(id);
	}
	
	public List<TalModelnum> findList(TalModelnum talModelnum) {
		return super.findList(talModelnum);
	}
	
	public Page<TalModelnum> findPage(Page<TalModelnum> page, TalModelnum talModelnum) {
		return super.findPage(page, talModelnum);
	}

	@Transactional(readOnly = false)
	public void save(TalModelnum talModelnum) {
		super.save(talModelnum);
	}
	
	@Transactional(readOnly = false)
	public void delete(TalModelnum talModelnum) {
		super.delete(talModelnum);
	}
	
}