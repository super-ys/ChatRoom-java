package com.zys.websocketchatroom.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class TextMessage {

    public String from;
    public String to;
    public String content;
    public String type;
    public Boolean isgroup;
    public String create_time;
}
