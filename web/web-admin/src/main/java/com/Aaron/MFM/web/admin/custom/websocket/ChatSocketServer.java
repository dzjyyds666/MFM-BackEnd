package com.Aaron.MFM.web.admin.custom.websocket;

import com.Aaron.MFM.common.chat.ChatVo;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.rabbitmq.RabbitConfig;
import com.Aaron.MFM.common.utils.SpringContextHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/chatSocket/{userId}")
@Slf4j
@Component
public class ChatSocketServer {

    // 存储当前会话
    private Session session;

    // 静态变量，用来记录当前在线连接数
    private static final CopyOnWriteArraySet<ChatSocketServer> chatSocketSet = new CopyOnWriteArraySet<>();

    // 存储当前在线连接用户
    private static final Map<String,Session> sessionPool = new HashMap<>();

    // json序列化
    private final ObjectMapper objectMapper = new ObjectMapper();

    private RabbitTemplate rabbitTemplate;

    @OnOpen
    public void onOpen(Session session1, @PathParam(value = "userId") String userId) {
        try{
            this.session = session1;
            chatSocketSet.add(this);
            sessionPool.put(userId, session);
            log.info("websocket消息: 有新的连接，总数为:" + chatSocketSet.size());
            this.rabbitTemplate = SpringContextHolder.getBean(RabbitTemplate.class);
        }catch (Exception e){
            throw new MFMException(500, "连接失败");
        }
    }

    // 把前端发来的聊天记录存入redis 和 mysql
    @OnMessage
    public void onMessage(String message) {
        ChatVo chatVo = null;
        try{
            chatVo = objectMapper.readValue(message, ChatVo.class);
        }catch (Exception e){
            throw new MFMException(500, "消息解析失败");
        }
        if(chatVo != null){
            // 把发送给商家消息发送到消息队列, 存入数据库
            rabbitTemplate.convertAndSend(RabbitConfig.CHAT_EXCHANGE_TO_USER , RabbitConfig.CHAT_ROUTING_KEY_TO_USER, message);
        }
    }

    @OnClose
    public void onClose( @PathParam(value = "userId") String userId) {
        chatSocketSet.remove(this);
        log.info("websocket消息: ID:"+userId+"-连接断开，剩余连接总数为:" + chatSocketSet.size());
    }

    public int sendMessage(String userId,String message) {
        Session session = sessionPool.get(userId);
        if(session != null && session.isOpen()) {
            try{
                log.error("websocket消息: 单点消息:" + message);
                session.getAsyncRemote().sendText(message);
                return 1;
            }catch (Exception e){
                throw new MFMException(501, "发送消息失败");
            }
        }else{
            return 0;
        }
    }
}
