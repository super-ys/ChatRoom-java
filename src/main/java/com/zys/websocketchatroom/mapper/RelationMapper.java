package com.zys.websocketchatroom.mapper;


import com.zys.websocketchatroom.pojo.Group;
import com.zys.websocketchatroom.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RelationMapper {

    // 查询好友
    List<User> queryFriendsById(String userid);

    // 添加好友
    int insertFriend(String userid, String friend_id);

    // 查询群
    List<Group> queryGroupById(String userid);

    // 添加群
    int insertGroup(String groupid, String userid);

}
