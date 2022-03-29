package com.tqy.blog.service;

import com.tqy.blog.po.User;

/**
 * @author tqy
 * @version 1.0
 * @date 2021/2/22 11:51 下午
 */
public interface UserService {
    User chekUser(String username, String password);
}
