/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.talcolor.dao;

import com.seller.talcolor.entity.TalColor;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 颜色管理DAO接口
 * @author wn
 * @version 2017-12-28
 */
@MyBatisDao
public interface TalColorDao extends CrudDao<TalColor> {

}