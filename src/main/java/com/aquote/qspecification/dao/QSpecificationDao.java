/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qspecification.dao;

import com.aquote.qspecification.entity.QSpecification;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 产品规格管理DAO接口
 * @author wn
 * @version 2017-09-15
 */
@MyBatisDao
public interface QSpecificationDao extends CrudDao<QSpecification> {

}