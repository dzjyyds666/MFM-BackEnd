package com.Aaron.MFM.common.websocket;

import com.Aaron.MFM.common.exception.MFMException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{userId}")
@Slf4j
public class WebSocketServer {
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
    private static final CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    // 静态变量，用来记录当前在线连接数
    private static final Map<String,Session> sessionPool = new HashMap<>();


    /*
    * 连接建立后调用的方法
    * */
    @OnOpen
    public void onOpen(Session session1, @PathParam(value = "userId") String userId) {
        try{
            this.session = session1;
            webSocketSet.add(this);
            sessionPool.put(userId, session);
            log.info("websocket消息: 有新的连接，总数为:" + webSocketSet.size());
        }catch (Exception e){
            throw new MFMException(500, "连接失败");
        }
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("websocket消息: 收到客户端发来的消息:" + message);
    }

    @OnClose
    public void onClose( @PathParam(value = "userId") String userId) {
        webSocketSet.remove(this);
        log.info("websocket消息: ID:"+userId+"-连接断开，剩余连接总数为:" + webSocketSet.size());
    }

    /*
    * 此为单点消息
    * */
    public void sendMessage(String userId,String message) {
        Session session1 = sessionPool.get(userId);
        if(session1 != null && session1.isOpen()){
            try{
                log.error("websocket消息: 单点消息:" + message);
                session1.getAsyncRemote().sendText(message);
            }catch (Exception e){
                throw new MFMException(501, "发送消息失败");
            }
        }
    }
}
