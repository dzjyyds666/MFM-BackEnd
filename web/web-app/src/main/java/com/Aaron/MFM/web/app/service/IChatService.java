package com.Aaron.MFM.web.app.service;

import com.Aaron.MFM.common.chat.ChatVo;
import com.Aaron.MFM.model.entity.ChatInfo;

import java.util.List;

public interface IChatService {
    Integer getNotReadNumber();

    List<ChatInfo> getMessageOneday();

    String sendMessage(ChatVo chatVo);

    List<ChatInfo> getMessageList();
}
