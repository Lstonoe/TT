package com.tqy.blog.service;

import com.tqy.blog.dao.UserRepository;
import com.tqy.blog.po.User;
import com.tqy.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tqy
 * @version 1.0
 * @date 2021/2/22 11:52 下午
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User chekUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
