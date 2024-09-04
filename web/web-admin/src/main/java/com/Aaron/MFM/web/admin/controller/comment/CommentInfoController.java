package com.Aaron.MFM.web.admin.controller.comment;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.web.admin.service.ICommentInfoService;
import com.Aaron.MFM.web.admin.vo.comment.CommentInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@RequestMapping("/admin/comment")
@Tag(name = "后台评论管理")
public class CommentInfoController {

    @Autowired
    private ICommentInfoService commentInfoService;

    @GetMapping("/getCommentList")
    @Operation(summary = "获取评论列表")
    public Result<List<CommentInfoVo>> getCommentList() {
        List<CommentInfoVo> list = commentInfoService.getCommentList();
        return Result.ok(list);
    }

    @Operation(summary = "删除评论")
    @DeleteMapping("/deleteComment")
    public Result<String> deleteComment(@RequestParam Integer id) {
        commentInfoService.removeById(id);
        return Result.ok("删除成功");
    }

    @Operation(summary = "根据用户id查询评论")
    @GetMapping("/getCommentByUserId")
    public Result<List<CommentInfoVo>> getCommentByUserId(@RequestParam Long userId) {
        return Result.ok(commentInfoService.getCommentListByUserId(userId));
    }

    @Operation(summary = "根据食品Id查询评论")
    @GetMapping("/getCommentByFoodId")
    public Result<List<CommentInfoVo>> getCommentByFoodId(@RequestParam Integer foodId) {
        return Result.ok(commentInfoService.getCommentListByFoodId(foodId));
    }
}
