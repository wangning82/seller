package com.aquote.taluser.dao;

import com.aquote.taluser.entity.TalUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 2016/11/24.
 */
public class TalUserDaoImplement implements TalUserDao {
    @Override
    public void save(Connection connection, TalUser talUser) throws SQLException {
        //链接数据库进行插入
        PreparedStatement statement = connection.prepareCall("insert into tal_user (loginname,password,nickname) values(?,?,?)");
        //插入的三项数据
        statement.setString(1, talUser.getLoginName());
        statement.setString(2, talUser.getPassword());
        statement.setString(3, talUser.getNickname());

        statement.execute();
    }

    @Override
    public int queryUserName(Connection connection, String loginName) throws SQLException {
        PreparedStatement statement = connection.prepareCall("Select * from tal_user where loginname = ?");

        statement.setString(1, loginName);

        ResultSet set = statement.executeQuery();
        // 下一个不为空，说明数据库中包含有此字段,则返回此条数据的id
        if (set.next()) {
            return set.getInt("id");
        } else {
            return 0;
        }
    }

    @Override
    public int queryPassWord(Connection connection, int id, String password) throws SQLException {
        PreparedStatement statement = connection.prepareCall("Select * from tal_user where id = ? and password = ?");

        statement.setInt(1, id);
        statement.setString(2, password);

        ResultSet set = statement.executeQuery();
        // 下一个不为空，说明数据库中包含有此字段,则返回此条数据的id
        if (set.next()) {
            return set.getInt("id");
        } else {
            return 0;
        }
    }

    @Override
    public void updateToken(Connection connection, int id, String token) throws SQLException {
        PreparedStatement statement = connection.prepareCall("update tal_user set token =  ? where id = ?");

        statement.setString(1, token);
        statement.setInt(2, id);

        statement.execute();
    }

    @Override
    public int getUserId(Connection connection, String token) throws SQLException {
        PreparedStatement statement = connection.prepareCall("select * from tal_user where token = ?");

        statement.setString(1, token);
        statement.executeQuery();

        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return set.getInt("id");
        } else {
            return 0;
        }
    }

    @Override
    public TalUser get(String id) {
        return null;
    }

    @Override
    public TalUser get(TalUser entity) {
        return null;
    }

    @Override
    public List<TalUser> findList(TalUser entity) {
        return null;
    }

    @Override
    public List<TalUser> findAllList(TalUser entity) {
        return null;
    }

    @Override
    public List<TalUser> findAllList() {
        return null;
    }

    @Override
    public int insert(TalUser entity) {
        return 0;
    }

    @Override
    public int update(TalUser entity) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int delete(TalUser entity) {
        return 0;
    }
}
