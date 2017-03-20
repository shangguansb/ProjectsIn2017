package com.qunar.fresh.dao.impl;

import com.qunar.fresh.dao.UserDao;
import com.qunar.fresh.model.User;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kingsley.zhang on 17-3-16.
 */
@Component
@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertSingleUser(User user) {
        String sql = "insert into tb_user (username,gender,age,remark,isValid,registerTime)values(?,?,?,?,?,?)";
        int insertCount = jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getGender(), user.getAge(), user.getRemark(), user.getIsValid(), user.getRegisterTime().toString()});
        return insertCount;
    }

    @Override
    public int deleteSingleUser(User user) {

        String sql = "update tb_user set isValid=0 where id=" + user.getId();
        int deleteCount = jdbcTemplate.update(sql);
        updateModifyTime(user);
        return deleteCount;
    }

    @Override
    public int updateSingleUser(User user) {
        String sql = "update tb_user set username=?, gender=?, age=?, remark=?, isValid=? where id=?";
        int updateCount = jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getGender(), user.getAge(), user.getRemark(), user.getIsValid(), user.getId()});
        updateModifyTime(user);
        return updateCount;
    }

    private int updateModifyTime(User user) {
        String sql = "update tb_user set lastModifyTime=? where id=?";
        user.setLastModifyTime(new DateTime());
        int updateCount = jdbcTemplate.update(sql, new Object[]{user.getLastModifyTime().toString(), user.getId()});
        return updateCount;
    }

    @Override
    public User selectSingleUser(User user) {
        String sql = "SELECT id, username, gender, age, remark, isValid, registerTime, lastModifyTime FROM tb_user where id=? and isValid=1";
        User userInfo = jdbcTemplate.query(sql, new Object[]{user.getId()}, new UserMapper()).get(0);
        return userInfo;
    }

    @Override
    public List<User> selectAllUsers() {
        String sql = "SELECT id, username, gender, age, remark, isValid, registerTime, lastModifyTime FROM tb_user where isValid=1";
        List<User> userList = jdbcTemplate.query(sql, new UserMapper());
        return userList;
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setAge(rs.getInt("age"));
            user.setGender(rs.getInt("gender"));
            user.setRemark(rs.getString("remark"));
            user.setIsValid(rs.getInt("isValid"));
            user.setRegisterTime(new DateTime(rs.getTimestamp("registerTime").getTime()));
            user.setLastModifyTime(new DateTime(rs.getTimestamp("lastModifyTime").getTime()));
            return user;
        }
    }
}
