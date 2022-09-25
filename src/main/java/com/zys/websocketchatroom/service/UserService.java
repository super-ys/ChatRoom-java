package com.zys.websocketchatroom.service;

import com.zys.websocketchatroom.pojo.User;

public interface UserService {

    User login(String userid, String password);
    boolean register(User user);
    User query(String userid);
}
