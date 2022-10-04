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

    @PostMapping("/addfriend/{userid}/{friendid}")
    public Map<String, Object> addFriends(@RequestParam("userid") String user_id,
                                          @RequestParam("friendid") String friend_id){
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

    @PostMapping("/addgroup/{userid}/{groupid}")
    public Map<String, Object> addGroup(@RequestParam("userid") String user_id,
                                        @RequestParam("groupid") String group_id){
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
    public Map<String, Object> getFriends(@RequestParam("userid") String userid){
        Map<String, Object> result = new HashMap<>();
        try{
            List<User> users = relationService.showFriends(userid);
            result.put("code", "200");
            result.put("msg", "好友加载成功");
            result.put("data", users);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", "400");
            result.put("msg", "群聊加载失败");
        }
        return result;
    }

    @GetMapping("group/{userid}")
    public Map<String, Object> getGroup(@RequestParam("userid") String userid){
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
}
