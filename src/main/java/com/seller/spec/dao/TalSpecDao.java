/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.spec.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.seller.spec.entity.TalSpec;

/**
 * 规格DAO接口
 * @author wn
 * @version 2017-12-28
 */
@MyBatisDao
public interface TalSpecDao extends CrudDao<TalSpec> {
	
}