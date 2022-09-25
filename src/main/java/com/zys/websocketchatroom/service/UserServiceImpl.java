package com.zys.websocketchatroom.service;


import com.zys.websocketchatroom.mapper.UserMapper;
import com.zys.websocketchatroom.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 * 注册、登录、查询
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String userid, String password) {
        return userMapper.login(userid, password);
    }

    @Override
    public boolean register(User user) {
        int register = userMapper.register(user);
        return register != 0 ? true : false;
    }

    @Override
    public User query(String userid) {
        return userMapper.queryById(userid);
    }
}
