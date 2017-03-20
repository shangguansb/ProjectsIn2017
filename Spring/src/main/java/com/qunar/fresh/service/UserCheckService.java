package com.qunar.fresh.service;

import com.qunar.fresh.model.User;

/**
 * Created by kingsley.zhang on 17-3-16.
 */
public interface UserCheckService {
    /**
     * 检测对象不为空
     *
     * @param objecto
     * @return 空返回false，不为空返回true
     */
    boolean checkObjectNotNull(Object objecto);

    /**
     * 根据isValid字段，检测用户是否有效，1表示有效，0表示无效
     *
     * @param user
     * @return
     */
    boolean checkUserValid(User user);

    /**
     * 检测在更新用户时，用户信息是否正确，包括username,age,sex的判断
     *
     * @param user
     * @return
     */
    boolean checkUpdateUserValid(User user);
}
