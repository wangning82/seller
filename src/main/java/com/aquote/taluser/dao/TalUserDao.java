/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.taluser.dao;

import com.aquote.taluser.entity.TalUser;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 终端用户注册后的管理功能DAO接口
 * @author wn
 * @version 2016-11-24
 */
@MyBatisDao
public interface TalUserDao extends CrudDao<TalUser> {
    /**
     * 将User保存至数据库中
     *
     * @param connection
     * @param talUser
     */
    public void save(Connection connection, TalUser talUser) throws SQLException;

    /**
     * 查询数据库中是否有对应的UserName，如果有，返回对应id，没有，返回0
     *
     * @param connection
     * @param loginName
     * @return
     */
    public int queryUserName(Connection connection, String loginName) throws SQLException;

    /**
     * 根据User查询数据库中相应的id的password是否正确。如果正确，返回对应的id，否则返回0
     *
     * @param connection
     * @param password
     * @return
     * @throws SQLException
     */
    public int queryPassWord(Connection connection, int id, String password) throws SQLException;

    /**
     * 向指定Id的User中更新token
     *
     * @param connection
     * @param id
     * @param token
     * @throws SQLException
     */
    public void updateToken(Connection connection, int id, String token) throws SQLException;

    /**
     * 根据token获取到用户的id，如果没有，返回0
     * @param connection
     * @param token
     * @throws SQLException
     */
    public int getUserId(Connection connection,  String token) throws SQLException;
}