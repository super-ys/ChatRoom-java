package com.zys.websocketchatroom.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Relationship {

    public String user_id;
    public String friend_id;

}
