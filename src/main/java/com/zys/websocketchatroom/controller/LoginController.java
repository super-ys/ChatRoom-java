package com.zys.websocketchatroom.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model){
        model.addAttribute("username", username);
        return "chat";
    }
}
