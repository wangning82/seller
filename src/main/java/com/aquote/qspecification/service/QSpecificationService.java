/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qspecification.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.aquote.qspecification.entity.QSpecification;
import com.aquote.qspecification.dao.QSpecificationDao;

/**
 * 产品规格管理Service
 * @author wn
 * @version 2017-09-15
 */
@Service
@Transactional(readOnly = true)
public class QSpecificationService extends CrudService<QSpecificationDao, QSpecification> {

	public QSpecification get(String id) {
		return super.get(id);
	}
	
	public List<QSpecification> findList(QSpecification qSpecification) {
		return super.findList(qSpecification);
	}
	
	public Page<QSpecification> findPage(Page<QSpecification> page, QSpecification qSpecification) {
		return super.findPage(page, qSpecification);
	}
	
	@Transactional(readOnly = false)
	public void save(QSpecification qSpecification) {
		super.save(qSpecification);
	}
	
	@Transactional(readOnly = false)
	public void delete(QSpecification qSpecification) {
		super.delete(qSpecification);
	}
	
}