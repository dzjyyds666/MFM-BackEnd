package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.rabbitmq.RabbitConfig;

import com.Aaron.MFM.web.app.service.IChatService;
import com.Aaron.MFM.web.app.vo.chat.ChatVo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements IChatService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsg(ChatVo chatVo) {
        chatVo.setFromId(LoginHolder.getLoginUser().getId());
        String message = chatVo.getFromId()+":"+ chatVo.getToId()+":"+chatVo.getMsg();
        rabbitTemplate.convertAndSend(RabbitConfig.CHAT_EXCHANGE,RabbitConfig.CHAT_ROUTING_KEY,message);
    }

    @RabbitListener(queues = RabbitConfig.CHAT_QUEUE)
    public void getMsg(String msg){
        System.out.println("接收到消息："+msg);
    }

}
