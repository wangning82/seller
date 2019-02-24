/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.productinven.service;

import com.seller.productinven.dao.TalProductDao;
import com.seller.productinven.entity.TalProduct;
import com.seller.talcolor.dao.TalColorDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 库存Service
 * @author wn
 * @version 2017-12-28
 */
@Service
@Transactional(readOnly = true)
public class TalProductService extends CrudService<TalProductDao, TalProduct> {

	private TalColorDao colorDao;

	public TalProduct get(String id) {
		return super.get(id);
	}
	
	public List<TalProduct> findList(TalProduct talProduct) {
		return super.findList(talProduct);
	}
	
	public Page<TalProduct> findPage(Page<TalProduct> page, TalProduct talProduct) {
		return super.findPage(page, talProduct);
	}

	@Transactional(readOnly = false)
	public void save(TalProduct talProduct) {
		super.save(talProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(TalProduct talProduct) {
		super.delete(talProduct);
	}
	
}