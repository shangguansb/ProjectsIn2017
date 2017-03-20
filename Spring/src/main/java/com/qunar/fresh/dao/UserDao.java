package com.qunar.fresh.dao;

import com.qunar.fresh.model.User;

import java.util.List;

/**
 * Created by kingsley.zhang on 17-3-16.
 */
public interface UserDao {
    /**
     * 数据库中存入单个用户
     *
     * @param user
     * @return
     */
    int insertSingleUser(User user);

    /**
     * 删除单个用户，逻辑删除，isValid设为1
     *
     * @param user
     * @return
     */
    int deleteSingleUser(User user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateSingleUser(User user);

    /**
     * 查询单个用户的所有信息
     *
     * @param user
     * @return
     */
    User selectSingleUser(User user);

    /**
     * 查询所有用户
     *
     * @param
     * @return
     */
    List<User> selectAllUsers();

}
