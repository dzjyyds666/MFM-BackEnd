package com.Aaron.MFM.common.chat;

import lombok.Data;

@Data
public class ChatVo {
    private Long senderId;
    private Long receiverId;
    private String content;
    private String type;
}
