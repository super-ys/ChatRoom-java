package com.zys.websocketchatroom.controller;


import com.zys.websocketchatroom.pojo.HistoryMsg;
import com.zys.websocketchatroom.pojo.TextMessage;
import com.zys.websocketchatroom.service.TextMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {



    @Autowired
    private TextMessageService messageService;

    /**
     * 获取和一个好友的，或者群的历史消息
     * @param user_id 本人id
     * @param friend_id 好友/
     * @return
     */
    @GetMapping("/friend/{user_id}/{friend_id}")
    public Map<String, Object> frendHistory(@PathVariable String user_id,
                                        @PathVariable String friend_id){
        Map<String, Object> result = new HashMap<>();
        try{
            List<HistoryMsg> historyMsgs = messageService.HistoryOfFriend(user_id, friend_id);
            result.put("code", "200");
            result.put("msg", "历史消息拉取成功");
            result.put("data", historyMsgs);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", "400");
            result.put("msg", "历史消息拉取失败");
        }

        return result;
    }

    @GetMapping("/group/{group_id}/{user_id}")
    public Map<String, Object> groupHistory(@PathVariable String group_id,
                                            @PathVariable String user_id){
        Map<String, Object> result = new HashMap<>();
        try{
            List<HistoryMsg> historyMsgs = messageService.HistoryOfGroup(group_id, user_id);
            result.put("code", "200");
            result.put("msg", "历史消息拉取成功");
            result.put("data", historyMsgs);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", "400");
            result.put("msg", "历史消息拉取失败");
        }
        return result;
    }
}
