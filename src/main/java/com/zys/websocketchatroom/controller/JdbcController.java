package com.zys.websocketchatroom.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdbcController {

    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/query")
    public String query(){
        String sql = "";
        jdbcTemplate.queryForList(sql);
        return "";
    }
}
