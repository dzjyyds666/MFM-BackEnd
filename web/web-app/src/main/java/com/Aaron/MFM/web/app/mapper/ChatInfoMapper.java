package com.Aaron.MFM.web.app.mapper;

import com.Aaron.MFM.model.entity.ChatInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface ChatInfoMapper extends BaseMapper<ChatInfo> {
    void deleteChatInfoExpired();
}
