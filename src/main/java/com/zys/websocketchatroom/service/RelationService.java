package com.zys.websocketchatroom.service;

import com.zys.websocketchatroom.pojo.Group;
import com.zys.websocketchatroom.pojo.Relationship;
import com.zys.websocketchatroom.pojo.User;

import java.util.List;

public interface RelationService {

    // 查询是否为已添加好友
    Boolean isFriend(String userid, String friendid);

    // 添加好友
    Boolean addFriend(String userid, String friendid);
    // 添加群
    Boolean addGroup(String groupid, String userid);

    // 列出好友列表
    List<User> showFriends(String userid);

    // 列出群列表
    List<Group> showGroups(String userid);

    // 查询群成员
    List<String> queryMembers(String group_id);
}
