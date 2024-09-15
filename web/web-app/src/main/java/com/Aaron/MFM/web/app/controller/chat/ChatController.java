package com.Aaron.MFM.web.app.controller.chat;

import com.Aaron.MFM.web.app.service.IChatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/chat")
@Tag(name = "app聊天管理")
public class ChatController {

    @Autowired
    private IChatService chatService;
}
