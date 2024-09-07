package com.Aaron.MFM.web.app.mapper;

import com.Aaron.MFM.model.entity.CommentInfo;
import com.Aaron.MFM.web.app.vo.comment.CommentVo;
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

    List<CommentVo> getCommentByFoodId(Integer foodId,Long userId);
}
