package com.zys.websocketchatroom.controller;


import com.zys.websocketchatroom.pojo.Group;
import com.zys.websocketchatroom.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relation")
public class RelationController {


    @GetMapping("/friends/{userid}")
    public List<User> getFriends(@RequestParam("userid") String userid){
        return null;
    }

    @GetMapping("group/{userid}")
    public List<Group> getGroup(@RequestParam("userid") String userid){
        return null;
    }
}
