package com.qunar.fresh.service;

import com.qunar.fresh.model.User;

import java.util.List;

/**
 * Created by kingsley.zhang on 17-3-16.
 */
public interface UserService {

    /**
     * 插入单个用户
     *
     * @param user
     * @return
     */
    String insertSingleUser(User user);

    /**
     * 删除单个用户
     *
     * @param id
     * @return
     */
    String deleteSingleUser(Integer id);

    /**
     * 删除单个用户
     *
     * @param user
     * @return
     */
    String deleteSingleUser(User user);

    /**
     * 修改用户信息
     *
     * @param
     * @return
     */

    String updateSingleUser(User user);

    /**
     * 根据UserInfo查询单个用户
     *
     * @param user
     * @return
     */
    User querySingleUser(User user);

    /**
     * 根据用户编号查询单个用户详细信息
     *
     * @param id
     * @return
     */
    User querySingleUserById(Integer id);

    /**
     * 查询所有 有效 用户
     *
     * @return
     */
    List<User> queryAllUsers();
}
