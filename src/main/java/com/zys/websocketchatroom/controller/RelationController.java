package com.zys.websocketchatroom.controller;


import com.zys.websocketchatroom.pojo.Group;
import com.zys.websocketchatroom.pojo.User;
import com.zys.websocketchatroom.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;

    @PostMapping("/addfriend/{user_id}/{friend_id}")
    public Map<String, Object> addFriends(@PathVariable String user_id,
                                          @PathVariable String friend_id){
        Map<String, Object> result = new HashMap<>();
        try {
            if(!relationService.isFriend(user_id, friend_id)){
                Boolean aBoolean = relationService.addFriend(user_id, friend_id);
                if(aBoolean){
                    result.put("code", "200");
                    result.put("msg", "添加好友成功");
                }else {
                    result.put("code", "400");
                    result.put("msg", "添加好友失败");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", "400");
            result.put("msg", "添加好友失败");
        }
        return result;
    }

    @PostMapping("/addgroup/{user_id}/{group_id}")
    public Map<String, Object> addGroup(@PathVariable String user_id,
                                        @PathVariable String group_id){
        Map<String, Object> result = new HashMap<>();
        try{
            Boolean aBoolean = relationService.addGroup(group_id, user_id);
            if(aBoolean){
                result.put("code", "200");
                result.put("msg", "添加群聊成功");
            }else {
                result.put("code", "400");
                result.put("msg", "添加群聊失败");
            }
        }catch (Exception e){
            result.put("code", "400");
            result.put("msg", "添加群聊失败");
        }
        return result;
    }

    @GetMapping("/friends/{userid}")
    public Map<String, Object> getFriends(@PathVariable String userid){
        Map<String, Object> result = new HashMap<>();
        try{
            List<User> users = relationService.showFriends(userid);
            result.put("code", "200");
            result.put("msg", "好友加载成功");
            result.put("data", users);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", "400");
            result.put("msg", "好友加载失败");
        }
        return result;
    }

    @GetMapping("group/{userid}")
    public Map<String, Object> getGroup(@PathVariable String userid){
        Map<String, Object> result = new HashMap<>();
        try{
            List<Group> groups = relationService.showGroups(userid);
            result.put("code", "200");
            result.put("msg", "群聊加载成功");
            result.put("data", groups);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", "400");
            result.put("msg", "群聊加载失败");

        }
        return result;
    }

    @GetMapping("add/query/{user_id}/{query_id}/{isGroup}")
    public Map<String, Object> getQuery(@PathVariable String user_id, @PathVariable String query_id,
                                        @PathVariable boolean isGroup){
        System.out.println("user_id: " + user_id + " query_id: " + query_id + " isGroup: "+isGroup);
        Map<String, Object> result = new HashMap<>();
        try{
            List<Map<String, Object>> maps = relationService.queryUserOrGroup(user_id, query_id, isGroup);
            result.put("code", "200");
            result.put("msg", "查寻成功");
            result.put("data", maps);
        }catch (Exception e){
            result.put("code", "400");
            result.put("msg", "查询失败");
        }
        return result;
    }

    @PostMapping("add/create")
    public Map<String, Object> createGroup(@RequestBody Group group){
        Map<String, Object> result = new HashMap<>();
        try{
            Boolean group1 = relationService.createGroup(group);
            if(group1){
                result.put("code", "200");
                result.put("msg", "群聊创建成功");
            }else{
                result.put("code", "200");
                result.put("msg", "群聊创建成功");
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }
}
