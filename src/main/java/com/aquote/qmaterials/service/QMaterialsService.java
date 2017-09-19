/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qmaterials.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.aquote.qmaterials.entity.QMaterials;
import com.aquote.qmaterials.dao.QMaterialsDao;

/**
 * 原材料Service
 * @author wn
 * @version 2017-09-18
 */
@Service
@Transactional(readOnly = true)
public class QMaterialsService extends CrudService<QMaterialsDao, QMaterials> {

	
	public QMaterials get(String id) {
		QMaterials qMaterials = super.get(id);
		return qMaterials;
	}
	
	public List<QMaterials> findList(QMaterials qMaterials) {
		return super.findList(qMaterials);
	}

	public Page<QMaterials> findPage(Page<QMaterials> page, QMaterials qMaterials) {
		return super.findPage(page, qMaterials);
	}
	
	@Transactional(readOnly = false)
	public void save(QMaterials qMaterials) {
		super.save(qMaterials);
	}
	
	@Transactional(readOnly = false)
	public void delete(QMaterials qMaterials) {
		super.delete(qMaterials);
	}


	
}