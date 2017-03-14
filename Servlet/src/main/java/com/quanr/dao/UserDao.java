package com.quanr.dao;

import com.quanr.Users.User;

/**
 * Created by kingsley.zhang on 2017/3/10.
 */
public interface UserDao {
    int insertUser(User user);

    User queryUserByName(String username);

    int update(User user);
}
