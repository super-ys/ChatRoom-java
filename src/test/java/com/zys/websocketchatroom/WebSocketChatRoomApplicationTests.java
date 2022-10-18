package com.zys.websocketchatroom;

import com.zys.websocketchatroom.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketChatRoomApplicationTests {

    @Autowired(required = false)
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        //template模板，拿来即用
        connection.close();
    }

    @Autowired
    UserMapper userMapper;

    @Test
    public void toTest(){
        int count = userMapper.count();
        System.out.println(count);
    }

    public int scoreOfParentheses(String s) {
        int ans = 0;

        StringBuilder sb = new StringBuilder(s);
        int i = 0;
        while (sb.length() != 0){
            int num = 1;
            int up = -1;
            while (sb.charAt(i) == '('){
                i ++;
                up ++;
            }
            while (sb.charAt(i) == ')'){
                num = (int)Math.pow(2, up) * num;
                sb.delete(i -1, i+ 1);
                i --;
            }
            ans += num;
        }

        return ans;
    }
}
