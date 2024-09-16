package com.Aaron.MFM.web.admin.service;

import com.Aaron.MFM.common.chat.ChatVo;
import com.Aaron.MFM.model.entity.ChatInfo;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.vo.chat.ChatNotReadVo;

import java.util.List;

public interface IChatService {
    List<ChatInfo> getChatList(Long receiverId);


    String sendMessage(ChatVo chatVo);

    List<ChatNotReadVo> getChatUserAndNotRead();

    List<ChatInfo> getMessageOneday(Long receiverId);
}
