package com.zys.websocketchatroom.mapper;


import com.zys.websocketchatroom.pojo.Group;
import com.zys.websocketchatroom.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RelationMapper {


    // 查询好友是否存在
    int isFriend(String userid, String friend_id);

    // 查询群是否存在
    int isGroupship(String userid, String group_id);

    // 查询好友
    List<User> queryFriendsById(String userid);

    // 添加好友
    int insertFriend(String userid, String friend_id);

    // 查询群聊（自己的）
    List<Group> queryGroupById(String userid);

    // 创建群聊
    int createGroup(Group group);

    // 查询群成员
    List<String> queryMembers(String group_id);

    // 更新群成员数量
    int addCount(String group_id);
    // 添加群
    int insertGroup(String groupid, String userid);

    // 查询用户
    List<User> queryUser(String user_id);

    // 查询群聊（总的）
    List<Group> queryGroup(String group_id);




}
