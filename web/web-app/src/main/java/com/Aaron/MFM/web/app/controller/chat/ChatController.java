package com.Aaron.MFM.web.app.controller.chat;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.web.app.service.IChatService;
import com.Aaron.MFM.web.app.vo.chat.ChatVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/chat")
@Tag(name = "app聊天管理")
public class ChatController {

    @Autowired
    private IChatService chatService;


    @PostMapping("/sendMsg")
    @Operation(summary = "发送消息")
    public Result<String> sendMsg(@RequestBody ChatVo chatVo) {
        chatService.sendMsg(chatVo);
        return Result.ok("发送成功");
    }


}
