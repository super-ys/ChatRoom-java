package com.zys.websocketchatroom.service;


import com.zys.websocketchatroom.pojo.HistoryMsg;
import com.zys.websocketchatroom.pojo.TextMessage;

import java.util.List;

public interface TextMessageService {

    List<HistoryMsg> HistoryOfFriend(String user_id, String friend_id);
    List<HistoryMsg> HistoryOfGroup(String group_id, String user_id);
    boolean addMessage(TextMessage message);

}
