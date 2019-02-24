/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.productinven.dao;

import com.seller.productinven.entity.TalProduct;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 库存DAO接口
 * @author wn
 * @version 2017-12-28
 */
@MyBatisDao
public interface TalProductDao extends CrudDao<TalProduct> {
}