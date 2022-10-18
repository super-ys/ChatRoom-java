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
    public Map<String, Object> login(@RequestBody Map<String, String > map){
        String userid = map.get("userid");
        String password = map.get("password");
        Map<String, Object> result = new HashMap<>();
        System.out.println(userid+ "----" + password);
        try{
            User user = userService.login(userid, password);
            if(user == null){
                result.put("code", "400");
                result.put("msg", "登录密码不正确");
            }else{
                result.put("code", "200");
                result.put("msg", "登录成功");
                result.put("data", user);
            }
        }catch (Exception e){
            result.put("code", "400");
            result.put("msg", "登录失败");
        }
       return result;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user){
        System.out.println(user);
        String code = "200";
        String msg = "注册成功";
        Map<String, String> res = new HashMap();
        try {
            User query = userService.query(user.userid);

            if (query != null) {
                code = "300";
                msg = "该登录名已被占用，请更换";
            } else {
                boolean register = userService.register(user);
                if (!register) {
                    code = "400";
                    msg = "注册失败，请稍后重试";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
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
