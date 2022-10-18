package com.zys.websocketchatroom.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class HistoryMsg {

    public String user_id;
    public String avatar;
    public String name;
    public String content;
    public Boolean isMe;
    public String create_time;
}
