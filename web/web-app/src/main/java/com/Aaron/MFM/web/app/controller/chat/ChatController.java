package com.Aaron.MFM.web.app.controller.chat;

import com.Aaron.MFM.common.chat.ChatVo;
import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.model.entity.ChatInfo;
import com.Aaron.MFM.web.app.aop.LimitAccess;
import com.Aaron.MFM.web.app.service.IChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/chat")
@Tag(name = "app聊天管理")
public class ChatController {

    @Autowired
    private IChatService chatService;

    @GetMapping("getNotReadNumber")
    @Operation(summary = "获取未读消息数量")
    @LimitAccess
    public Result<Integer> getNotReadNumber()
    {
        return Result.ok(chatService.getNotReadNumber());
    }

    @GetMapping("getMessageOneday")
    @Operation(summary = "获取当天消息")
    @LimitAccess
    public Result<List<ChatInfo>> getMessageOneday()
    {
        return Result.ok(chatService.getMessageOneday());
    }

    @PostMapping("sendMessage")
    @Operation(summary = "发送消息")
    @LimitAccess(maxAccessCount = 60)
    public Result<String> sendMessage(@RequestBody ChatVo chatVo)
    {
        return Result.ok(chatService.sendMessage(chatVo));
    }

    @GetMapping("getMessageList")
    @Operation(summary = "获取聊天记录")
    @LimitAccess
    public Result<List<ChatInfo>> getMessageList()
    {
        return Result.ok(chatService.getMessageList());
    }
}
