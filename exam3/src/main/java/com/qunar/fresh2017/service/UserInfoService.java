package com.qunar.fresh2017.service;

/**
 * Created by kingsley.zhang on 2017/3/23.
 * 服务层，与用户信息相关的服务
 */
public interface UserInfoService {
    /*
     * 判断登录的用户和密码是否正确
     */
    public boolean login(String userName, String password);
}
