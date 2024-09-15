package com.Aaron.MFM.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("chat_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "_id", type = IdType.AUTO)
    private String id;

    private Long senderId;

    private Long receiverId;

    private String content;

    private LocalDateTime createTime;

    private Byte isDelete;

    private String type;
}
