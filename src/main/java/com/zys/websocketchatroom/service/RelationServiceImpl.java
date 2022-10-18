package com.zys.websocketchatroom.service;

import com.zys.websocketchatroom.mapper.RelationMapper;
import com.zys.websocketchatroom.pojo.Group;
import com.zys.websocketchatroom.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        int count = 0;
        if(i != 0){
            count = relationMapper.addCount(groupid);
        }
        return count != 0 ? true : false;
    }

    @Override
    public Boolean createGroup(Group group){
        Boolean bo = false;
        int group1 = relationMapper.createGroup(group);
        if(group1 != 0){
            bo = addGroup(group.group_id, group.group_owner);
        }
        return bo;
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

    @Override
    public List<Map<String, Object>> queryUserOrGroup(String user_id, String query_id, boolean isGroup) {

        List<Map<String, Object>> result = new ArrayList<>();
        if(!isGroup){
            List<User> users = relationMapper.queryUser(query_id);
            System.out.println("users: " + users);
            for(User user : users){
                Map<String, Object> map = new HashMap<>();
                map.put("id", user.userid);
                map.put("name", user.username);
                map.put("avatar", user.avatar);
                map.put("isAdd", relationMapper.isFriend(user_id, user.userid));
                result.add(map);
            }
        }else{
            List<Group> groups = relationMapper.queryGroup(query_id);

            for(Group group : groups){
                Map<String, Object> map = new HashMap<>();
                map.put("id", group.group_id);
                map.put("name", group.group_name);
                map.put("avatar", group.group_head);
                map.put("isAdd", relationMapper.isGroupship(user_id, group.group_id));
                result.add(map);
            }
        }

        return result;
    }
}
