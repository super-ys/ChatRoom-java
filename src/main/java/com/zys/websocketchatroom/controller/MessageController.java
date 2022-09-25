package com.zys.websocketchatroom.controller;


import com.zys.websocketchatroom.pojo.TextMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    /**
     * 获取和一个好友的，或者群的历史消息
     * @param idone 本人id
     * @param idtwo 好友/群id
     * @return
     */
    @GetMapping("/histry/{idone}/{idtwo}")
    public List<TextMessage> getMessage(@RequestParam("idone") String idone,
                                        @RequestParam("idtwo") String idtwo){

        return null;
    }
}
