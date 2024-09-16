package com.Aaron.MFM.web.admin.controller.chat;


import com.Aaron.MFM.common.chat.ChatVo;
import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.model.entity.ChatInfo;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.service.IChatService;
import com.Aaron.MFM.web.admin.vo.chat.ChatNotReadVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/chat")
@Tag(name = "app聊天管理")
public class ChatController {

    @Autowired
    private IChatService chatService;

    @PostMapping("getchatList")
    @Operation(summary = "获取聊天记录")
    private Result<List<ChatInfo>> getChatList(Long receiverId)
    {
        return Result.ok(chatService.getChatList(receiverId));
    }

    @PostMapping("sendMessage")
    @Operation(summary = "发送消息")
    private Result<String> sendMessage(@RequestBody ChatVo chatVo)
    {
        return Result.ok(chatService.sendMessage(chatVo));
    }

    @PostMapping("getchatUser")
    @Operation(summary = "获取聊天用户并获取到对应未读消息")
    private Result<List<ChatNotReadVo>> getChatUser()
    {
        return Result.ok(chatService.getChatUserAndNotRead());
    }

    @PostMapping("getMessageOneday")
    @Operation(summary = "获取当天消息")
    private Result<List<ChatInfo>> getMessageOneday(Long receiverId)
    {
        return Result.ok(chatService.getMessageOneday(receiverId));
    }

}
