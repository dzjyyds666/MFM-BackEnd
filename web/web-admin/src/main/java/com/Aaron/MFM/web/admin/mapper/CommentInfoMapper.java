package com.Aaron.MFM.web.admin.mapper;

import com.Aaron.MFM.model.entity.CommentInfo;
import com.Aaron.MFM.web.admin.vo.comment.CommentInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface CommentInfoMapper extends BaseMapper<CommentInfo> {

    List<CommentInfoVo> getCommentListByUserId(Long userId);

    List<CommentInfoVo> getCommentList();

    List<CommentInfoVo> getCommentListByFoodId(Integer foodId);
}
