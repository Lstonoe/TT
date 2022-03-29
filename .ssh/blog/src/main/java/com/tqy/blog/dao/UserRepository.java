package com.tqy.blog.dao;

import com.tqy.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tqy
 * @version 1.0
 * @date 2021/2/22 11:54 下午
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);


}
