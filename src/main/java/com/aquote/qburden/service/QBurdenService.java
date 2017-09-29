/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qburden.service;

import com.aquote.qburden.dao.QBurdenDao;
import com.aquote.qburden.entity.QBurden;
import com.aquote.qspecification.dao.QSpecificationDao;
import com.aquote.qspecification.entity.QSpecification;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品规格管理Service
 * @author wn
 * @version 2017-09-15
 */
@Service
@Transactional(readOnly = true)
public class QBurdenService extends CrudService<QBurdenDao, QBurden> {

	public QBurden get(String id) {
		return super.get(id);
	}
	
	public List<QBurden> findList(QBurden qBurden) {
		return super.findList(qBurden);
	}
	
	public Page<QBurden> findPage(Page<QBurden> page, QBurden qBurden) {
		return super.findPage(page, qBurden);
	}
	
	@Transactional(readOnly = false)
	public void save(QBurden qBurden) {
		super.save(qBurden);
	}
	
	@Transactional(readOnly = false)
	public void delete(QBurden qBurden) {
		super.delete(qBurden);
	}
	//更新
	@Transactional(readOnly = false)
	public void update(QBurden qBurden) {
		dao.update(qBurden);
	}
}