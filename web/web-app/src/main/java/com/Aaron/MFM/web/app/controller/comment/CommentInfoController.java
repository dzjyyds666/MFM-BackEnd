package com.Aaron.MFM.web.app.controller.comment;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.web.app.aop.LimitAccess;
import com.Aaron.MFM.web.app.service.ICommentInfoService;
import com.Aaron.MFM.web.app.vo.comment.CommentInfovo;
import com.Aaron.MFM.web.app.vo.comment.CommentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@RequestMapping("/app/comment")
@Tag(name = "app评论管理")
public class CommentInfoController {

    @Autowired
    private ICommentInfoService commentInfoService;

    @GetMapping("/getCommentByFoodId")
    @Operation(summary = "根据userId或foodId查询评论")
    @LimitAccess
    public Result<List<CommentVo>> getCommentByFoodId(@RequestParam(required = false) Integer foodId, @RequestParam(required = false) Long userId) {
        System.out.print("foodId:"+foodId+"userId:"+userId);
        List<CommentVo> commentVoList = commentInfoService.getCommentByFoodId(foodId,userId);
        return Result.ok(commentVoList);
    }

    @PostMapping("/addComment")
    @Operation(summary = "发布评论")
    @LimitAccess
    public Result<String> addComment(@RequestBody CommentInfovo commentInfo) {
        commentInfoService.addComment(commentInfo);
        return Result.ok("添加成功");
    }
}
