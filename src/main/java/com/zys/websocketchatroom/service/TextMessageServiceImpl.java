package com.zys.websocketchatroom.service;

import com.zys.websocketchatroom.mapper.TextMessageMapper;
import com.zys.websocketchatroom.mapper.UserMapper;
import com.zys.websocketchatroom.pojo.HistoryMsg;
import com.zys.websocketchatroom.pojo.TextMessage;
import com.zys.websocketchatroom.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class TextMessageServiceImpl implements TextMessageService{

    @Autowired
    private TextMessageMapper textMessageMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取与好友之间的历史消息
     * @param user_id
     * @param friend_id
     * @return
     */
    @Override
    public List<HistoryMsg> HistoryOfFriend(String user_id, String friend_id) {

        List<TextMessage> friendMessage = textMessageMapper.getFriendMessage(user_id, friend_id);

        return MessageConvertHistory(friendMessage, user_id);
    }

    /**
     * 获取群聊间的历史消息
     * @param group_id
     * @param user_id
     * @return
     */
    @Override
    public List<HistoryMsg> HistoryOfGroup(String group_id, String user_id) {
        List<TextMessage> groupMessage = textMessageMapper.getGroupMessage(group_id);
        return MessageConvertHistory(groupMessage, user_id);
    }

    /**
     * 新增一条消息
     * @param message
     * @return
     */
    @Override
    public boolean addMessage(TextMessage message) {
        int insert = textMessageMapper.insert(message);
        return insert != 0 ? true : false;
    }


    /**
     * 将 消息 message 转换成历史消息的格式
     * @param Message
     * @param user_id
     * @return
     */
    private List<HistoryMsg> MessageConvertHistory(List<TextMessage> Message, String user_id){
        List<HistoryMsg> historyMsgs = new ArrayList<>();
        for(TextMessage msg : Message){
            HistoryMsg historyMsg = new HistoryMsg();
            User user = userMapper.queryById(msg.getFrom());
            historyMsg.setUser_id(user.getUserid());
            historyMsg.setAvatar(user.getAvatar());
            historyMsg.setName(user.getUsername());
            if(user_id.equals(msg.getFrom())){
                historyMsg.setIsMe(true);
            }else {
                historyMsg.setIsMe(false);
            }
            historyMsg.setContent(msg.getContent());
            historyMsg.setCreate_time(msg.getCreate_time());

            historyMsgs.add(historyMsg);
        }

        return historyMsgs;
    }
}
