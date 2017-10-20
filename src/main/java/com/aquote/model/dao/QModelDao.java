/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.model.dao;

import com.aquote.model.entity.QModel;
import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import java.util.List;
/**
 * 产品型号管理DAO接口
 * @author wn
 * @version 2017-09-14
 */
@MyBatisDao
public interface QModelDao extends TreeDao<QModel> {

 public List<QModel> findListById(String modelid);
}