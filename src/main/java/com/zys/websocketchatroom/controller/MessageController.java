package com.zys.websocketchatroom.controller;


import com.zys.websocketchatroom.pojo.HistoryMsg;
import com.zys.websocketchatroom.pojo.TextMessage;
import com.zys.websocketchatroom.service.TextMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @param userid 本人id
     * @param friendid 好友/
     * @return
     */
    @GetMapping("/friend/{userid}/{friendid}")
    public Map<String, Object> frendHistory(@RequestParam("userid") String userid,
                                        @RequestParam("friendid") String friendid){
        Map<String, Object> result = new HashMap<>();
        try{
            List<HistoryMsg> historyMsgs = messageService.HistoryOfFriend(userid, friendid);
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
    public Map<String, Object> groupHistory(@RequestParam("group_id") String group_id,
                                            @RequestParam("user_id") String user_id){
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
