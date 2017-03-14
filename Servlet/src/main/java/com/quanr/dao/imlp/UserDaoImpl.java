package com.quanr.dao.imlp;

import com.quanr.Users.User;
import com.quanr.Utils.DataBaseUtil;
import com.quanr.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by kingsley.zhang on 2017/3/10.
 */
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    public int insertUser(User user) {
        int temp = 0;
        Connection connection = DataBaseUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO user(User_Name,Password,Nick_Name) VALUES (?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getNickname());
            temp = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("插入数据出错", e);
        } finally {
            DataBaseUtil.close(connection, preparedStatement, null);
        }
        return temp;
    }

    public User queryUserByName(String username) {

        Connection connection = DataBaseUtil.getConnection();
        PreparedStatement preparedStatement = null;
        User user = null;
        ResultSet resultSet = null;
        String sql = "SELECT Password, Nick_Name FROM user WHERE User_Name=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setUsername(username);
                user.setPassword(resultSet.getString("Password"));
                user.setNickname(resultSet.getString("Nick_Name"));
            }
        } catch (SQLException e) {
            LOGGER.error("查询数据库错误", e);
        } finally {
            DataBaseUtil.close(connection, preparedStatement, resultSet);
        }
        return user;
    }

    public int update(User user) {
        int temp = 0;
        Connection connection = DataBaseUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE user SET Password =? WHERE User_Name=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());
            temp = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("数据更新异常", e);
        } finally {
            DataBaseUtil.close(connection, preparedStatement, null);
        }
        return temp;
    }
}
