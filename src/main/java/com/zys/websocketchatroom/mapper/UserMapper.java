package com.zys.websocketchatroom.mapper;


import com.zys.websocketchatroom.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *  登录，注册，查询
 */

@Mapper
@Repository
public interface UserMapper {

    int count();

    User login(String userid, String password);
    int register(User user);

    User queryById(String userid);

}
