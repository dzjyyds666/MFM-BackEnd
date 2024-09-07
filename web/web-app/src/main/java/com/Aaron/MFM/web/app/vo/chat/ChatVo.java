package com.Aaron.MFM.web.app.vo.chat;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long fromId;

    private Long toId;

    private String msg;
}
