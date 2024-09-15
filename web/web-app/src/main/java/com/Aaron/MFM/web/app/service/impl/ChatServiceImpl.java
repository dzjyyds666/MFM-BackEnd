package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.common.rabbitmq.RabbitConfig;

import com.Aaron.MFM.model.entity.ChatInfo;
import com.Aaron.MFM.web.app.mapper.ChatInfoMapper;
import com.Aaron.MFM.web.app.service.IChatService;
import com.Aaron.MFM.common.chat.ChatVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatInfoMapper, ChatInfo> implements IChatService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ChatInfoMapper chatInfoMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();



    @RabbitListener(queues = RabbitConfig.CHAT_QUEUE)
    public void getMsg(String msg){
        ChatVo chatVo = null;
        try {
            chatVo = objectMapper.readValue(msg, ChatVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (chatVo != null){
            ChatInfo chatinfo = new ChatInfo();
            chatinfo.setId(String.valueOf(UUID.randomUUID()).substring(0,10).replace("-","A"));
            chatinfo.setSenderId(chatVo.getSenderId());
            chatinfo.setReceiverId(chatVo.getReceiverId());
            chatinfo.setContent(chatVo.getContent());
            chatinfo.setType(chatVo.getType());
            chatInfoMapper.insert(chatinfo);
        }
    }
}
