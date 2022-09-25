package com.zys.websocketchatroom.controller;


import com.zys.websocketchatroom.pojo.User;
import com.zys.websocketchatroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestParam("userid") String userid,
                      @RequestParam("password") String password){
        System.out.println(userid+ "----" + password);
       User user = userService.login(userid, password);
       return user;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user){
        System.out.println(user);
        boolean register = userService.register(user);
        Map<String, String> res = new HashMap();
        String code = "200";
        String msg = "注册成功";
        if(!register){
            code = "400";
            msg = "注册失败，请稍后重试";
        }
        res.put("code", code);
        res.put("msg", msg);
        return res;
    }

    @GetMapping("/query/{userid}")
    public User query(@PathVariable String userid){
        return userService.query(userid);
    }
}
