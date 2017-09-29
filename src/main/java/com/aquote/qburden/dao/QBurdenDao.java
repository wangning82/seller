/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qburden.dao;

import com.aquote.qburden.entity.QBurden;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 产品规格材料DAO接口
 * @author wn
 * @version 2017-09-20
 */
@MyBatisDao
public interface QBurdenDao extends CrudDao<QBurden> {

}