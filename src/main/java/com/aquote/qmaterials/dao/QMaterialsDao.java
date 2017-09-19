/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qmaterials.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.aquote.qmaterials.entity.QMaterials;

/**
 * 原材料DAO接口
 * @author wn
 * @version 2017-09-18
 */
@MyBatisDao
public interface QMaterialsDao extends CrudDao<QMaterials> {


	
}