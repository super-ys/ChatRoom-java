package com.zys.websocketchatroom.service;

import com.zys.websocketchatroom.mapper.RelationMapper;
import com.zys.websocketchatroom.pojo.Group;
import com.zys.websocketchatroom.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationServiceImpl implements RelationService{

    @Autowired
    RelationMapper relationMapper;

    @Override
    public Boolean isFriend(String userid, String friend_id) {
        int friend = relationMapper.isFriend(userid, friend_id);
        return friend != 0 ? true : false;
    }

    @Override
    public Boolean addFriend(String userid, String friendid) {

        int i = relationMapper.insertFriend(userid, friendid);
        return i != 0 ? true : false;
    }

    @Override
    public Boolean addGroup(String groupid, String userid) {
        int i = relationMapper.insertGroup(groupid, userid);
        return i != 0 ? true : false;
    }

    @Override
    public List<User> showFriends(String userid) {
        List<User> users = relationMapper.queryFriendsById(userid);
        return users;
    }

    @Override
    public List<Group> showGroups(String userid) {
        List<Group> groups = relationMapper.queryGroupById(userid);
        return groups;
    }

    @Override
    public List<String> queryMembers(String group_id) {
        List list = relationMapper.queryMembers(group_id);
        return list;
    }
}
