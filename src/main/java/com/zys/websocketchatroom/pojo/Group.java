package com.zys.websocketchatroom.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Group {

    public String group_id;
    public String group_name;
    public String gruop_head;
    public String group_owner;

}
