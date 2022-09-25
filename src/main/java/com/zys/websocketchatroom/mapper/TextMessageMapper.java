package com.zys.websocketchatroom.mapper;

import com.zys.websocketchatroom.pojo.TextMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TextMessageMapper {

    // 获取历史消息 与好友的消息
    List<TextMessage> getFriendMessage(String idone, String idtwo);

    // 获取历史纤细 与群的历史消息
    List<TextMessage> getGroupMessage(String groupid);

    // 增加一条信息
    int insert(TextMessage message);

}
