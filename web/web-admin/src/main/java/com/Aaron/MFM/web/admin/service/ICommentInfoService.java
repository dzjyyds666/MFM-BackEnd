package com.Aaron.MFM.web.admin.service;

import com.Aaron.MFM.model.entity.CommentInfo;
import com.Aaron.MFM.web.admin.vo.comment.CommentInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface ICommentInfoService extends IService<CommentInfo> {

    List<CommentInfoVo> getCommentList();

    List<CommentInfoVo> getCommentListByUserId(Long userId);

    List<CommentInfoVo> getCommentListByFoodId(Integer foodId);
}
