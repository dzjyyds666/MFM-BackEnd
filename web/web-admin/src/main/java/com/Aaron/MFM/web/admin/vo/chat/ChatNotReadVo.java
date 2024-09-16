package com.Aaron.MFM.web.admin.vo.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ChatNotReadVo {
    private Long id;

    private String nickName;

    private String avatarUrl;

    private Integer notReadNumber;

}
