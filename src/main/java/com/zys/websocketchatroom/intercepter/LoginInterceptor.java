package com.zys.websocketchatroom.intercepter;

import com.alibaba.fastjson.JSONObject;
import com.zys.websocketchatroom.websocket.WebSocketServer;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if("OPTIONS".equals(request.getMethod().toUpperCase())){
            log.info("Method:OPTIONS");
            return true;
        }
        String authorization = request.getHeader("Authorization");
        log.info("拦截的请求token: "+authorization);
        if(authorization != null){
            return true;
        }else{
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setStatus(401);
            return false;
        }

    }

    /**
     * 认证失败返回json数据
     * @param response
     * @param json
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
