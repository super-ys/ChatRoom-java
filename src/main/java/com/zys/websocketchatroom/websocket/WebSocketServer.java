package com.zys.websocketchatroom.websocket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 注册成一个 websocket 服务
 */
@ServerEndpoint(value = "/imserver/{username}")
@Component
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    // 记录在线用户
    public static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();


    /**
     * 建立链接调用的方法
     * @param session
     * @param userid
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String userid){
        // 将用户加入到 map 中 以用户名为主键
        onlineUsers.put(userid, session);

        // 拼接 json 进行发送在线人员名单
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        result.put("users", array);
        for (Object key : onlineUsers.keySet()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userid", key);
            array.add(jsonObject);
        }
        sendOnlineUsers(result.toJSONString());


    }

    /**
     * 链接关闭调用方法
     * @param session
     * @Param username
     */
    @OnClose
    public void onClose(Session session, @PathParam("username") String username){
        onlineUsers.remove(username);
        log.info("有一个链接关闭，移除 username={}的用户session，当前在线人数为：{}", username, onlineUsers.size());
    }

    /**
     * 用户发消息调用的方法
     * @param message
     * @param session
     * @param username
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username){
        log.info("服务端收到用户username={}的消息：{}", username, message);
        JSONObject object = JSON.parseObject(message);
        if("true".equals(object.getString("isGroup"))){
            String gruop_id = object.getString("to");
            //  TODO 查询表 Groupship, 查询出群成员
            List<String> users = new ArrayList<>();
            // 找到群 id 为 group_id 的所有成员
            List<Session> sessions = new ArrayList<>();
            for(String user : users){
                sessions.add(onlineUsers.get(user));
            }
            log.info("进入群聊");
            // 发送群消息
            multicast(message, sessions);
        }else {
            log.info("进入单聊");
            Session toSession = onlineUsers.get(object.getString("to"));
            unicast(message, toSession);
        }
    }

    // 给单个用户发送信息
    private void unicast(String message, Session session) {
        try{
            log.info("服务端给客户端[{}]发送信息{}", session.getId(), message);
            session.getBasicRemote().sendText(message);
        }catch (Exception e){
            log.error("服务端发送消息给客户端失败", e);
        }


    }

    // 组播
    private void multicast(String message, List<Session> sessions){
        for(Session session : sessions){
            try{
                synchronized (session){
                    log.info("服务端给客户端[{}]发送信息{}", session.getId(), message);
                    session.getBasicRemote().sendText(message);
                }
            }catch (Exception e){
                log.info("发送组播失败");
                e.printStackTrace();
            }
        }

        // TODO 存储信息

    }



    // 给多个用户发送信息
    private void broadcast(String message) {
        for (Session session : onlineUsers.values()){
            try{
                synchronized (session){
                    log.info("服务端给客户端[{}]发送信息{}", session.getId(), message);
                    session.getBasicRemote().sendText(message);
                }
            }catch (Exception e){
                log.info("发送广播失败");
                e.printStackTrace();
            }

        }
    }

    @OnError
    public void onError(Session session, Throwable error){
        log.error("发生错误");
        error.printStackTrace();
    }


    private void sendOnlineUsers(String message) {
        try{
            for(Session session : onlineUsers.values()){
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }


        }catch (Exception e){
            log.error("服务端发送消息给客户端失败", e);
        }

    }

}
